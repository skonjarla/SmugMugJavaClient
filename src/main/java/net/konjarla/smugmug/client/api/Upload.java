package net.konjarla.smugmug.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMUploadResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Upload {
    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig().requestVerb("POST");
    private static final Logger log = LoggerFactory.getLogger(Upload.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private Upload() {
    }

    /**
     * Uploads a file to an album, given the path to the file to upload and the
     * key of the album to upload to.
     *
     * @param client   the OAuth client to use
     * @param fileName the file to upload
     * @param albumKey the key of the album to upload to
     * @return the response from the server
     */
    public static SMUploadResponse uploadFileToAlbum(OAuth1HttpClient client, String fileName, String albumKey) {
        if (fileName == null || fileName.isEmpty() || albumKey == null || albumKey.isEmpty()) {
            throw new IllegalArgumentException("fileName and albumKey must not be null or empty");
        }
        FileBody fileBody = new FileBody(new File(fileName));
        FileEntity entityToSend = new FileEntity(fileBody.getFile(), fileBody.getContentType());
        try {
            return SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, API_CONFIG.uploadHost, SMUploadResponse.class)
                    .body(entityToSend)
                    .header("X-Smug-AlbumUri", "/api/v2/album/" + albumKey)
                    .header("X-Smug-ResponseType", "JSON")
                    .header("X-Smug-Version", "v2")
                    .header("X-Smug-FileName", fileBody.getFilename())
                    .header("Content-Type", fileBody.getContentType().getMimeType())
                    .header("Content-MD5", md5Checksum(fileName))
                    .executeRaw(rawResponse -> {
                        String entity = EntityUtils.toString(rawResponse.getEntity(), StandardCharsets.UTF_8);
                        return mapper.readValue(entity, SMUploadResponse.class);
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Uploads an image file to a specified album on SmugMug with optional metadata.
     *
     * @param client        the OAuth1HttpClient used for authenticated requests
     * @param imageToUpload the image to upload, encapsulating file name, album key, and optional metadata
     * @return the response from the server as a SMUploadResponse object
     * @throws RuntimeException if an error occurs during the upload process
     */
    public static SMUploadResponse uploadFileToAlbum(OAuth1HttpClient client, ImageToUpload imageToUpload) {
        String fileName = imageToUpload.getFileName();
        String albumKey = imageToUpload.getAlbumKey();
        if (fileName.isEmpty() || albumKey.isEmpty()) {
            throw new IllegalArgumentException("fileName and albumKey must not be null or empty");
        }
        FileBody fileBody = new FileBody(new File(fileName));
        FileEntity entityToSend = new FileEntity(fileBody.getFile(), fileBody.getContentType());
        try {
            SmugMugHttpRequestBuilder<SMUploadResponse> builder = SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, API_CONFIG.uploadHost, SMUploadResponse.class)
                    .body(entityToSend)
                    .header("X-Smug-AlbumUri", "/api/v2/album/" + albumKey)
                    .header("X-Smug-ResponseType", "JSON")
                    .header("X-Smug-Version", "v2")
                    .header("X-Smug-FileName", fileBody.getFilename())
                    .header("Content-Type", fileBody.getContentType().getMimeType())
                    .header("Content-MD5", md5Checksum(fileName));

            if (imageToUpload.getCaption() != null) {
                builder.header("X-Smug-Caption", imageToUpload.getCaption());
            }
            if (imageToUpload.getLatitude() != null) {
                builder.header("X-Smug-Latitude", imageToUpload.getLatitude());
            }
            if (imageToUpload.getLongitude() != null) {
                builder.header("X-Smug-Longitude", imageToUpload.getLongitude());
            }
            if (imageToUpload.getAltitude() != null) {
                builder.header("X-Smug-Altitude", imageToUpload.getAltitude());
            }
            if (imageToUpload.getTitle() != null) {
                builder.header("X-Smug-Title", imageToUpload.getTitle());
            }
            if (imageToUpload.getKeywords() != null) {
                builder.header("X-Smug-Keywords", imageToUpload.getKeywords());
            }

            return builder.executeRaw(rawResponse -> {
                String entity = EntityUtils.toString(rawResponse.getEntity(), StandardCharsets.UTF_8);
                SMUploadResponse response = mapper.readValue(entity, SMUploadResponse.class);
                if (response.getStat().equals("fail")) {
                    throw new RuntimeException(String.valueOf(response));
                }
                return response;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Computes the MD5 checksum of a file.
     *
     * @param fileName the name of the file for which to compute the checksum
     * @return the MD5 checksum as a hexadecimal string
     * @throws RuntimeException if an I/O error occurs
     */
    private static String md5Checksum(String fileName) {
        try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
            return DigestUtils.md5Hex(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}