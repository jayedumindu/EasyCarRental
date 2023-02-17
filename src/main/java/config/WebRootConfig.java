package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JPAConfig.class)
public class WebRootConfig {
    public WebRootConfig(){
        System.out.println("WebRootConfig : instantiated");
    }
}
