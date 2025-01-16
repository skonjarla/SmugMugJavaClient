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
    String niceName;
    @JsonProperty("UrlName")
    String urlName;
    @JsonProperty("Title")
    String title;
    @JsonProperty("Name")
    String name;
    @JsonProperty("AllowDownloads")
    Boolean allowDownloads;
    @JsonProperty("Backprinting")
    String backprinting;
    @JsonProperty("BoutiquePackaging")
    String boutiquePackaging;
    @JsonProperty("CanRank")
    Boolean canRank;
    @JsonProperty("Clean")
    Boolean clean;
    @JsonProperty("Comments")
    Boolean comments;
    @JsonProperty("Description")
    String description;
    @JsonProperty("EXIF")
    Boolean exif;
    @JsonProperty("External")
    Boolean external;
    @JsonProperty("FamilyEdit")
    Boolean familyEdit;
    @JsonProperty("Filenames")
    Boolean fileNames;
    @JsonProperty("FriendEdit")
    Boolean friendEdit;
    @JsonProperty("Geography")
    Boolean geography;
    @JsonProperty("Header")
    String header;
    @JsonProperty("HideOwner")
    Boolean hideOwner;
    @JsonProperty("InterceptShipping")
    String interceptShipping;
    @JsonProperty("Keywords")
    String keywords;
    @JsonProperty("LargestSize")
    String largestSize;
    @JsonProperty("PackagingBranding")
    Boolean packagingBranding;
    @JsonProperty("Password")
    String password;
    @JsonProperty("PasswordHint")
    String passwordHint;
    @JsonProperty("Printable")
    Boolean printable;
    @JsonProperty("Privacy")
    String privacy;
    @JsonProperty("ProofDays")
    Integer proofDays;
    @JsonProperty("Protected")
    Boolean isProtected;
    @JsonProperty("Share")
    Boolean share;
    @JsonProperty("Slideshow")
    Boolean slideShow;
    @JsonProperty("SmugSearchable")
    String smugSearchable;
    @JsonProperty("SortDirection")
    String sortDirection;
    @JsonProperty("SortMethod")
    String sortMethod;
    @JsonProperty("SquareThumbs")
    Boolean squareThumbs;
    @JsonProperty("Watermark")
    Boolean watermark;
    @JsonProperty("WorldSearchable")
    Boolean worldSearchable;
    @JsonProperty("SecurityType")
    String securityType;
    @JsonProperty("CommerceLightbox")
    Boolean commerceLightbox;
    @JsonProperty("AlbumKey")
    String albumKey;
    @JsonProperty("CanBuy")
    Boolean canBuy;
    @JsonProperty("CanFavorite")
    Boolean canFavorite;
    @JsonProperty("Date")
    String date;
    @JsonProperty("LastUpdated")
    String lastUpdated;
    @JsonProperty("ImagesLastUpdated")
    String imagesLastUpdated;
    @JsonProperty("NodeID")
    String nodeId;
    @JsonProperty("OriginalSizes")
    Long originalSizes;
    @JsonProperty("TotalSizes")
    Long totalSizes;
    @JsonProperty("ImageCount")
    Integer imageCount;
    @JsonProperty("UrlPath")
    String urlPath;
    @JsonProperty("CanShare")
    Boolean canShare;
    @JsonProperty("HasDownloadPassword")
    Boolean hasDownloadPassword;
    @JsonProperty("Uri")
    String uri;
    @JsonProperty("WebUri")
    String webUri;
    @JsonProperty("UriDescription")
    String uriDescription;
    @JsonProperty("Uris")
    SMAlbumURIs uris;

    @Data
    @Builder
    @ToString
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SMAlbumURIs {
        @JsonProperty("AlbumShareUris")
        SMUri albumShareUris;
        @JsonProperty("Node")
        SMUri node;
        @JsonProperty("NodeCoverImage")
        SMUri nodeCoverImage;
        @JsonProperty("User")
        SMUri user;
        @JsonProperty("Folder")
        SMUri folder;
        @JsonProperty("ParentFolders")
        SMUri parentFolders;
        @JsonProperty("HighlightImage")
        SMUri highlightImage;
        @JsonProperty("AlbumHighlightImage")
        SMUri albumHighlightImage;
        @JsonProperty("AlbumImages")
        SMUri albumImages;
        @JsonProperty("AlbumPopularMedia")
        SMUri albumPopularMedia;
        @JsonProperty("AlbumGeoMedia")
        SMUri albumGeoMedia;
        @JsonProperty("MoveAlbumImages")
        SMUri moveAlbumImages;
        @JsonProperty("CollectImages")
        SMUri collectImages;
        @JsonProperty("ApplyAlbumTemplate")
        SMUri applyAlbumTemplate;
        @JsonProperty("DeleteAlbumImages")
        SMUri deleteAlbumImages;
        @JsonProperty("UploadFromUri")
        SMUri uploadFromUri;
        @JsonProperty("AlbumDownload")
        SMUri albumDownload;
        @JsonProperty("AlbumPrices")
        SMUri albumPrices;
        @JsonProperty("AlbumPricelistExclusions")
        SMUri albumPricelistExclusions;
    }
}
