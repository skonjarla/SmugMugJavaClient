package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Getter
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SMFolder {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("UrlName")
    private String urlName;
    @JsonProperty("SecurityType")
    private String securityType;
    @JsonProperty("SortMethod")
    private String sortMethod;
    @JsonProperty("SortDirection")
    private String sortDirection;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("PasswordHint")
    private String passwordHint;
    @JsonProperty("DateAdded")
    private Date dateAdded;
    @JsonProperty("DateModified")
    private Date dateModified;
    @JsonProperty("UrlPath")
    private String urlPath;
    @JsonProperty("NodeID")
    private String nodeID;
    @JsonProperty("IsEmpty")
    private boolean isEmpty;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("WebUri")
    private String webUri;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("Uris")
    private SMFolderURIs uris;
    @JsonProperty("ResponseLevel")
    private String responseLevel;

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class SMFolderURIs {
        @JsonProperty("FolderByID")
        private SMUri folderByID;
        @JsonProperty("Node")
        private SMUri node;
        @JsonProperty("User")
        private SMUri user;
        @JsonProperty("ParentFolders")
        private SMUri parentFolders;
        @JsonProperty("ParentFolder")
        private SMUri parentFolder;
        @JsonProperty("HighlightImage")
        private SMUri highlightImage;
        @JsonProperty("FolderHighlightImage")
        private SMUri folderHighlightImage;
        @JsonProperty("Folders")
        private SMUri folders;
        @JsonProperty("FolderList")
        private SMUri folderList;
        @JsonProperty("FolderAlbums")
        private SMUri folderAlbums;
        @JsonProperty("AlbumList")
        private SMUri albumList;
        @JsonProperty("FolderSearch")
        private SMUri folderSearch;
        @JsonProperty("FolderPages")
        private SMUri folderPages;
        @JsonProperty("Size")
        private SMUri size;
    }
}

