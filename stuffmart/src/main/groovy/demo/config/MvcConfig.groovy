package demo.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = [ 'demo.controller', 'demo.service'])
class MvcConfig implements WebMvcConfigurer {


    void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**", "/css/**", "/js/**")
                .addResourceLocations(
                "classpath:/static/img/",
                                "classpath:/static/css/",
                                "classpath:/static/js/")


    }


}