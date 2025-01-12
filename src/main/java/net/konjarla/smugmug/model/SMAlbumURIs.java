package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMAlbumURIs {
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
