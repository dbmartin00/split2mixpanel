package io.split.dbm.integrations.mixpanel;

import org.json.JSONObject;

public class Impression {
	
		String key;
	    String split;
	    String environmentId;
	    String environmentName;
	    String treatment;
	    long time;
	    String bucketingKey;
	    String label;
	    String machineName;
	    String machineIp;
	    long splitVersionNumber;
	    String sdk;
	    String sdkVersion;
	    String properties;
	    
	    public Impression() {
	    	
	    }

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getSplit() {
			return split;
		}

		public void setSplit(String split) {
			this.split = split;
		}

		public String getEnvironmentId() {
			return environmentId;
		}

		public void setEnvironmentId(String environmentId) {
			this.environmentId = environmentId;
		}

		public String getEnvironmentName() {
			return environmentName;
		}

		public void setEnvironmentName(String environmentName) {
			this.environmentName = environmentName;
		}

		public String getTreatment() {
			return treatment;
		}

		public void setTreatment(String treatment) {
			this.treatment = treatment;
		}

		public long getTime() {
			return time;
		}

		public void setTime(long time) {
			this.time = time;
		}

		public String getBucketingKey() {
			return bucketingKey;
		}

		public void setBucketingKey(String bucketingKey) {
			this.bucketingKey = bucketingKey;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getMachineName() {
			return machineName;
		}

		public void setMachineName(String machineName) {
			this.machineName = machineName;
		}

		public String getMachineIp() {
			return machineIp;
		}

		public void setMachineIp(String machineIp) {
			this.machineIp = machineIp;
		}

		public long getSplitVersionNumber() {
			return splitVersionNumber;
		}

		public void setSplitVersionNumber(long splitVersionNumber) {
			this.splitVersionNumber = splitVersionNumber;
		}

		public String getSdk() {
			return sdk;
		}

		public void setSdk(String sdk) {
			this.sdk = sdk;
		}

		public String getSdkVersion() {
			return sdkVersion;
		}

		public void setSdkVersion(String sdkVersion) {
			this.sdkVersion = sdkVersion;
		}

		public String getProperties() {
			return properties;
		}

		public void setProperties(String properties) {
			this.properties = properties;
		}
	    
	    public String
	    toJson() {
	    	JSONObject result = new JSONObject();

			result.put("key", getKey());
		    result.put("split",getSplit());
		    result.put("environmentId", getEnvironmentId());
		    result.put("environmentName", getEnvironmentName());
		    result.put("treatment", getTreatment());
		    result.put("time", getTime());
		    result.put("bucketingKey", getBucketingKey());
		    result.put("label", getLabel());
		    result.put("machineName", getMachineName());
		    result.put("machineIp", getMachineIp());
		    result.put("splitVersionNumber", getSplitVersionNumber());
		    result.put("sdk", getSdk());
		    result.put("sdkVersion", getSdkVersion());
		    result.put("properties", getProperties());
		    
		    return result.toString(2);
	    }

		public Impression(String key, String split, String environmentId, String environmentName, String treatment,
				long time, String bucketingKey, String label, String machineName, String machineIp,
				long splitVersionNumber, String sdk, String sdkVersion, String properties) {
			super();
			this.key = key;
			this.split = split;
			this.environmentId = environmentId;
			this.environmentName = environmentName;
			this.treatment = treatment;
			this.time = time;
			this.bucketingKey = bucketingKey;
			this.label = label;
			this.machineName = machineName;
			this.machineIp = machineIp;
			this.splitVersionNumber = splitVersionNumber;
			this.sdk = sdk;
			this.sdkVersion = sdkVersion;
			this.properties = properties;
		}
	    
	    
}
