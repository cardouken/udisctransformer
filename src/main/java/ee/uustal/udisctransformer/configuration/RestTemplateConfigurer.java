package ee.uustal.udisctransformer.configuration;

import ee.uustal.udisctransformer.service.http.CookieHandlingClientHttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class RestTemplateConfigurer {

    public static RestPackage configure(RestTemplate restTemplate, int maxConnTotal, int maxConnPerRoute, int connectTimeout, int connectionRequestTimeout, int readTimeout) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxConnTotal);
        connectionManager.setDefaultMaxPerRoute(maxConnPerRoute);

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionReuseStrategy(null)
                .setConnectionManager(connectionManager)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(connectTimeout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout);
        factory.setReadTimeout(readTimeout);

        restTemplate.setRequestFactory(factory);
        restTemplate.getInterceptors().add(new CookieHandlingClientHttpRequestInterceptor());

        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());

        return new RestPackage(restTemplate, connectionManager);
    }

    public static class RestPackage {

        private RestTemplate restTemplate;
        private PoolingHttpClientConnectionManager connectionManager;

        public RestPackage(RestTemplate restTemplate, PoolingHttpClientConnectionManager connectionManager) {
            this.restTemplate = restTemplate;
            this.connectionManager = connectionManager;
        }

        public RestTemplate getRestTemplate() {
            return restTemplate;
        }

        public PoolingHttpClientConnectionManager getConnectionManager() {
            return connectionManager;
        }
    }
}
