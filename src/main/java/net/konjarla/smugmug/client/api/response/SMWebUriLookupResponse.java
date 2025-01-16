package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import net.konjarla.smugmug.model.SMAlbumImage;
import net.konjarla.smugmug.model.SMFolder;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMWebUriLookupResponse extends SMBaseResponse {
    @JsonProperty("AlbumImage")
    SMAlbumImage albumImage;
    @JsonProperty("Folder")
    SMFolder folder;
    @JsonProperty("Album")
    SMAlbumImage album;
}
