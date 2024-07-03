package com.gabriel.ecommerce.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Class to configure the OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig implements OpenApiCustomizer {

  /**
   * The Scheme name.
   */
  public final String schemeName = "Bearer Auth";

  @Override
  public void customise(OpenAPI openApi) {
    Info info = new Info()
        .title("Agenda de Contatos")
        .description("Este projeto apresenta uma API RESTful que facilita a gestão de listas de"
            + " contatos, possibilitando as pessoas usuárias criar, visualizar, atualizar e excluir"
            + " contatos de forma intuitiva e prática. A API oferece endpoints específicos para"
            + " operações CRUD (Create, Read, Update, Delete) em listas de contatos, visando"
            + " proporcionar uma experiência consistente e confiável.")
        .version("1.0.0");

    openApi.info(info);

    openApi.getComponents()
        .addSecuritySchemes(schemeName, new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT"));

    openApi.addSecurityItem(new SecurityRequirement().addList(schemeName));
  }
}