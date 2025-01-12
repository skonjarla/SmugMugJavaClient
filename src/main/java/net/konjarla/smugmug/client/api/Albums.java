package net.konjarla.smugmug.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMAlbumResponse;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMImagesResponse;
import net.konjarla.smugmug.model.SMAlbum;
import net.konjarla.smugmug.model.SMImage;
import net.konjarla.smugmug.model.SMPages;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Albums {

    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig("/api/v2/album/");
    private static final Logger log = LoggerFactory.getLogger(Albums.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private Albums() {
    }

    /**
     * Retrieves the album with the given key.
     *
     * @param client OAuth1HttpClient used to make authenticated requests
     * @param albumKey    the key of the album to retrieve
     * @return SMAlbum object containing the album information
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMAlbum getAlbumByKey(OAuth1HttpClient client, String albumKey) throws RuntimeException {
        try {
            String path = API_CONFIG.hostName + API_CONFIG.path + albumKey;
            SMAlbumResponse smAlbumResponse = (SMAlbumResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            return smAlbumResponse.getAlbum();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all images from the specified album.
     *
     * @param client OAuth1HttpClient used to make authenticated requests
     * @param albumKey    the album key used to identify the album
     * @return List of SMImage objects containing the images in the album
     * @throws RuntimeException if there is a problem executing the request
     */
    public static List<SMImage> getAllImagesOfAlbum(OAuth1HttpClient client, String albumKey) throws RuntimeException {
        try {
            String path = API_CONFIG.hostName + API_CONFIG.path + albumKey + "!images";
            SMImagesResponse smImagesResponse = (SMImagesResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            SMPages pages = smImagesResponse.getPages();
            List<SMImage> result = new ArrayList<>(smImagesResponse.getImages());
            while (pages.getNextPage() != null) {
                path = API_CONFIG.hostName + pages.getNextPage();
                smImagesResponse = (SMImagesResponse) SmugMugHttpRequestBuilder
                        .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                        .execute();
                pages = smImagesResponse.getPages();
                result.addAll(smImagesResponse.getImages());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the album with the specified key and information.
     *
     * @param client the OAuth1 client to use when making the request
     * @param album  the album to update
     * @return the updated album
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMAlbum updateAlbum(OAuth1HttpClient client, SMAlbum album) {
        if(album == null || album.getUri() == null || album.getUri().isEmpty()) {
            throw new RuntimeException("album uri is null or empty");
        }
        try {
            String path = API_CONFIG.hostName + album.getUri();
            SMAlbumResponse smAlbumResponse = (SMAlbumResponse) SmugMugHttpRequestBuilder
                    .create(client, HttpPatch.METHOD_NAME, path, SMBaseResponse.class)
                    .header("accept", "application/json")
                    .body(new StringEntity(mapper.writeValueAsString(album)))
                    .execute();
            return smAlbumResponse.getAlbum();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
