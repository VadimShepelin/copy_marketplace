package com.spring.marketplace.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${app.server.url}")
    private String serverUrl;

    @Bean
    public OpenAPI defineOpenAPI () {
        Server server = new Server();
        server.setUrl(serverUrl);
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Vadim Shepelin");
        myContact.setEmail("my.email@example.com");

        Info info = new Info()
                .title("Marketplace API")
                .version("1.0")
                .description("This is a marketplace API that provides the ability to order products, view order history, etc.")
                .contact(myContact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
