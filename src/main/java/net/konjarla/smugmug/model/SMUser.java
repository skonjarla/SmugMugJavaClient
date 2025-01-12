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
public class SMUser {
    @JsonProperty("NickName")
    private String nickName;
    @JsonProperty("ViewPassHint")
    private String viewPassHint;
    @JsonProperty("RefTag")
    private String refTag;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("QuickShare")
    private boolean quickShare;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("WebUri")
    private String webUri;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("Uris")
    private SMUserURIs uris;
}