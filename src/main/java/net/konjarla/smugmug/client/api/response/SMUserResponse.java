package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import net.konjarla.smugmug.model.SMUser;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(fluent = false, chain = true)
public class SMUserResponse extends SMBaseResponse {
    @JsonProperty("User")
    SMUser user;
}
