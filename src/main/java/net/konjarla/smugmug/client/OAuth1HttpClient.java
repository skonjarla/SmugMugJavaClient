package net.konjarla.smugmug.client;

import net.konjarla.smugmug.oauth.OAuth1Signature;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OAuth1HttpClient {

    private final OAuth1Signature.Builder signatureBuilder;
    private final CloseableHttpClient httpClient;
    private static final Logger log = LoggerFactory.getLogger(OAuth1HttpClient.class);

    private OAuth1HttpClient(Builder builder) {
        this.signatureBuilder = builder.signatureBuilder;
        HttpClientBuilder httpClientBuilder = builder.httpClientBuilder != null ? builder.httpClientBuilder : HttpClientBuilder.create();
        this.httpClient = httpClientBuilder
                .addRequestInterceptorLast(new RequestLoggingInterceptor())
                .disableRedirectHandling()
                .build();
    }

    public static class Builder {
        private OAuth1Signature.Builder signatureBuilder;
        private HttpClientBuilder httpClientBuilder;

        public Builder signatureBuilder(OAuth1Signature.Builder signatureBuilder) {
            this.signatureBuilder = signatureBuilder;
            return this;
        }

        public Builder httpClientBuilder(HttpClientBuilder httpClientBuilder) {
            this.httpClientBuilder = httpClientBuilder;
            return this;
        }

        public OAuth1HttpClient build() {
            Objects.requireNonNull(signatureBuilder, "signatureBuilder cannot be null");
            return new OAuth1HttpClient(this);
        }
    }


    /**
     * Execute a request with OAuth1 authentication.
     *
     * @param method            HTTP method to use
     * @param url               URL to call
     * @param body              HTTP entity to send
     * @param additionalHeaders Additional headers to set
     * @param handler           Response handler
     * @return The response, handled by the provided handler
     * @throws Exception if any error occurs
     */
    public <T> T execute(String method, String url, HttpEntity body, Map<String, String> additionalHeaders, HttpClientResponseHandler<T> handler) throws Exception {
        try {
            URI uri = new URI(url);
            HttpUriRequestBase request;

            switch (method.toUpperCase()) {
                case "GET":
                    request = new HttpGet(uri);
                    request.addHeader(HttpHeaders.ACCEPT, "application/json");
                    if (additionalHeaders != null) additionalHeaders.forEach(request::addHeader);
                    break;
                case "POST":
                    request = new HttpPost(uri);
                    if (body != null) {
                        ((HttpPost) request).setEntity(body);
                        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                        if (additionalHeaders != null) additionalHeaders.forEach(request::addHeader);
                    }
                    break;
                case "PUT":
                    request = new HttpPut(uri);
                    if (body != null) {
                        ((HttpPut) request).setEntity(body);
                        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                        if (additionalHeaders != null) additionalHeaders.forEach(request::addHeader);
                    }
                    break;
                case "PATCH":
                    request = new HttpPatch(uri);
                    if (body != null) {
                        ((HttpPatch) request).setEntity(body);
                        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                        if (additionalHeaders != null) additionalHeaders.forEach(request::addHeader);
                    }
                    break;
                case "DELETE":
                    request = new HttpDelete(uri);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }

            Map<String, String> allParams = new HashMap<>();
            //allParams.put("realm", url);
            String queryString = uri.getQuery();
            log.debug("Base URL {}", getBaseUri(uri));

            if (queryString != null) {
                Map<String, String> keyValueMap = Arrays.stream(queryString.split("&"))
                        .map(kv -> kv.split("="))
                        //.filter(kvArray -> kvArray.length == 2)
                        .collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
                allParams.putAll(keyValueMap);
            }

            OAuth1Signature signature = signatureBuilder
                    .httpMethod(method)
                    //.url(url)
                    .url(getBaseUri(uri))
                    .parameters(allParams)
                    .build();

            String oauthHeader = buildOAuthHeader(signature, allParams);
            log.debug("OAuth Header: " + oauthHeader);
            request.addHeader(HttpHeaders.AUTHORIZATION, oauthHeader);

            return httpClient.execute(request, handler);
            //return httpClient.execute(request);// Use provided or default handler

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL: " + url, e);
        } catch (IOException e) {
            throw new RuntimeException("Error executing request", e);
        }
    }

    /**
     * Builds an OAuth 1.0 authorization header using the provided OAuth1Signature and parameters.
     *
     * @param signature the OAuth1Signature object containing necessary OAuth credentials and information
     * @param allParams a map of additional parameters to include in the signature
     * @return a string representing the OAuth 1.0 authorization header
     * @throws Exception if an error occurs while generating the OAuth signature
     */
    private String buildOAuthHeader(OAuth1Signature signature, Map<String, String> allParams) throws Exception {
        String generatedSignature = signature.generateSignature();
        Map<String, String> oauthParams = new HashMap<>(allParams);
        oauthParams.put("oauth_consumer_key", signature.getConsumerKey());
        oauthParams.put("oauth_nonce", signature.getNonce());
        oauthParams.put("oauth_signature", generatedSignature);
        oauthParams.put("oauth_signature_method", "HMAC-SHA1");
        oauthParams.put("oauth_timestamp", signature.getTimestamp());
        oauthParams.put("oauth_token", signature.getAccessToken());
        oauthParams.put("oauth_version", "1.0");
        //oauthParams.put("realm", signature.getUrl());

        return "OAuth " + oauthParams.entrySet().stream()
                .map(entry -> OAuth1Signature.encode(entry.getKey()) + "=\"" + OAuth1Signature.encode(entry.getValue()) + "\"")
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    /**
     * Extracts the base URI from the given {@link URI}, without the query string.
     * <p>
     * The base URI is the scheme, host, port, and path of the given URI, without the query string.
     * If the port is not the default port for the scheme, the port is included in the base URI.
     * <p>
     * For example, if the given URI is {@code https://example.com:8080/path/to/resource?query=string},
     * the base URI returned by this method is {@code https://example.com:8080/path/to/resource}.
     * <p>
     * This method is useful for constructing the base URI for an OAuth 1.0a signature.
     *
     * @param uri the URI from which to extract the base URI
     * @return the base URI
     * @throws URISyntaxException    if the given URI is invalid
     * @throws MalformedURLException if the given URI is invalid
     */
    public String getBaseUri(URI uri) throws URISyntaxException, MalformedURLException {
        String scheme = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        String path = uri.getPath();

        // Construct the base URI
        if (port != -1 && port != uri.toURL().getDefaultPort()) {
            return scheme + "://" + host + ":" + port + path;
        } else {
            return scheme + "://" + host + path;
        }
    }
}
