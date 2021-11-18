package sit.int221.ppclothes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Value("#{'${clothes.origin.method}'.split(',')}")
    private String[] methodList;
    @Value("#{'${clothes.origin.host}'.split(',')}")
    String[] hostList;
    @Value("#{'${clothes.origin.headers}'.split(',')}")
    String[] headersList;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(hostList).allowedMethods(methodList).allowedHeaders(headersList);
    }
}