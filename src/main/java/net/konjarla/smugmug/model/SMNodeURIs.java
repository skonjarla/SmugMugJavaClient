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
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMNodeURIs {
    @JsonProperty("Album")
    private SMUri album;
    @JsonProperty("FolderByID")
    private SMUri folderByID;
    @JsonProperty("ParentNode")
    private SMUri parentNode;
    @JsonProperty("ParentNodes")
    private SMUri parentNodes;
    @JsonProperty("User")
    private SMUri user;
    @JsonProperty("NodeCoverImage")
    private SMUri nodeCoverImage;
    @JsonProperty("HighlightImage")
    private SMUri highlightImage;
    @JsonProperty("NodeComments")
    private SMUri nodeComments;
    @JsonProperty("NodePricelist")
    private SMUri nodePricelist;
    @JsonProperty("ChildNodes")
    private SMUri childNodes;
}
