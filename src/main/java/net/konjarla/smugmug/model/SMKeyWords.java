package net.konjarla.smugmug.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@ToString
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMKeyWords {
    @JsonProperty("KeywordArray")
    private List<String> keywords;

}
