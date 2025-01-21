package net.konjarla.smugmug.client.api;

import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMWebUriLookupResponse;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class WebUriLookup {
    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig("/api/v2!weburilookup");
    private static final Logger log = LoggerFactory.getLogger(WebUriLookup.class);

    private WebUriLookup() {
    }

    /**
     * Returns a {@link SMWebUriLookupResponse} containing information about a particular weburi.
     * @param client the OAuth1HttpClient to use for making the request
     * @param webUri the weburi to lookup
     * @return the response containing information about the weburi
     * @throws IllegalArgumentException if the client or webUri is null
     * @throws RuntimeException if any other error occurs
     */
    public static SMWebUriLookupResponse lookupWebUri(OAuth1HttpClient client, String webUri) {
        if(webUri == null || client == null) {
            throw new IllegalArgumentException("webUri or OAuth1HttpClient client cannot be null");
        }
        String path = API_CONFIG.hostName + API_CONFIG.path;
        try {
            URI uri = new URIBuilder(path)
                    .addParameter("WebUri", webUri)
                    .build();
            return (SMWebUriLookupResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, uri.toString(), SMBaseResponse.class)
                    .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
