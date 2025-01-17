package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import net.konjarla.smugmug.model.SMAlbum;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(fluent = false, chain = true)
@JsonTypeName("Album")
public class SMAlbumResponse extends SMBaseResponse {
    @JsonProperty("Album")
    private SMAlbum album;
}
