package net.konjarla.smugmug.oauth;

import lombok.Getter;
import lombok.NonNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class OAuth1Signature {

    private final String consumerKey;
    private final String consumerSecret;
    private final String accessToken;
    private final String tokenSecret;
    private final String httpMethod;
    private final String url;
    private final TimestampNonce timestampNonce;
    private final Map<String, String> parameters;

    private OAuth1Signature(Builder builder) {
        this.consumerKey = builder.consumerKey;
        this.consumerSecret = builder.consumerSecret;
        this.accessToken = builder.accessToken;
        this.tokenSecret = builder.tokenSecret;
        this.httpMethod = builder.httpMethod;
        this.url = builder.url;
        // this.parameters = Collections.unmodifiableMap(new HashMap<>(builder.parameters));
        this.parameters = new HashMap<>(builder.parameters);
        try {
            this.timestampNonce = getTimestampNonce();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Builder {
        private String consumerKey;
        private String consumerSecret;
        private String accessToken;
        private String tokenSecret;
        private String httpMethod;
        private String url;
        private Map<String, String> parameters = new HashMap<>(); // Initialize here!

        public Builder consumerKey(String consumerKey) {
            this.consumerKey = consumerKey;
            return this;
        }

        public Builder consumerSecret(String consumerSecret) {
            this.consumerSecret = consumerSecret;
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder tokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
            return this;
        }

        public Builder httpMethod(String httpMethod) {
            this.httpMethod = httpMethod.toUpperCase();
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder parameter(String key, String value) {
            this.parameters.put(key, value); // Method to add parameters
            return this;
        }

        public Builder parameters(Map<String, String> parameters) {
            this.parameters = new HashMap<>(parameters);
            //this.parameters.putAll(parameters);
            return this;
        }

        public OAuth1Signature build() {
            Objects.requireNonNull(consumerKey, "consumerKey cannot be null");
            Objects.requireNonNull(consumerSecret, "consumerSecret cannot be null");
            Objects.requireNonNull(httpMethod, "httpMethod cannot be null");
            Objects.requireNonNull(url, "url cannot be null");
            return new OAuth1Signature(this);
        }
    }

    public Map<String, String> getParameters() {
        return new HashMap<>(parameters);
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getNonce() {
        return timestampNonce.getNonce();
    }

    public String getTimestamp() {
        return timestampNonce.getTimestamp();
    }

    public String getUrl() {
        return url;
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateSignature() throws Exception {
        // TimestampNonce timestampNonce = getTimestampNonce();
        Map<String, String> allParams = new TreeMap<>(parameters); // Use TreeMap for sorting
        allParams.put("oauth_consumer_key", consumerKey);
        allParams.put("oauth_nonce", this.timestampNonce.getNonce());
        allParams.put("oauth_signature_method", "HMAC-SHA1");
        allParams.put("oauth_timestamp", this.timestampNonce.getTimestamp());
        allParams.put("oauth_token", accessToken);
        allParams.put("oauth_version", "1.0");

        String parameterString = allParams.entrySet().stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .reduce((a, b) -> a + "&" + b)
                .orElse("");

        String signatureBaseString = httpMethod + "&" + encode(url) + "&" + encode(parameterString);

        String signingKey = encode(consumerSecret) + "&" + encode(tokenSecret);

        return generateHmacSha1Signature(signatureBaseString, signingKey);
    }

    private String generateHmacSha1Signature(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);
    }

    private TimestampNonce getTimestampNonce() throws UnsupportedEncodingException {
        long timeInMilliSeconds = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");
        return TimestampNonce.builder()
                .timestamp(URLEncoder.encode(String.valueOf(timeInMilliSeconds), "UTF-8"))
                .nonce(URLEncoder.encode(nonce, "UTF-8"))
                .build();
    }

    @Getter
    @lombok.Builder
    private static class TimestampNonce {
        @NonNull
        private final String timestamp;
        @NonNull
        private final String nonce;
    }

}