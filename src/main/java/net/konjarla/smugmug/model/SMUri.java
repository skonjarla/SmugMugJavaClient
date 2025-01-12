package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMUri {
    @JsonProperty("Uri")
    String uri;
    @JsonProperty("Locator")
    String locator;
    @JsonProperty("LocatorType")
    String locatorType;
    @JsonProperty("UriDescription")
    String uriDescription;
    @JsonProperty("EndpointType")
    String endPointType;
}
