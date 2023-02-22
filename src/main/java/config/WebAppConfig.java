package config;

import advisor.AppWideExceptionHandler;
import controller.carController;
import controller.driverController;
import controller.mainController;
import controller.userController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {AppWideExceptionHandler.class, mainController.class, userController.class, carController.class, driverController.class})
public class WebAppConfig {
    public WebAppConfig() {
        System.out.println("web-config instantiated");
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }

}
