package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMUploadResponse {
    @JsonProperty("stat")
    private String stat;
    @JsonProperty("method")
    private String method;
    @JsonProperty("Image")
    private UploadResponseImage image;
    @JsonProperty("Asset")
    private Asset asset;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;

    @Getter
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Asset{
        @JsonProperty("AssetComponentUri")
        private String assetComponentUri;
        @JsonProperty("AssetUri")
        private String assetUri;
    }
}
