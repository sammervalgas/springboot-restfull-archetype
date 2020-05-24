package it.pkg.configurations.swagger;

import it.pkg.RestApplication;
import it.pkg.model.autenticacao.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocument() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(projectPackage()))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(LoginUser.class);
    }

    private String projectPackage() {
        Class<?> mainPackClass = RestApplication.class;
        return mainPackClass.getCanonicalName()
                .replace(mainPackClass.getSimpleName(),
                        "");
    }
}
