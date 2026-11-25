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
        .title("SpringMarket")
        .description("API REST de e-commerce com cadastro e login de usuários, catálogo de"
            + " produtos e registro de vendas. A autenticação usa tokens JWT e as rotas"
            + " protegidas exigem o header Authorization: Bearer <token>.")
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