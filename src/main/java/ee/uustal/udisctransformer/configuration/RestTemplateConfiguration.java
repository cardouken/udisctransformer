package ee.uustal.udisctransformer.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate discGolfMetrixRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplateConfigurer.RestPackage restPackage = RestTemplateConfigurer.configure(
                restTemplateBuilder.build(),
                100,
                100,
                20000,
                2000,
                2000
        );

        return restPackage.getRestTemplate();
    }
}
