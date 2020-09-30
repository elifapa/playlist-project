package com.trendyol.playlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .paths(not(regex("/error.*")))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Playlist app")
                .description("Make playlists great again")
                .build();
    }
}
