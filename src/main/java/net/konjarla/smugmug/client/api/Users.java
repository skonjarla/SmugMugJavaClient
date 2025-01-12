package net.konjarla.smugmug.client.api;

import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMUserResponse;
import net.konjarla.smugmug.model.SMUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Users {
    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig("/api/v2/user/");
    private static final Logger log = LoggerFactory.getLogger(Users.class);

    private Users() {
    }

    public static SMUser getAuthenticatedUser(OAuth1HttpClient client) {
        String authUserPath = "/api/v2!authuser";
        String path = API_CONFIG.hostName + authUserPath;
        try {
            SMUserResponse smUserResponse = (SMUserResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            return smUserResponse.getUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
