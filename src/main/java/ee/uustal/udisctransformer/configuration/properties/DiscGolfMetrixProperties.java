package ee.uustal.udisctransformer.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "metrix.api")
public class DiscGolfMetrixProperties {

    private final String uri;
    private final String username;
    private final String password;

    @ConstructorBinding
    public DiscGolfMetrixProperties(String uri, String username, String password) {
        this.uri = uri;
        this.username = username;
        this.password = password;
    }

    public String getUri() {
        return uri;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}