package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.konjarla.smugmug.model.SMPages;

//@EqualsAndHashCode(callSuper = true)
//@Data
@Getter
@SuperBuilder
//@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "Response.EndPointType", visible = true)
//@JsonSubTypes({
//       @JsonSubTypes.Type(value = SMAlbumResponse.class, name = "Album"),
       // @JsonSubTypes.Type(value = RepairOrder.class, name = "REPAIR"),
        //@JsonSubTypes.Type(value = ReplacementOrder.class, name = "REPLACEMENT")
//})
public abstract class SMBaseResponse {
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
    @JsonProperty("DocUri")
    String docUri;
    @JsonProperty("Pages")
    SMPages pages;
}
