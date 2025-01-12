package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@ToString
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMImageURIs {
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
