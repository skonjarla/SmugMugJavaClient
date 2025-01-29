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
@ToString
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SMAlbumImage {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Caption")
    private String caption;
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("KeywordArray")
    private ArrayList<String> keywordArray;
    @JsonProperty("Watermark")
    private String watermark;
    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("Longitude")
    private String longitude;
    @JsonProperty("Altitude")
    private int altitude;
    @JsonProperty("Hidden")
    private boolean hidden;
    @JsonProperty("ThumbnailUrl")
    private String thumbnailUrl;
    @JsonProperty("FileName")
    private String fileName;
    @JsonProperty("Processing")
    private boolean processing;
    @JsonProperty("UploadKey")
    private String uploadKey;
    @JsonProperty("Date")
    private Date date;
    @JsonProperty("DateTimeUploaded")
    private Date dateTimeUploaded;
    @JsonProperty("DateTimeOriginal")
    private Date dateTimeOriginal;
    @JsonProperty("Format")
    private String format;
    @JsonProperty("OriginalHeight")
    private int originalHeight;
    @JsonProperty("OriginalWidth")
    private int originalWidth;
    @JsonProperty("OriginalSize")
    private int originalSize;
    @JsonProperty("LastUpdated")
    private Date lastUpdated;
    @JsonProperty("Collectable")
    private boolean collectable;
    @JsonProperty("IsArchive")
    private boolean isArchive;
    @JsonProperty("IsVideo")
    private boolean isVideo;
    @JsonProperty("ComponentFileTypes")
    private ComponentFileTypes componentFileTypes;
    @JsonProperty("CanEdit")
    private boolean canEdit;
    @JsonProperty("CanBuy")
    private boolean canBuy;
    @JsonProperty("Protected")
    private boolean isProtected;
    @JsonProperty("EZProject")
    private boolean eZProject;
    @JsonProperty("Watermarked")
    private boolean watermarked;
    @JsonProperty("ImageKey")
    private String imageKey;
    @JsonProperty("Serial")
    private int serial;
    @JsonProperty("ArchivedUri")
    private String archivedUri;
    @JsonProperty("ArchivedSize")
    private int archivedSize;
    @JsonProperty("ArchivedMD5")
    private String archivedMD5;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("SubStatus")
    private String subStatus;
    @JsonProperty("CanShare")
    private boolean canShare;
    @JsonProperty("Comments")
    private boolean comments;
    @JsonProperty("ShowKeywords")
    private boolean showKeywords;
    @JsonProperty("FormattedValues")
    private FormattedValues formattedValues;
    @JsonProperty("PreferredDisplayFileExtension")
    private String preferredDisplayFileExtension;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("WebUri")
    private String webUri;
    @JsonProperty("UriDescription")
    private String uriDescription;
    @JsonProperty("Uris")
    private Uris uris;
    @JsonProperty("AlbumKey")
    private String albumKey;
    @JsonProperty("Movable")
    private boolean movable;
    @JsonProperty("Origin")
    private String origin;

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ComponentFileTypes {
        @JsonProperty("Image")
        private ArrayList<String> image;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Caption {
        private String html;
        private String text;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class FileName {
        private String html;
        private String text;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class FormattedValues {
        @JsonProperty("Caption")
        private Caption caption;
        @JsonProperty("FileName")
        private FileName fileName;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Uris {
        @JsonProperty("Components")
        private SMUri components;
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
        @JsonProperty("ImageOwner")
        private SMUri imageOwner;
        @JsonProperty("ImageAlbums")
        private SMUri imageAlbums;
        @JsonProperty("ImageDownload")
        private SMUri imageDownload;
        @JsonProperty("RotateImage")
        private SMUri rotateImage;
        @JsonProperty("ColorImage")
        private SMUri colorImage;
        @JsonProperty("CopyImage")
        private SMUri copyImage;
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
        @JsonProperty("AlbumImagePricelistExclusions")
        private SMUri albumImagePricelistExclusions;
        @JsonProperty("AlbumImageMetadata")
        private SMUri albumImageMetadata;
        @JsonProperty("AlbumImageShareUris")
        private SMUri albumImageShareUris;
        @JsonProperty("AlbumImageDownload")
        private SMUri albumImageDownload;
    }
}
