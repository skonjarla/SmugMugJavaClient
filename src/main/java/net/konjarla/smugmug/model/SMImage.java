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
    String title;
    @JsonProperty("Caption")
    String caption;
    @JsonProperty("Keywords")
    String keywords;
    @JsonProperty("KeywordArray")
    List<String> keywordArray;
    @JsonProperty("Watermark")
    String watermark;
    @JsonProperty("Latitude")
    Double latitude;
    @JsonProperty("Longitude")
    Double longitude;
    @JsonProperty("Altitude")
    Double altitude;
    @JsonProperty("Hidden")
    Boolean hidden;
    @JsonProperty("ThumbnailUrl")
    String thumbnailUrl;
    @JsonProperty("FileName")
    String fileName;
    @JsonProperty("Processing")
    Boolean processing;
    @JsonProperty("UploadKey")
    String uploadKey;
    @JsonProperty("Date")
    String date;
    @JsonProperty("Format")
    String format;
    @JsonProperty("OriginalHeight")
    Integer originalHeight;
    @JsonProperty("OriginalWidth")
    Integer originalWidth;
    @JsonProperty("LastUpdated")
    String lastUpdated;
    @JsonProperty("Collectable")
    String collectable;
    @JsonProperty("IsArchive")
    Boolean isArchive;
    @JsonProperty("IsVideo")
    Boolean isVideo;
    @JsonProperty("CanEdit")
    Boolean canEdit;
    @JsonProperty("CanBuy")
    Boolean canBuy;
    @JsonProperty("Protected")
    Boolean isProtected;
    @JsonProperty("Watermarked")
    Boolean watermarked;
    @JsonProperty("ImageKey")
    String imageKey;
    @JsonProperty("ArchivedUri")
    String archivedUri;
    @JsonProperty("ArchivedSize")
    Integer archivedSize;
    @JsonProperty("ArchivedMD5")
    String archivedMD5;
    @JsonProperty("CanShare")
    Boolean canShare;
    @JsonProperty("Comments")
    Boolean comments;
    @JsonProperty("ShowKeywords")
    Boolean showKeywords;
    @JsonProperty("Uri")
    String uri;
    @JsonProperty("WebUri")
    String webUri;
    @JsonProperty("UriDescription")
    String uriDescription;
    @JsonProperty("Uris")
    SMImageURIs uris;
    @JsonProperty("Movable")
    Boolean movable;
    @JsonProperty("Origin")
    String origin;

    @Getter
    @ToString
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SMImageURIs {
        @JsonProperty("LargestImage")
        SMUri largestImage;
        @JsonProperty("ImageSizes")
        SMUri imageSizes;
        @JsonProperty("ImageSizeDetails")
        SMUri imageSizeDetails;
        @JsonProperty("PointOfInterest")
        SMUri pointOfInterest;
        @JsonProperty("PointOfInterestCrops")
        SMUri pointOfInterestCrops;
        @JsonProperty("Regions")
        SMUri regions;
        @JsonProperty("ImageAlbum")
        SMUri imageAlbum;
        @JsonProperty("ImageDownload")
        SMUri imageDownload;
        @JsonProperty("ImageOwner")
        SMUri imageOwner;
        @JsonProperty("RotateImage")
        SMUri rotateImage;
        @JsonProperty("ColorImage")
        SMUri colorImage;
        @JsonProperty("CropImage")
        SMUri cropImage;
        @JsonProperty("ImageMetadata")
        SMUri imageMetadata;
        @JsonProperty("ImagePrices")
        SMUri imagePrices;
        @JsonProperty("ImagePricelistExclusions")
        SMUri imagePricelistExclusions;
        @JsonProperty("Album")
        SMUri album;
        @JsonProperty("Image")
        SMUri image;
        @JsonProperty("AlbumImageShareUris")
        SMUri albumImageShareUris;
    }
}
