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

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SMUserURIs {
        @JsonProperty("BioImage")
        public SMUri bioImage;
        @JsonProperty("CoverImage")
        public SMUri coverImage;
        @JsonProperty("UserProfile")
        public SMUri userProfile;
        @JsonProperty("Node")
        public SMUri node;
        @JsonProperty("Folder")
        public SMUri folder;
        @JsonProperty("Features")
        public SMUri features;
        @JsonProperty("SiteSettings")
        public SMUri siteSettings;
        @JsonProperty("UserAlbums")
        public SMUri userAlbums;
        @JsonProperty("UserGeoMedia")
        public SMUri userGeoMedia;
        @JsonProperty("UserPopularMedia")
        public SMUri userPopularMedia;
        @JsonProperty("UserFeaturedAlbums")
        public SMUri userFeaturedAlbums;
        @JsonProperty("UserRecentImages")
        public SMUri userRecentImages;
        @JsonProperty("UserImageSearch")
        public SMUri userImageSearch;
        @JsonProperty("UserTopKeywords")
        public SMUri userTopKeywords;
        @JsonProperty("UrlPathLookup")
        public SMUri urlPathLookup;
    }
}