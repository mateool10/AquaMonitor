package com.aquamonitor.platform.pc216712u202422549.shared.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configures the OpenAPI specification shown in Swagger UI.
 *
 * @author Jorge Mateo León Naupari
 */
@Configuration
public class OpenApiConfiguration {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${documentation.application.description}")
    String applicationDescription;

    @Value("${documentation.application.version}")
    String applicationVersion;

    /**
     * Builds the OpenAPI document of the AquaMonitor Platform API.
     *
     * @return the configured OpenAPI descriptor
     */
    @Bean
    public OpenAPI aquaMonitorPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi.info(new Info()
                .title(this.applicationName)
                .description(this.applicationDescription)
                .version(this.applicationVersion)
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html")));

        openApi.servers(List.of(
                new Server()
                        .url("http://localhost:8080")
                        .description("Local Development Environment")));

        return openApi;
    }
}
