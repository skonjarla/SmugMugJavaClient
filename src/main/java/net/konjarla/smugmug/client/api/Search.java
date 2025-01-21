package net.konjarla.smugmug.client.api;

import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMAlbumsResponse;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMImagesResponse;
import net.konjarla.smugmug.client.api.response.SMNodesResponse;
import net.konjarla.smugmug.model.SearchParams;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig();
    private static final Logger log = LoggerFactory.getLogger(Search.class);
    private static final String NODE_SEARCH_PATH = "/api/v2/node!search";
    private static final String ALBUM_SEARCH_PATH = "/api/v2/album!search";
    private static final String IMAGE_SEARCH_PATH = "/api/v2/image!search";

    private Search() {
    }

    /**
     * Searches for nodes based on the specified search parameters and text.
     *
     * @param client       the OAuth1HttpClient to use for making the request
     * @param searchParams the parameters to refine the search
     * @param searchText   the text to search for in nodes
     * @return a list of SMNode objects matching the search criteria, or null if an error occurs
     * @throws IllegalArgumentException if any of the arguments are null or if the search scope is not provided
     */
    public static SMNodesResponse searchForNodes(OAuth1HttpClient client, SearchParams searchParams, String searchText) {
        if (client == null || searchParams == null || searchText == null) {
            throw new IllegalArgumentException("searchParams or searchText or OAuth1HttpClient client cannot be null");
        }
        if (searchParams.getScope() == null || searchParams.getScope().isEmpty()) {
            throw new IllegalArgumentException("Scope for search must be provided");
        }
        String path = API_CONFIG.hostName + NODE_SEARCH_PATH;
        try {
            URI uri = new URIBuilder(path)
                    .addParameter("Text", searchText)
                    .addParameters(getNameValuePair(searchParams))
                    .build();
            return (SMNodesResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, uri.toString(), SMBaseResponse.class)
                    .execute();
        } catch (Exception e) {
            log.error("Error while searching in node", e);
        }
        return null;
    }

    /**
     * Searches for albums based on the specified search parameters and text.
     *
     * @param client       the OAuth1HttpClient to use for making the request
     * @param searchParams the parameters to refine the search
     * @param searchText   the text to search for in albums
     * @return a list of SMAlbum objects matching the search criteria, or null if an error occurs
     * @throws IllegalArgumentException if any of the arguments are null or if the search scope is not provided
     */
    public static SMAlbumsResponse searchForAlbums(OAuth1HttpClient client, SearchParams searchParams, String searchText) {
        if (client == null || searchParams == null || searchText == null) {
            throw new IllegalArgumentException("searchParams or searchText or OAuth1HttpClient client cannot be null");
        }
        if (searchParams.getScope() == null || searchParams.getScope().isEmpty()) {
            throw new IllegalArgumentException("Scope for search must be provided");
        }
        String path = API_CONFIG.hostName + ALBUM_SEARCH_PATH;
        try {
            URI uri = new URIBuilder(path)
                    .addParameter("Text", searchText)
                    .addParameters(getNameValuePair(searchParams))
                    .build();
            return (SMAlbumsResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, uri.toString(), SMBaseResponse.class)
                    .execute();
        } catch (Exception e) {
            log.error("Error while searching in album", e);
        }
        return null;
    }

    /**
     * Searches for images based on the specified search parameters and text.
     *
     * @param client       the OAuth1HttpClient to use for making the request
     * @param searchParams the parameters to refine the search
     * @param searchText   the text to search for in images
     * @return a list of SMImage objects matching the search criteria, or null if an error occurs
     * @throws IllegalArgumentException if any of the arguments are null
     */
    public static SMImagesResponse searchForImages(OAuth1HttpClient client, SearchParams searchParams, String searchText) {
        if (client == null || searchParams == null || searchText == null) {
            throw new IllegalArgumentException("searchParams or searchText or OAuth1HttpClient client cannot be null");
        }
        String path = API_CONFIG.hostName + IMAGE_SEARCH_PATH;
        try {
            URI uri = new URIBuilder(path)
                    .addParameter("Text", searchText)
                    .addParameters(getNameValuePair(searchParams))
                    .build();
            return (SMImagesResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, uri.toString(), SMBaseResponse.class)
                    .execute();
        } catch (Exception e) {
            log.error("Error while searching in image", e);
        }
        return null;
    }

    /**
     * Converts the provided SearchParams object into a list of NameValuePair objects.
     * Each NameValuePair represents a parameter key-value pair that can be used in
     * constructing a URI for search queries.
     *
     * @param searchParams the SearchParams object containing search parameters
     * @return a list of NameValuePair objects representing the search parameters
     */
    private static List<NameValuePair> getNameValuePair(SearchParams searchParams) {
        assert searchParams != null;
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (searchParams.getCount() != null) {
            nameValuePairs.add(new BasicNameValuePair("count", String.valueOf(searchParams.getCount())));
        }
        if (searchParams.getStart() != null) {
            nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(searchParams.getStart())));
        }
        if (searchParams.getSortDirection() != null) {
            nameValuePairs.add(new BasicNameValuePair("SortDirection", String.valueOf(searchParams.getSortDirection())));
        }
        if (searchParams.getSortMethod() != null) {
            nameValuePairs.add(new BasicNameValuePair("SortMethod", String.valueOf(searchParams.getSortMethod())));
        }
        if (searchParams.getScope() != null) {
            nameValuePairs.add(new BasicNameValuePair("Scope", searchParams.getScope()));
        }
        if (searchParams.getRelevance() != null) {
            nameValuePairs.add(new BasicNameValuePair("Relevance", String.valueOf(searchParams.getRelevance())));
        }
        if (searchParams.getDateTakenStart() != null) {
            nameValuePairs.add(new BasicNameValuePair("DateTakenStart", String.valueOf(searchParams.getDateTakenStart())));
        }
        if (searchParams.getDateTakenEnd() != null) {
            nameValuePairs.add(new BasicNameValuePair("DateTakenEnd", String.valueOf(searchParams.getDateTakenEnd())));
        }
        if (searchParams.getDateTakenStart() != null) {
            nameValuePairs.add(new BasicNameValuePair("DateUploadedStart", String.valueOf(searchParams.getDateTakenStart())));
        }
        if (searchParams.getDateTakenEnd() != null) {
            nameValuePairs.add(new BasicNameValuePair("DateUploadedEnd", String.valueOf(searchParams.getDateTakenEnd())));
        }
        if (searchParams.getKeywords() != null && !searchParams.getKeywords().isEmpty()) {
            nameValuePairs.add(new BasicNameValuePair("Keywords", String.valueOf(searchParams.getKeywords())));
        }
        if (searchParams.getType() != null) {
            nameValuePairs.add(new BasicNameValuePair("Type", String.valueOf(searchParams.getType())));
        }
        return nameValuePairs;
    }
}
