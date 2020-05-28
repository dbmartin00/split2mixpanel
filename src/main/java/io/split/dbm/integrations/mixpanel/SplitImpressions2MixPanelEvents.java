package io.split.dbm.integrations.mixpanel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

public class SplitImpressions2MixPanelEvents implements RequestStreamHandler {

    OkHttpClient client;
    
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	long start = System.currentTimeMillis();
        LambdaLogger logger = context.getLogger();
		String json = IOUtils.toString(input, Charset.forName("UTF-8"));
		logger.log("input: " + json);
		long startParse = System.currentTimeMillis();
		Impression[] impressions = new Gson().fromJson(json, Impression[].class);
        logger.log("parsed " + impressions.length + " impressions in " + (System.currentTimeMillis() - startParse) + "ms");
        
        long startMixPanel = System.currentTimeMillis();
		List<MixPanelEvent> events = new LinkedList<MixPanelEvent>();
        for(Impression impression : impressions) {
        	logger.log("output: " + impression.toJson());
        	MixPanelEvent event = new MixPanelEvent();
        	event.event = "split_evaluation";
        	event.properties = new HashMap<String, Object>();
        	event.properties.put("split", impression.split);
        	event.properties.put("distinct_id", impression.key);
        	event.properties.put("token", "9add192c319da91127787dfa1065dfa6");
        	event.properties.put("time", impression.time / 1000);
        	event.properties.put("treatment", impression.treatment);
        	event.properties.put("label", impression.label);
        	event.properties.put("environmentId", impression.environmentId);
        	event.properties.put("environmentName", impression.environmentName);
        	event.properties.put("sdk", impression.sdk);
        	event.properties.put("sdkVersion", impression.sdkVersion);
        	event.properties.put("splitVersionNumber", impression.splitVersionNumber);
        	events.add(event);
        }
        String rawJson = new Gson().toJson(events);
        Base64 base64 = new Base64();
        String encodedJson = new String(base64.encode(rawJson.getBytes()));
        String body = "data=" + encodedJson;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, body);
        logger.log("encoded MixPanel events in " + (System.currentTimeMillis() - startMixPanel) + "ms");
        
        long startPost = System.currentTimeMillis();
        if (client == null) {
        	client = new OkHttpClient();
        }
        Request request = new Request.Builder()
        		.url("http://api.mixpanel.com/track/")
        		.post(requestBody)
        		.build();
        
        client.newCall(request).execute();
        logger.log("POSTed MixPanel events in " + (System.currentTimeMillis() - startPost) + "ms");
        
    	PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));
    	writer.println("" + impressions.length + " impressions accepted and posted to MixPanel");
    	writer.flush();
    	writer.close();
    	logger.log("finished in " + (System.currentTimeMillis() - start) + "ms");
    }

    class MixPanelEvent {
    	String event;
    	Map<String, Object> properties;
    	
		public String getEvent() {
			return event;
		}
		public void setEvent(String event) {
			this.event = event;
		}
		public Map<String, Object> getProperties() {
			return properties;
		}
		public void setProperties(Map<String, Object> properties) {
			this.properties = properties;
		}
    	
    	
    }
    
}
