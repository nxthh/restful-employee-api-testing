package co.istad.restfulemployeeapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Value("${media.client-url}")
    private String clientUrl;

    @Value("${media.location}")
    private String mediaLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(clientUrl + "/**")
                .addResourceLocations("file:" + mediaLocation);

    }
}
