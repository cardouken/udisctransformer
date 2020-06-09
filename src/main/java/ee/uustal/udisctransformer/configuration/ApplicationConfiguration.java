package ee.uustal.udisctransformer.configuration;

import ee.uustal.udisctransformer.configuration.properties.DiscGolfMetrixProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        DiscGolfMetrixProperties.class
})
public class ApplicationConfiguration {
}
