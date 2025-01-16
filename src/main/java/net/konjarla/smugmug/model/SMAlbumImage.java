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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMAlbumImage {
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Caption")
    public String caption;
    @JsonProperty("Keywords")
    public String keywords;
    @JsonProperty("KeywordArray")
    public ArrayList<String> keywordArray;
    @JsonProperty("Watermark")
    public String watermark;
    @JsonProperty("Latitude")
    public String latitude;
    @JsonProperty("Longitude")
    public String longitude;
    @JsonProperty("Altitude")
    public int altitude;
    @JsonProperty("Hidden")
    public boolean hidden;
    @JsonProperty("ThumbnailUrl")
    public String thumbnailUrl;
    @JsonProperty("FileName")
    public String fileName;
    @JsonProperty("Processing")
    public boolean processing;
    @JsonProperty("UploadKey")
    public String uploadKey;
    @JsonProperty("Date")
    public Date date;
    @JsonProperty("DateTimeUploaded")
    public Date dateTimeUploaded;
    @JsonProperty("DateTimeOriginal")
    public Date dateTimeOriginal;
    @JsonProperty("Format")
    public String format;
    @JsonProperty("OriginalHeight")
    public int originalHeight;
    @JsonProperty("OriginalWidth")
    public int originalWidth;
    @JsonProperty("OriginalSize")
    public int originalSize;
    @JsonProperty("LastUpdated")
    public Date lastUpdated;
    @JsonProperty("Collectable")
    public boolean collectable;
    @JsonProperty("IsArchive")
    public boolean isArchive;
    @JsonProperty("IsVideo")
    public boolean isVideo;
    @JsonProperty("ComponentFileTypes")
    public ComponentFileTypes componentFileTypes;
    @JsonProperty("CanEdit")
    public boolean canEdit;
    @JsonProperty("CanBuy")
    public boolean canBuy;
    @JsonProperty("Protected")
    public boolean isProtected;
    @JsonProperty("EZProject")
    public boolean eZProject;
    @JsonProperty("Watermarked")
    public boolean watermarked;
    @JsonProperty("ImageKey")
    public String imageKey;
    @JsonProperty("Serial")
    public int serial;
    @JsonProperty("ArchivedUri")
    public String archivedUri;
    @JsonProperty("ArchivedSize")
    public int archivedSize;
    @JsonProperty("ArchivedMD5")
    public String archivedMD5;
    @JsonProperty("Status")
    public String status;
    @JsonProperty("SubStatus")
    public String subStatus;
    @JsonProperty("CanShare")
    public boolean canShare;
    @JsonProperty("Comments")
    public boolean comments;
    @JsonProperty("ShowKeywords")
    public boolean showKeywords;
    @JsonProperty("FormattedValues")
    public FormattedValues formattedValues;
    @JsonProperty("PreferredDisplayFileExtension")
    public String preferredDisplayFileExtension;
    @JsonProperty("Uri")
    public String uri;
    @JsonProperty("WebUri")
    public String webUri;
    @JsonProperty("UriDescription")
    public String uriDescription;
    @JsonProperty("Uris")
    public Uris uris;
    @JsonProperty("AlbumKey")
    public String albumKey;
    @JsonProperty("Movable")
    public boolean movable;
    @JsonProperty("Origin")
    public String origin;

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ComponentFileTypes {
        @JsonProperty("Image")
        public ArrayList<String> image;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Caption {
        public String html;
        public String text;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FileName {
        public String html;
        public String text;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class FormattedValues {
        @JsonProperty("Caption")
        public Caption caption;
        @JsonProperty("FileName")
        public FileName fileName;
    }

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Uris {
        @JsonProperty("Components")
        public SMUri components;
        @JsonProperty("LargestImage")
        public SMUri largestImage;
        @JsonProperty("ImageSizes")
        public SMUri imageSizes;
        @JsonProperty("ImageSizeDetails")
        public SMUri imageSizeDetails;
        @JsonProperty("PointOfInterest")
        public SMUri pointOfInterest;
        @JsonProperty("PointOfInterestCrops")
        public SMUri pointOfInterestCrops;
        @JsonProperty("Regions")
        public SMUri regions;
        @JsonProperty("ImageAlbum")
        public SMUri imageAlbum;
        @JsonProperty("ImageOwner")
        public SMUri imageOwner;
        @JsonProperty("ImageAlbums")
        public SMUri imageAlbums;
        @JsonProperty("ImageDownload")
        public SMUri imageDownload;
        @JsonProperty("RotateImage")
        public SMUri rotateImage;
        @JsonProperty("ColorImage")
        public SMUri colorImage;
        @JsonProperty("CopyImage")
        public SMUri copyImage;
        @JsonProperty("CropImage")
        public SMUri cropImage;
        @JsonProperty("ImageMetadata")
        public SMUri imageMetadata;
        @JsonProperty("ImagePrices")
        public SMUri imagePrices;
        @JsonProperty("ImagePricelistExclusions")
        public SMUri imagePricelistExclusions;
        @JsonProperty("Album")
        public SMUri album;
        @JsonProperty("Image")
        public SMUri image;
        @JsonProperty("AlbumImagePricelistExclusions")
        public SMUri albumImagePricelistExclusions;
        @JsonProperty("AlbumImageMetadata")
        public SMUri albumImageMetadata;
        @JsonProperty("AlbumImageShareUris")
        public SMUri albumImageShareUris;
        @JsonProperty("AlbumImageDownload")
        public SMUri albumImageDownload;
    }
}
