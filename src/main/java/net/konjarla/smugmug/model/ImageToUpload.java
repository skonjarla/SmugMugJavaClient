package net.konjarla.smugmug.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder(builderMethodName = "fieldBuilder")
public class ImageToUpload {
    @NonNull
    private String fileName;
    @NonNull
    private String albumKey;
    private String caption;
    private String latitude;
    private String longitude;
    private String altitude;
    private String title;
    private String keywords;

    public static ImageToUploadBuilder builder(String fileName, String albumKey) {
        return fieldBuilder().fileName(fileName).albumKey(albumKey);
    }
}
