package com.longsalix.web;

import com.longsalix.web.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:db.properties"})
public class Configurations {
    
    @Bean
    public UserService buildUserService(@Value("${server.port}") String port) {
        return new UserService();
    }

}
