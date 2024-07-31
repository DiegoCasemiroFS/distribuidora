package br.com.DiegoCasemiroFS.distribuidora.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Distribuidora API")
                        .description("API desenvolvida para auxiliar no controle dos clientes, fornecedores e " +
                                "produtos de uma distribuidora.")
                        .contact(new Contact()
                                .name("Diego Casemiro")
                                .email("diegocasemirodev@gmail.com")));
    }
}
