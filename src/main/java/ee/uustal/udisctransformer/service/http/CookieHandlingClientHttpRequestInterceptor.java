package ee.uustal.udisctransformer.service.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CookieHandlingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(CookieHandlingClientHttpRequestInterceptor.class);
    private final Map<String, HttpCookie> cookies = new HashMap<>();

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        List<String> cookiesForRequest = cookies.values().stream()
                .filter(cookie -> cookie.getPath() != null && request.getURI().getPath().startsWith(cookie.getPath()))
                .map(HttpCookie::toString)
                .collect(Collectors.toList());

        LOG.info("Using cookies: {}", cookiesForRequest);
        request.getHeaders().addAll(HttpHeaders.COOKIE, cookiesForRequest);

        ClientHttpResponse response = execution.execute(request, body);

        List<String> newCookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        if (newCookies != null) {
            List<HttpCookie> parsedCookies = newCookies.stream()
                    .flatMap(rawCookie -> HttpCookie.parse(HttpHeaders.COOKIE + ": " + rawCookie).stream())
                    .collect(Collectors.toList());

            LOG.info("Extracted cookies from response: {}", parsedCookies);
            parsedCookies.forEach(newCookie -> cookies.put(newCookie.getName(), newCookie));
        }

        return response;
    }
}