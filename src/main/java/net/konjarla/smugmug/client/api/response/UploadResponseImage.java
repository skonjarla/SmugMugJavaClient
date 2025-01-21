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
public class UploadResponseImage {
    @JsonProperty("StatusImageReplaceUri")
    private String statusImageReplaceUri;
    @JsonProperty("ImageUri")
    private String imageUri;
    @JsonProperty("AlbumImageUri")
    private String albumImageUri;
    @JsonProperty("URL")
    private String uRL;
}
