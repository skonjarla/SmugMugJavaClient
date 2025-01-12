package net.konjarla.smugmug.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMUploadResponse;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

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
        FileBody fileBody = new FileBody(new File(fileName));
        FileEntity entityToSend = new FileEntity(fileBody.getFile(), fileBody.getContentType());
        try {
            byte[] data = Files.readAllBytes(Paths.get(fileName));
            byte[] hash = MessageDigest.getInstance("MD5").digest(data);
            String checksum = new BigInteger(1, hash).toString(16);
            return SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, API_CONFIG.uploadHost, SMUploadResponse.class)
                    .body(entityToSend)
                    .header("X-Smug-AlbumUri", "/api/v2/album/" + albumKey)
                    .header("X-Smug-ResponseType", "JSON")
                    .header("X-Smug-Version", "v2")
                    .header("X-Smug-FileName", fileBody.getFilename())
                    .header("Content-Type", fileBody.getContentType().getMimeType())
                    .header("Content-MD5", checksum)
                    .executeRaw(rawResponse -> {
                        String entity = EntityUtils.toString(rawResponse.getEntity(), StandardCharsets.UTF_8);
                        return mapper.readValue(entity, SMUploadResponse.class);
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}