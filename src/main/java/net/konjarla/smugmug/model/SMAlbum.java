package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMAlbum {
    @JsonProperty("NiceName")
    private String niceName;
    @JsonProperty("UrlName")
    private String urlName;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("AllowDownloads")
    private Boolean allowDownloads;
    @JsonProperty("Backprinting")
    private String backprinting;
    @JsonProperty("BoutiquePackaging")
    private String boutiquePackaging;
    @JsonProperty("CanRank")
    private Boolean canRank;
    @JsonProperty("Clean")
    private Boolean clean;
    @JsonProperty("Comments")
    private Boolean comments;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("EXIF")
    private Boolean exif;
    @JsonProperty("External")
    private Boolean external;
    @JsonProperty("FamilyEdit")
    private Boolean familyEdit;
    @JsonProperty("Filenames")
    private Boolean fileNames;
    @JsonProperty("FriendEdit")
    private Boolean friendEdit;
    @JsonProperty("Geography")
    private Boolean geography;
    @JsonProperty("Header")
    private String header;
    @JsonProperty("HideOwner")
    private Boolean hideOwner;
    @JsonProperty("InterceptShipping")
    private String interceptShipping;
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("LargestSize")
    private String largestSize;
    @JsonProperty("PackagingBranding")
    private Boolean packagingBranding;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("PasswordHint")
    private String passwordHint;
    @JsonProperty("Printable")
    private Boolean printable;
    @JsonProperty("Privacy")
    private String privacy;
    @JsonProperty("ProofDays")
    private Integer proofDays;
    @JsonProperty("Protected")
    private Boolean isProtected;
    @JsonProperty("Share")
    private Boolean share;
    @JsonProperty("Slideshow")
    private Boolean slideShow;
    @JsonProperty("SmugSearchable")
    private String smugSearchable;
    @JsonProperty("SortDirection")
    private String sortDirection;
    @JsonProperty("SortMethod")
    private String sortMethod;
    @JsonProperty("SquareThumbs")
    private Boolean squareThumbs;
    @JsonProperty("Watermark")
    private Boolean watermark;
    @JsonProperty("WorldSearchable")
    private Boolean worldSearchable;
    @JsonProperty("SecurityType")
    private String securityType;
    @JsonProperty("CommerceLightbox")
    private Boolean commerceLightbox;
    @JsonProperty("AlbumKey")
    private String albumKey;
    @JsonProperty("CanBuy")
    private Boolean canBuy;
    @JsonProperty("CanFavorite")
    private Boolean canFavorite;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("LastUpdated")
    private String lastUpdated;
    @JsonProperty("ImagesLastUpdated")
    private String imagesLastUpdated;
    @JsonProperty("NodeID")
    private String nodeId;
    @JsonProperty("OriginalSizes")
    private Long originalSizes;
    @JsonProperty("TotalSizes")
    private Long totalSizes;
    @JsonProperty("ImageCount")
    private Integer imageCount;
    @JsonProperty("UrlPath")
    private String urlPath;
    @JsonProperty("CanShare")
    private Boolean canShare;
    @JsonProperty("HasDownloadPassword")
    private Boolean hasDownloadPassword;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("WebUri")
    private String webUri;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("Uris")
    private SMAlbumURIs uris;

    @Data
    @Builder
    @ToString
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SMAlbumURIs {
        @JsonProperty("AlbumShareUris")
        private SMUri albumShareUris;
        @JsonProperty("Node")
        private SMUri node;
        @JsonProperty("NodeCoverImage")
        private SMUri nodeCoverImage;
        @JsonProperty("User")
        private SMUri user;
        @JsonProperty("Folder")
        private SMUri folder;
        @JsonProperty("ParentFolders")
        private SMUri parentFolders;
        @JsonProperty("HighlightImage")
        private SMUri highlightImage;
        @JsonProperty("AlbumHighlightImage")
        private SMUri albumHighlightImage;
        @JsonProperty("AlbumImages")
        private SMUri albumImages;
        @JsonProperty("AlbumPopularMedia")
        private SMUri albumPopularMedia;
        @JsonProperty("AlbumGeoMedia")
        private SMUri albumGeoMedia;
        @JsonProperty("MoveAlbumImages")
        private SMUri moveAlbumImages;
        @JsonProperty("CollectImages")
        private SMUri collectImages;
        @JsonProperty("ApplyAlbumTemplate")
        private SMUri applyAlbumTemplate;
        @JsonProperty("DeleteAlbumImages")
        private SMUri deleteAlbumImages;
        @JsonProperty("UploadFromUri")
        private SMUri uploadFromUri;
        @JsonProperty("AlbumDownload")
        private SMUri albumDownload;
        @JsonProperty("AlbumPrices")
        private SMUri albumPrices;
        @JsonProperty("AlbumPricelistExclusions")
        private SMUri albumPricelistExclusions;
    }
}
