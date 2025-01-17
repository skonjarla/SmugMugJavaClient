package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMImage {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Caption")
    private String caption;
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("KeywordArray")
    private List<String> keywordArray;
    @JsonProperty("Watermark")
    private String watermark;
    @JsonProperty("Latitude")
    private Double latitude;
    @JsonProperty("Longitude")
    private Double longitude;
    @JsonProperty("Altitude")
    private Double altitude;
    @JsonProperty("Hidden")
    private Boolean hidden;
    @JsonProperty("ThumbnailUrl")
    private String thumbnailUrl;
    @JsonProperty("FileName")
    private String fileName;
    @JsonProperty("Processing")
    private Boolean processing;
    @JsonProperty("UploadKey")
    private String uploadKey;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Format")
    private String format;
    @JsonProperty("OriginalHeight")
    private Integer originalHeight;
    @JsonProperty("OriginalWidth")
    private Integer originalWidth;
    @JsonProperty("LastUpdated")
    private String lastUpdated;
    @JsonProperty("Collectable")
    private String collectable;
    @JsonProperty("IsArchive")
    private Boolean isArchive;
    @JsonProperty("IsVideo")
    private Boolean isVideo;
    @JsonProperty("CanEdit")
    private Boolean canEdit;
    @JsonProperty("CanBuy")
    private Boolean canBuy;
    @JsonProperty("Protected")
    private Boolean isProtected;
    @JsonProperty("Watermarked")
    private Boolean watermarked;
    @JsonProperty("ImageKey")
    private String imageKey;
    @JsonProperty("ArchivedUri")
    private String archivedUri;
    @JsonProperty("ArchivedSize")
    private Integer archivedSize;
    @JsonProperty("ArchivedMD5")
    private String archivedMD5;
    @JsonProperty("CanShare")
    private Boolean canShare;
    @JsonProperty("Comments")
    private Boolean comments;
    @JsonProperty("ShowKeywords")
    private Boolean showKeywords;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("WebUri")
    private String webUri;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("Uris")
    private SMImageURIs uris;
    @JsonProperty("Movable")
    private Boolean movable;
    @JsonProperty("Origin")
    private String origin;

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SMImageURIs {
        @JsonProperty("LargestImage")
        private SMUri largestImage;
        @JsonProperty("ImageSizes")
        private SMUri imageSizes;
        @JsonProperty("ImageSizeDetails")
        private SMUri imageSizeDetails;
        @JsonProperty("PointOfInterest")
        private SMUri pointOfInterest;
        @JsonProperty("PointOfInterestCrops")
        private SMUri pointOfInterestCrops;
        @JsonProperty("Regions")
        private SMUri regions;
        @JsonProperty("ImageAlbum")
        private SMUri imageAlbum;
        @JsonProperty("ImageDownload")
        private SMUri imageDownload;
        @JsonProperty("ImageOwner")
        private SMUri imageOwner;
        @JsonProperty("RotateImage")
        private SMUri rotateImage;
        @JsonProperty("ColorImage")
        private SMUri colorImage;
        @JsonProperty("CropImage")
        private SMUri cropImage;
        @JsonProperty("ImageMetadata")
        private SMUri imageMetadata;
        @JsonProperty("ImagePrices")
        private SMUri imagePrices;
        @JsonProperty("ImagePricelistExclusions")
        private SMUri imagePricelistExclusions;
        @JsonProperty("Album")
        private SMUri album;
        @JsonProperty("Image")
        private SMUri image;
        @JsonProperty("AlbumImageShareUris")
        private SMUri albumImageShareUris;
    }
}
