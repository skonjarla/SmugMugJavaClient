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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMFolder {
    @JsonProperty("Name")
    public String name;
    @JsonProperty("UrlName")
    public String urlName;
    @JsonProperty("SecurityType")
    public String securityType;
    @JsonProperty("SortMethod")
    public String sortMethod;
    @JsonProperty("SortDirection")
    public String sortDirection;
    @JsonProperty("Description")
    public String description;
    @JsonProperty("Keywords")
    public String keywords;
    @JsonProperty("PasswordHint")
    public String passwordHint;
    @JsonProperty("DateAdded")
    public Date dateAdded;
    @JsonProperty("DateModified")
    public Date dateModified;
    @JsonProperty("UrlPath")
    public String urlPath;
    @JsonProperty("NodeID")
    public String nodeID;
    @JsonProperty("IsEmpty")
    public boolean isEmpty;
    @JsonProperty("Uri")
    public String uri;
    @JsonProperty("WebUri")
    public String webUri;
    @JsonProperty("UriDescription")
    public String uriDescription;
    @JsonProperty("Uris")
    public SMFolderURIs uris;
    @JsonProperty("ResponseLevel")
    public String responseLevel;

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SMFolderURIs {
        @JsonProperty("FolderByID")
        public SMUri folderByID;
        @JsonProperty("Node")
        public SMUri node;
        @JsonProperty("User")
        public SMUri user;
        @JsonProperty("ParentFolders")
        public SMUri parentFolders;
        @JsonProperty("ParentFolder")
        public SMUri parentFolder;
        @JsonProperty("HighlightImage")
        public SMUri highlightImage;
        @JsonProperty("FolderHighlightImage")
        public SMUri folderHighlightImage;
        @JsonProperty("Folders")
        public SMUri folders;
        @JsonProperty("FolderList")
        public SMUri folderList;
        @JsonProperty("FolderAlbums")
        public SMUri folderAlbums;
        @JsonProperty("AlbumList")
        public SMUri albumList;
        @JsonProperty("FolderSearch")
        public SMUri folderSearch;
        @JsonProperty("FolderPages")
        public SMUri folderPages;
        @JsonProperty("Size")
        public SMUri size;
    }
}

