package com.mindden.prueba.prediction.controller.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info()
      .title("Prediction")
      .version("0.0.1-SNAPSHOT")
      .description("Prueba técnica Manuel Porteiro")
      .termsOfService("http://swagger.io/terms/")
      .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }

}
