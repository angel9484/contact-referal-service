package es.bnext.api.client;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;

@ConfigurationProperties("client.user")
@Requires(property = "client.user")
@Data
public class UserClientConfiguration {

    private String url;
}
