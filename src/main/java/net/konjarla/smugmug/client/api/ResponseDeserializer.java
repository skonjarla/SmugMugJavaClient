package net.konjarla.smugmug.client.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import net.konjarla.smugmug.client.api.response.*;

import java.io.IOException;

public class ResponseDeserializer<T extends SMBaseResponse> extends JsonDeserializer<SMBaseResponse> {
    /**
     * Deserializes a SmugMug response into a {@link SMBaseResponse}. The actual
     * type of response is determined by the "EndpointType" field in the JSON.
     * If this field is not present, the deserializer will make a best guess
     * based on the fields present in the JSON.
     *
     * @param p the JSON parser
     * @param ctxt the deserialization context
     * @return the deserialized response
     * @throws IOException if the deserialization fails
     */
    @Override
    public SMBaseResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode responseNode = node.get("Response");
        String type;
        if (responseNode != null) {
            type = responseNode.get("EndpointType").asText();
        } else {
            type = node.get("EndpointType").asText();
            responseNode = node;
        }
        if ("Album".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMAlbumResponse.class);
        } else if ("Image".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMImageResponse.class);
        } else if ("ImageMetadata".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMImageMetadataResponse.class);
        } else if ("AlbumImages".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMImagesResponse.class);
        } else if ("User".equals(type) || "AuthUser".equals(type) || "SiteUser".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMUserResponse.class);
        } else if ("Node".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMNodeResponse.class);
        } else if ("ChildNodes".equals(type)) {
            return p.getCodec().treeToValue(responseNode, SMNodesResponse.class);
        } else {
            // Handle unknown type
            return null;
        }
    }
}
