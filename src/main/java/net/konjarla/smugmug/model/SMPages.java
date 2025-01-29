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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SMPages {
    @JsonProperty("Total")
    private Integer total;
    @JsonProperty("Start")
    private Integer start;
    @JsonProperty("Count")
    private Integer count;
    @JsonProperty("RequestedCount")
    private Integer requestedCount;
    @JsonProperty("FirstPage")
    private  String firstPage;
    @JsonProperty("LastPage")
    private String lastPage;
    @JsonProperty("PrevPage")
    private String prevPage;
    @JsonProperty("NextPage")
    private String nextPage;
}
