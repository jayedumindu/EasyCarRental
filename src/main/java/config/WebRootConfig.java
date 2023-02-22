package config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.impl.carServiceImpl;
import service.impl.driverServiceImpl;
import service.impl.userServiceImpl;

@Configuration
@Import(JPAConfig.class)
@ComponentScan(basePackageClasses = {userServiceImpl.class, carServiceImpl.class, driverServiceImpl.class})
public class WebRootConfig {
    public WebRootConfig(){
        System.out.println("WebRootConfig : instantiated");
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
