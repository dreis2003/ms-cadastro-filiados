package br.com.ikonbrasil.cadastrofiliados.compartilhado.infraestrutura.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfiguracaoOpenApi {

    @Bean
    OpenAPI documentacaoOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("ms-cadastro-filiados")
                        .description("API do microsservico de cadastro de filiados da IKO Nakamura Brasil")
                        .version("v1")
                        .contact(new Contact()
                                .name("IKO Nakamura Brasil"))
                        .license(new License()
                                .name("Uso interno")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Ambiente local")
                ));
    }
}
