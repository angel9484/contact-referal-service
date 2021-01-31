package es.bnext.api.client;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;

@ConfigurationProperties("client.contact")
@Requires(property = "client.contact")
@Data
public class ContactClientConfiguration {

    private String url;
}
