package com.consulta.credito.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableSwagger2
public class Swagger {
   
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.consulta.credito"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {    	
    	 return new ApiInfoBuilder()
    			.title("API Rest Consulta Clientes")
    			.description("API REST Consulta Crédito Cliente")
    			.termsOfServiceUrl("Terms of Service")
    			.license("Apache")
    			.licenseUrl("https://www.apache.org/licesen.html")
    			.version("2")
    			.build();
    }	
}
