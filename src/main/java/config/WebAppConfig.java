package config;

import advisor.AppWideExceptionHandler;
import controller.mainController;
import controller.userController;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {AppWideExceptionHandler.class, mainController.class, userController.class})
public class WebAppConfig {
    public WebAppConfig() {
        System.out.println("web-config instantiated");
    }

}
