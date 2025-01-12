package net.konjarla.smugmug.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMImageMetadataResponse;
import net.konjarla.smugmug.client.api.response.SMImageResponse;
import net.konjarla.smugmug.model.SMImage;
import net.konjarla.smugmug.model.SMImageMetaData;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Images {

    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig("/api/v2/image/");
    private static final Logger log = LoggerFactory.getLogger(Images.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private Images() {
    }

    /**
     * Retrieves the image with the specified key.
     *
     * @param client the OAuth1 client to use when making the request
     * @param key    the key of the image to retrieve
     * @return the image with the specified key
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMImage getImageByKey(OAuth1HttpClient client, String key) {
        try {
            String path = API_CONFIG.hostName + API_CONFIG.path + key;
            SMImageResponse smImageResponse = (SMImageResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            return smImageResponse.getImage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the metadata for the image with the specified key.
     *
     * @param client the OAuth1 client to use when making the request
     * @param key    the key of the image for which to retrieve metadata
     * @return the metadata for the image with the specified key
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMImageMetaData getMetaDataOfImageByKey(OAuth1HttpClient client, String key) {
        try {
            String path = API_CONFIG.hostName + API_CONFIG.path + key;
            SMImageResponse smImageResponse = (SMImageResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            path = API_CONFIG.hostName + smImageResponse.getImage().getUris().getImageMetadata().getUri();
            SMImageMetadataResponse imageMetadataResponse = (SMImageMetadataResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            return imageMetadataResponse.getImageMetaData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the image with the specified key and information.
     *
     * @param client the OAuth1 client to use when making the request
     * @param image  the image to update
     * @return the updated image
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMImage updateImage(OAuth1HttpClient client, SMImage image) {
        if(image == null || image.getUri() == null || image.getUri().isEmpty()) {
            throw new RuntimeException("Image URI is null or empty");
        }
        try {
            String path = API_CONFIG.hostName + image.getUri();
            SMImageResponse imageResponse = (SMImageResponse) SmugMugHttpRequestBuilder
                    .create(client, HttpPatch.METHOD_NAME, path, SMBaseResponse.class)
                    .header("accept", "application/json")
                    .body(new StringEntity(mapper.writeValueAsString(image)))
                    .execute();
            return imageResponse.getImage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
