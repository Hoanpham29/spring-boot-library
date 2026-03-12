package com.project.spring_boot_library.config;

import com.project.spring_boot_library.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String theAllowedOrigins = "http://localhost:3000";

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors){

        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PUT,
                HttpMethod.PATCH,
                HttpMethod.DELETE};
        config.exposeIdsFor(Book.class);

        disableHttpMethod(Book.class,config, theUnsupportedActions);

        /* Configure CORS Mapping*/
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethod(Class theClass,
                                   RepositoryRestConfiguration config,
                                   HttpMethod[] theUnsupportedAction){
        config.getExposureConfiguration()
                .forDomainType(theClass).
                withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedAction))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedAction));
    }
}
