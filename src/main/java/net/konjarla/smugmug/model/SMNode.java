package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMNode {
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Keywords")
    private ArrayList<Object> keywords;
    @JsonProperty("PasswordHint")
    private String passwordHint;
    @JsonProperty("SecurityType")
    private String securityType;
    @JsonProperty("ShowCoverImage")
    private boolean showCoverImage;
    @JsonProperty("SortDirection")
    private String sortDirection;
    @JsonProperty("SortMethod")
    private String sortMethod;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("UrlName")
    private String urlName;
    @JsonProperty("DateAdded")
    private Date dateAdded;
    @JsonProperty("DateModified")
    private Date dateModified;
    @JsonProperty("EffectiveSecurityType")
    private String effectiveSecurityType;
    @JsonProperty("HasChildren")
    private boolean hasChildren;
    @JsonProperty("IsRoot")
    private boolean isRoot;
    @JsonProperty("NodeID")
    private String nodeID;
    @JsonProperty("SortIndex")
    private int sortIndex;
    @JsonProperty("UrlPath")
    private String urlPath;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("WebUri")
    private String webUri;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("Uris")
    private SMNodeURIs uris;
}
