package ma.yassine.ecommerce_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pc
 **/
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("E-commerce API")
                        .description("E-commerce minimal API documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Yassine Bahadi")
                                .email("yassinebahadi04@gmail.com"))

                        .license(new License()
                                .name("Apache 2.0")
                                .url("ttp://springdoc.org")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/auth/**","/api/products/**","/api/categories/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/api/admin/**")
                .build();
    }
}
