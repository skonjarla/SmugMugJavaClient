package net.konjarla.smugmug.client.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import net.konjarla.smugmug.model.SMNode;
import net.konjarla.smugmug.model.SMPages;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMNodesResponse extends SMBaseResponse {
    @JsonProperty("Node")
    List<SMNode> nodes;
    @JsonProperty("Pages")
    SMPages pages;
}
