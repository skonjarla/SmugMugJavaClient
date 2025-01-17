package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchParams {
    @JsonProperty("Scope")
    @Builder.Default
    private String scope = "";
    @JsonProperty("SortDirection")
    @Builder.Default
    private SortDirection sortDirection = SortDirection.Descending;
    @JsonProperty("SortMethod")
    private SortMethod sortMethod;
    @JsonProperty("DateTakenStart")
    private LocalDateTime dateTakenStart;
    @JsonProperty("DateTakenEnd")
    private LocalDateTime dateTakenEnd;
    @JsonProperty("DateUploadedStart")
    private LocalDateTime dateUploadedStart;
    @JsonProperty("DateUploadedEnd")
    private LocalDateTime dateUploadedEnd;
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("Relevance")
    @Builder.Default
    private Relevance relevance = Relevance.True;
    @JsonProperty("Type")
    @Builder.Default
    private SearchType type = SearchType.Image;
    @JsonProperty("start")
    @Builder.Default
    private Integer start = 1;
    @JsonProperty("count")
    @Builder.Default
    private Integer count = 10;

    enum SearchType {
        Image,
        Video
    }

    enum SortMethod {
        Rank,
        Popular,
        DateTaken,
        DateUploaded,
        LastUpdated
    }

    enum SortDirection {
        Ascending,
        Descending
    }

    enum Relevance {
        True,
        False
    }
}
