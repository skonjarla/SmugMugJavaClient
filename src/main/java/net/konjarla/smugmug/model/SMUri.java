package net.konjarla.smugmug.model;

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
public class SMUri {
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("Locator")
    private String locator;
    @JsonProperty("LocatorType")
    private String locatorType;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("EndpointType")
    private String endPointType;
}
