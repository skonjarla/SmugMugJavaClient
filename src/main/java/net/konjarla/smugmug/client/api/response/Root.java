package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Root {
    @JsonProperty("Code")
    int code;
    @JsonProperty("Message")
    String message;
    @JsonProperty("Response")
    SMBaseResponse response;
}
