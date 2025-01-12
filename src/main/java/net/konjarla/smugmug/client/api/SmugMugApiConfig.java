package net.konjarla.smugmug.client.api;

public class SmugMugApiConfig {
    public String path;
    public String hostName = "https://api.smugmug.com";
    public String uploadHost = "https://upload.smugmug.com/";
    public String requestVerb = "GET";

    public SmugMugApiConfig() {
    }

    public SmugMugApiConfig(String path) {
        this.path = path;
    }

    public SmugMugApiConfig hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public SmugMugApiConfig uploadHost(String uploadHost) {
        this.uploadHost = uploadHost;
        return this;
    }

    public SmugMugApiConfig requestVerb(String requestVerb) {
        this.requestVerb = requestVerb;
        return this;
    }
}
