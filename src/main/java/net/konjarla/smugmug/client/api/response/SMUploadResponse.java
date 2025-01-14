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
    String stat;
    @JsonProperty("method")
    String method;
    @JsonProperty("Image")
    UploadResponseImage image;
    @JsonProperty("Asset")
    Asset asset;
    @JsonProperty("code")
    Integer code;
    @JsonProperty("message")
    String message;
}
