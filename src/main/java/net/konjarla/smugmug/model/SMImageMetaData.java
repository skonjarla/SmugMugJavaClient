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
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class SMImageMetaData {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Caption")
    private String caption;
    @JsonProperty("UserComment")
    private String userComment;
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("Author")
    private String author;
    @JsonProperty("Copyright")
    private String copyright;
    @JsonProperty("CopyrightUrl")
    private String copyrightUrl;
    @JsonProperty("CopyrightFlag")
    private String copyrightFlag;
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Credit")
    private String credit;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Rating")
    private String rating;
    @JsonProperty("CreatorContactInfo")
    private String creatorContactInfo;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("SupplementalCategories")
    private String supplementalCategories;
    @JsonProperty("SpecialInstructions")
    private String specialInstructions;
    @JsonProperty("AuthorTitle")
    private String authorTitle;
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("TransmissionReference")
    private String transmissionReference;
    @JsonProperty("Headline")
    private String headline;
    @JsonProperty("WriterEditor")
    private String writerEditor;
    @JsonProperty("Lens")
    private String lens;
    @JsonProperty("Make")
    private String make;
    @JsonProperty("Model")
    private String model;
    @JsonProperty("Aperture")
    private String aperture;
    @JsonProperty("DateTimeModified")
    private String dateTimeModified;
    @JsonProperty("DateTimeCreated")
    private String dateTimeCreated;
    @JsonProperty("DateCreated")
    private String dateCreated;
    @JsonProperty("TimeCreated")
    private String timeCreated;
    @JsonProperty("MicroDateTimeCreated")
    private String microDateTimeCreated;
    @JsonProperty("MicroDateTimeDigitized")
    private String microDateTimeDigitized;
    @JsonProperty("DateDigitized")
    private String dateDigitized;
    @JsonProperty("Exposure")
    private String exposure;
    @JsonProperty("ISO")
    private Integer iso;
    @JsonProperty("FocalLength")
    private String focalLength;
    @JsonProperty("FocalLength35mm")
    private String focalLength35mm;
    @JsonProperty("CompressedBitsPerPixel")
    private String compressedBitsPerPixel;
    @JsonProperty("Flash")
    private String flash;
    @JsonProperty("Metering")
    private String metering;
    @JsonProperty("ExposureProgram")
    private String exposureProgram;
    @JsonProperty("ExposureCompensation")
    private String exposureCompensation;
    @JsonProperty("ExposureMode")
    private String exposureMode;
    @JsonProperty("LightSource")
    private String lightSource;
    @JsonProperty("WhiteBalance")
    private String whiteBalance;
    @JsonProperty("DigitalZoomRatio")
    private String digitalZoomRatio;
    @JsonProperty("Contrast")
    private String contrast;
    @JsonProperty("Saturation")
    private String saturation;
    @JsonProperty("Sharpness")
    private String sharpness;
    @JsonProperty("SubjectDistance")
    private String subjectDistance;
    @JsonProperty("SubjectRange")
    private String subjectRange;
    @JsonProperty("SensingMethod")
    private String sensingMethod;
    @JsonProperty("ColorSpace")
    private String colorSpace;
    @JsonProperty("Brightness")
    private String brightness;
    @JsonProperty("LatitudeReference")
    private String latitudeReference;
    @JsonProperty("LongitudeReference")
    private String longitudeReference;
    @JsonProperty("Latitude")
    private Double latitude;
    @JsonProperty("Longitude")
    private Double longitude;
    @JsonProperty("AltitudeReference")
    private String altitudeReference;
    @JsonProperty("Altitude")
    private String altitude;
    @JsonProperty("SceneCaptureType")
    private String sceneCaptureType;
    @JsonProperty("GainControl")
    private String gainControl;
    @JsonProperty("ScaleFactor")
    private String scaleFactor;
    @JsonProperty("CircleOfConfusion")
    private String circleOfConfusion;
    @JsonProperty("FieldOfView")
    private String fieldOfView;
    @JsonProperty("DepthOfField")
    private String depthOfField;
    @JsonProperty("HyperfocalDistance")
    private String hyperfocalDistance;
    @JsonProperty("NormalizedLightValue")
    private String normalizedLightValue;
    @JsonProperty("Duration")
    private Double duration;
    @JsonProperty("AudioCodec")
    private String audioCodec;
    @JsonProperty("VideoCodec")
    private String videoCodec;
    @JsonProperty("Software")
    private String software;
    @JsonProperty("SerialNumber")
    private String serialNumber;
    @JsonProperty("LensSerialNumber")
    private String lensSerialNumber;
    @JsonProperty("Uri")
    private String uri;
    @JsonProperty("UriDescription")
    private String uriDescription;
}
