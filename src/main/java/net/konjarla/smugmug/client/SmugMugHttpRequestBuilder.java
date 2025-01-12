package net.konjarla.smugmug.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.konjarla.smugmug.client.api.ResponseDeserializer;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SmugMugHttpRequestBuilder<T> {

    private static final Logger log = LoggerFactory.getLogger(SmugMugHttpRequestBuilder.class);

    private final OAuth1HttpClient oAuthClient;
    private final String method;
    private final String url;
    private HttpEntity requestBody;
    private final Map<String, String> additionalHeaders = new HashMap<>();
    private final Class<T> responseType;
    private final ObjectMapper mapper = new ObjectMapper();

    private SmugMugHttpRequestBuilder(OAuth1HttpClient oAuthClient, String method, String url, Class<T> responseType) {
        this.oAuthClient = oAuthClient;
        this.method = method;
        this.url = url;
        this.responseType = responseType;
    }

    /**
     * Creates a new instance of SmugMugHttpRequestBuilder.
     *
     * @param oAuthClient the OAuth1HttpClient to use for making requests
     * @param method the HTTP method to use (e.g., "GET", "POST")
     * @param url the URL to send the request to
     * @param responseType the class type of the expected response
     * @param <T> the type of the expected response
     * @return a new SmugMugHttpRequestBuilder instance configured with the provided parameters
     * @throws NullPointerException if any of the parameters are null
     */
    public static <T> SmugMugHttpRequestBuilder<T> create(OAuth1HttpClient oAuthClient, String method, String url, Class<T> responseType) {
        Objects.requireNonNull(oAuthClient, "oAuthClient cannot be null");
        Objects.requireNonNull(method, "method cannot be null");
        Objects.requireNonNull(url, "url cannot be null");
        Objects.requireNonNull(responseType, "responseType cannot be null");
        return new SmugMugHttpRequestBuilder<>(oAuthClient, method, url, responseType);
    }

    /**
     * Sets the request body for the request. The body will be serialized to JSON.
     *
     * @param body the request body
     * @return this builder
     */
    public SmugMugHttpRequestBuilder<T> body(HttpEntity body) {
        //this.requestBody = mapper.writeValueAsString(body);
        this.requestBody = body; //Serialize to JSON
        return this;
    }

    /**
     * Sets a header for the request. Note that the {@code Authorization} header
     * is automatically set by the {@link OAuth1HttpClient} and should not be
     * overridden.
     *
     * @param key   the header key
     * @param value the header value
     * @return this builder
     */
    public SmugMugHttpRequestBuilder<T> header(String key, String value) {
        this.additionalHeaders.put(key, value);
        return this;
    }

    /**
     * Executes the request and returns the response. The response body is
     * deserialized to the provided response type.
     *
     * @return the response, deserialized to the provided response type
     * @throws Exception if any error occurs
     */
    public T execute() throws Exception {
        HttpClientResponseHandler<T> handler = response -> {
            int statusCode = response.getCode(); // Correct way to get status code
            if (statusCode >= HttpStatus.SC_CLIENT_ERROR) {
                String errorBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                throw new ClientProtocolException("HTTP error: " + statusCode + " Body: " + errorBody); // Use statusCode
            } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                log.debug("Redirecting to " + response.getFirstHeader("Location").getValue());
                String location = response.getFirstHeader("Location").getValue();
                try {
                    return oAuthClient.execute(method, getUpdatedRedirectURI(url, location), requestBody, additionalHeaders, newResponse -> {
                        String json = EntityUtils.toString(newResponse.getEntity(), StandardCharsets.UTF_8);
                        SimpleModule module = new SimpleModule();
                        module.addDeserializer(SMBaseResponse.class, new ResponseDeserializer<>());
                        mapper.registerModule(module);
                        return mapper.readValue(json, responseType);
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (response.getEntity() == null) {
                return null;
            }
            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            SimpleModule module = new SimpleModule();
            module.addDeserializer(SMBaseResponse.class, new ResponseDeserializer<>());
            mapper.registerModule(module);
            return mapper.readValue(json, responseType);
        };

        return oAuthClient.execute(method, url, requestBody, additionalHeaders, handler);

    }

    /**
     * Executes a request with the given handler. The handler is responsible for
     * handling the response and returning the result. The {@link HttpClientResponseHandler}
     * interface is used to handle the response.
     * @param handler the handler to use when the request is executed
     * @return the result of the request, as determined by the handler
     * @throws Exception if an error occurs while executing the request
     */
    public T executeRaw(HttpClientResponseHandler<T> handler) throws Exception {
        //HttpClientResponseHandler<CloseableHttpResponse> handler = response -> (CloseableHttpResponse)response;
        return oAuthClient.execute(method, url, requestBody, additionalHeaders, handler);
    }

    /**
     * Returns the request body entity for the given HTTP method. If the method is GET or DELETE, then
     * null is returned, since these methods should not have a body. Otherwise, the request body
     * (if any) is returned.
     *
     * @param method the HTTP method
     * @return the request body or null
     */
    private HttpEntity getRequestBodyForMethod(String method) {
        if ("GET".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
            return null; // Don't send a body for GET or DELETE
        }
        return requestBody;
    }

    /**
     * Updates the redirect URI by preserving the original URI's scheme, host, port, and query string.
     * Constructs a new URI using the original URI's components and the provided redirect path.
     *
     * @param originalURI the original URI to extract components from
     * @param redirectURI the new path to append to the base of the original URI
     * @return the updated URI with the new redirect path
     * @throws MalformedURLException if the original URI is invalid
     */
    private String getUpdatedRedirectURI(String originalURI, String redirectURI) throws MalformedURLException {
        URI uri = URI.create(originalURI);
        String scheme = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        String path = uri.getPath();
        String query = uri.getQuery();

        // Construct the base URI
        if (port != -1 && port != uri.toURL().getDefaultPort()) {
            return scheme + "://" + host + ":" + port + redirectURI + (query != null ? "?" + query : "");
        } else {
            return scheme + "://" + host + redirectURI + (query != null ? "?" + query : "");
        }
    }
}
