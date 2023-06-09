package com.realcoders.realcoderlinkedinspring3.web.security.filter;

import com.realcoders.realcoderlinkedinspring3.storage.repository.user.UserRepository;
import com.realcoders.realcoderlinkedinspring3.web.security.jwt.JwtService;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterConfig {


    private  final JwtService jwtService;

    private final UserRepository userRepository;

    public JwtFilterConfig(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtAuthenticationFilter(jwtService,userRepository));
        filterRegistrationBean.addUrlPatterns("/companies/dummy");
        return filterRegistrationBean;
    }
}
