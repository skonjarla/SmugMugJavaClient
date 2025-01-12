package net.konjarla.smugmug.client;

import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class RequestLoggingInterceptor implements HttpRequestInterceptor {
    private final static Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public void process(HttpRequest request, EntityDetails entity, HttpContext context) throws HttpException, IOException {

        try {
            log.debug("Request Method: {}", request.getMethod());
            log.debug("Request URL: {}", request.getUri().toString());
            log.debug("Request Headers: {}", Arrays.toString(request.getHeaders()));
            if(entity != null) {
                log.debug(entity.toString());
            }
        } catch (Exception e) {
            throw new HttpException("Error with RequestLoggingInterceptor", e);
        }
    }
}
