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
    String title;
    @JsonProperty("Caption")
    String caption;
    @JsonProperty("UserComment")
    String userComment;
    @JsonProperty("Keywords")
    String keywords;
    @JsonProperty("Author")
    String author;
    @JsonProperty("Copyright")
    String copyright;
    @JsonProperty("CopyrightUrl")
    String copyrightUrl;
    @JsonProperty("CopyrightFlag")
    String copyrightFlag;
    @JsonProperty("Source")
    String source;
    @JsonProperty("Credit")
    String credit;
    @JsonProperty("City")
    String city;
    @JsonProperty("State")
    String state;
    @JsonProperty("Country")
    String country;
    @JsonProperty("Rating")
    String rating;
    @JsonProperty("CreatorContactInfo")
    String creatorContactInfo;
    @JsonProperty("Category")
    String category;
    @JsonProperty("SupplementalCategories")
    String supplementalCategories;
    @JsonProperty("SpecialInstructions")
    String specialInstructions;
    @JsonProperty("AuthorTitle")
    String authorTitle;
    @JsonProperty("CountryCode")
    String countryCode;
    @JsonProperty("TransmissionReference")
    String transmissionReference;
    @JsonProperty("Headline")
    String headline;
    @JsonProperty("WriterEditor")
    String writerEditor;
    @JsonProperty("Lens")
    String lens;
    @JsonProperty("Make")
    String make;
    @JsonProperty("Model")
    String model;
    @JsonProperty("Aperture")
    String aperture;
    @JsonProperty("DateTimeModified")
    String dateTimeModified;
    @JsonProperty("DateTimeCreated")
    String dateTimeCreated;
    @JsonProperty("DateCreated")
    String dateCreated;
    @JsonProperty("TimeCreated")
    String timeCreated;
    @JsonProperty("MicroDateTimeCreated")
    String microDateTimeCreated;
    @JsonProperty("MicroDateTimeDigitized")
    String microDateTimeDigitized;
    @JsonProperty("DateDigitized")
    String dateDigitized;
    @JsonProperty("Exposure")
    String exposure;
    @JsonProperty("ISO")
    Integer iso;
    @JsonProperty("FocalLength")
    String focalLength;
    @JsonProperty("FocalLength35mm")
    String focalLength35mm;
    @JsonProperty("CompressedBitsPerPixel")
    String compressedBitsPerPixel;
    @JsonProperty("Flash")
    String flash;
    @JsonProperty("Metering")
    String metering;
    @JsonProperty("ExposureProgram")
    String exposureProgram;
    @JsonProperty("ExposureCompensation")
    String exposureCompensation;
    @JsonProperty("ExposureMode")
    String exposureMode;
    @JsonProperty("LightSource")
    String lightSource;
    @JsonProperty("WhiteBalance")
    String whiteBalance;
    @JsonProperty("DigitalZoomRatio")
    String digitalZoomRatio;
    @JsonProperty("Contrast")
    String contrast;
    @JsonProperty("Saturation")
    String saturation;
    @JsonProperty("Sharpness")
    String sharpness;
    @JsonProperty("SubjectDistance")
    String subjectDistance;
    @JsonProperty("SubjectRange")
    String subjectRange;
    @JsonProperty("SensingMethod")
    String sensingMethod;
    @JsonProperty("ColorSpace")
    String colorSpace;
    @JsonProperty("Brightness")
    String brightness;
    @JsonProperty("LatitudeReference")
    String latitudeReference;
    @JsonProperty("LongitudeReference")
    String longitudeReference;
    @JsonProperty("Latitude")
    Double latitude;
    @JsonProperty("Longitude")
    Double longitude;
    @JsonProperty("AltitudeReference")
    String altitudeReference;
    @JsonProperty("Altitude")
    String altitude;
    @JsonProperty("SceneCaptureType")
    String sceneCaptureType;
    @JsonProperty("GainControl")
    String gainControl;
    @JsonProperty("ScaleFactor")
    String scaleFactor;
    @JsonProperty("CircleOfConfusion")
    String circleOfConfusion;
    @JsonProperty("FieldOfView")
    String fieldOfView;
    @JsonProperty("DepthOfField")
    String depthOfField;
    @JsonProperty("HyperfocalDistance")
    String hyperfocalDistance;
    @JsonProperty("NormalizedLightValue")
    String normalizedLightValue;
    @JsonProperty("Duration")
    Double duration;
    @JsonProperty("AudioCodec")
    String audioCodec;
    @JsonProperty("VideoCodec")
    String videoCodec;
    @JsonProperty("Software")
    String software;
    @JsonProperty("SerialNumber")
    String serialNumber;
    @JsonProperty("LensSerialNumber")
    String lensSerialNumber;
    @JsonProperty("Uri")
    String uri;
    @JsonProperty("UriDescription")
    String uriDescription;
}
