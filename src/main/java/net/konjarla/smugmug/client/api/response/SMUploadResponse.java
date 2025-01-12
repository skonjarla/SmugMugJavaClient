package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMUploadResponse {
    @JsonProperty("stat")
    String stat;
    @JsonProperty("method")
    String method;
    @JsonProperty("Image")
    UploadResponseImage image;
    @JsonProperty("Asset")
    Asset asset;
}
