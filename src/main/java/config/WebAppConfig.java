package config;

import advisor.AppWideExceptionHandler;
import controller.customerController;
import controller.mainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {AppWideExceptionHandler.class, mainController.class})
public class WebAppConfig {
    public WebAppConfig() {
        System.out.println("web-config instantiated");
    }
//    @Bean
//    public ViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//        bean.setPrefix("/");
//        bean.setSuffix(".html");
//        return bean;
//    }
}
