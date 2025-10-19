package com.capymenu.capy_menu_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringdocConfig {

    @Value("${capymenu.swagger.url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server().url(url).description("Server active");

        Info info = new Info()
            .title("Capy Menu API")
            .version("1.0")
            .description("AAPI expose the endpoints of the system")
            .license(new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0"));

        Components components = new Components()
            .addSecuritySchemes("bearer-jwt",
                    new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .in(SecurityScheme.In.HEADER)
                            .name("Authorization"));
        
        return new OpenAPI()
            .info(info)
            .servers(List.of(server))
            .components(components);
    }

}
