package net.konjarla.smugmug.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMAlbumResponse;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMImagesResponse;
import net.konjarla.smugmug.client.api.response.SMNodeResponse;
import net.konjarla.smugmug.model.SMAlbum;
import net.konjarla.smugmug.model.SMImage;
import net.konjarla.smugmug.model.SMNode;
import net.konjarla.smugmug.model.SMPages;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
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
        if(albumKey == null || albumKey.isEmpty()) {
            throw new RuntimeException("album key is null or empty");
        }
        if(client == null) {
            throw new RuntimeException("client is null");
        }
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
     * Retrieves the images for the given album key.
     *
     * @param client OAuth1HttpClient used to make authenticated requests
     * @param albumKey    the key of the album to retrieve images from
     * @return SMImagesResponse object containing information about the album's images
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMImagesResponse getImagesOfAlbum(OAuth1HttpClient client, String albumKey) throws RuntimeException {
        if(albumKey == null || albumKey.isEmpty()) {
            throw new RuntimeException("album key is null or empty");
        }
        if(client == null) {
            throw new RuntimeException("client is null");
        }
        String uri = API_CONFIG.path + albumKey + "!images";
        return getImagesOfAlbumByUri(client, uri);
    }

    /**
     * Retrieves the images for the given album URI.
     *
     * @param client OAuth1HttpClient used to make authenticated requests
     * @param uri    the URI of the album to retrieve images from
     * @return SMImagesResponse object containing information about the album's images
     * @throws RuntimeException if there is a problem executing the request
     */
    public static SMImagesResponse getImagesOfAlbumByUri(OAuth1HttpClient client, String uri) throws RuntimeException {
        if(uri == null || uri.isEmpty()) {
            throw new RuntimeException("uri is null or empty");
        }
        if(client == null) {
            throw new RuntimeException("client is null");
        }
        try {
            String path = API_CONFIG.hostName + uri;
            return (SMImagesResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all images from the specified album URI.  If the album contains more than one page of images,
     * this method will make multiple requests to retrieve all images.
     *
     * @param client OAuth1HttpClient used to make authenticated requests
     * @param uri    the URI of the album to retrieve images from
     * @return List of SMImage objects containing the images in the album
     * @throws RuntimeException if there is a problem executing the request
     */
    public static List<SMImage> getAllImagesOfAlbumByUri(OAuth1HttpClient client, String uri) throws RuntimeException {
        if(uri == null || uri.isEmpty()) {
            throw new RuntimeException("uri is null or empty");
        }
        if(client == null) {
            throw new RuntimeException("client is null");
        }
        try {
            String path = API_CONFIG.hostName + uri;
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
     * Retrieves all images from the specified album.
     *
     * @param client OAuth1HttpClient used to make authenticated requests
     * @param albumKey    the album key used to identify the album
     * @return List of SMImage objects containing the images in the album
     * @throws RuntimeException if there is a problem executing the request
     */
    public static List<SMImage> getAllImagesOfAlbum(OAuth1HttpClient client, String albumKey) throws RuntimeException {
        if(albumKey == null || albumKey.isEmpty()) {
            throw new RuntimeException("album key is null or empty");
        }
        if(client == null) {
            throw new RuntimeException("client is null");
        }
        String uri = API_CONFIG.path + albumKey + "!images";
        return getAllImagesOfAlbumByUri(client, uri);
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
        if(client == null) {
            throw new RuntimeException("client is null");
        }
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

    /**
     * Adds a new album to the specified folder.
     *
     * @param client  the OAuth1HttpClient used to make authenticated requests
     * @param folderId the ID of the folder to which the album will be added
     * @param album   the SMAlbum object containing the album details
     * @return SMNode object representing the newly created album node
     * @throws RuntimeException if the client, folderId, or album is null or empty,
     *                          or if there is a problem executing the request
     */
    public static SMNode addAlbum(OAuth1HttpClient client, String folderId, SMAlbum album) {
        if(client == null) {
            throw new RuntimeException("client is null");
        }
        if(folderId == null || folderId.isEmpty()) {
            throw new RuntimeException("folderId is null or empty");
        }
        if(album == null) {
            throw new RuntimeException("album uri is null or empty");
        }
        try {
            String path = "/api/v2/node/" + folderId + "!children";
            return addAlbumByUri(client, path, album);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new album to the specified URI.
     *
     * The URI specified should be the full path to the node to which the album will be added.
     *
     * @param client  the OAuth1HttpClient used to make authenticated requests
     * @param uri     the full path to the node to which the album will be added
     * @param album   the SMAlbum object containing the album details
     * @return SMNode object representing the newly created album node
     * @throws RuntimeException if the client, uri, or album is null or empty,
     *                          or if there is a problem executing the request
     */
    public static SMNode addAlbumByUri(OAuth1HttpClient client, String uri, SMAlbum album) {
        if(client == null) {
            throw new RuntimeException("client is null");
        }
        if(uri == null || uri.isEmpty()) {
            throw new RuntimeException("folderId is null or empty");
        }
        if(album == null) {
            throw new RuntimeException("album uri is null or empty");
        }
        try {
            String path = API_CONFIG.hostName + uri;
            ObjectNode addAlbumPayload = mapper.createObjectNode();
            addAlbumPayload.put("Type","Album");
            if(album.getName() != null) {
                addAlbumPayload.put("Name", album.getName());
            }
            if(album.getDescription() != null) {
                addAlbumPayload.put("Description", album.getDescription());
            }
            if(album.getPrivacy() != null) {
                addAlbumPayload.put("Privacy", album.getPrivacy());
            }
            if(album.getUrlName() != null) {
                addAlbumPayload.put("UrlName", album.getUrlName());
            }
            if(album.getPasswordHint() != null) {
                addAlbumPayload.put("PasswordHint", album.getPasswordHint());
            }
            if(album.getSmugSearchable() != null) {
                addAlbumPayload.put("SmugSearchable", album.getSmugSearchable());
            }
            if(album.getWorldSearchable() != null) {
                addAlbumPayload.put("WorldSearchable", album.getWorldSearchable());
            }

            SMNodeResponse smNodeResponse = (SMNodeResponse) SmugMugHttpRequestBuilder
                    .create(client, HttpPost.METHOD_NAME, path, SMBaseResponse.class)
                    .header("accept", "application/json")
                    .body(new StringEntity(mapper.writeValueAsString(addAlbumPayload)))
                    .execute();

            return smNodeResponse.getNode();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}