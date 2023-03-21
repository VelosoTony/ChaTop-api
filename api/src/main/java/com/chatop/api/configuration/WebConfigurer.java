package com.chatop.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  
  
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/images/**")
      .addResourceLocations("file:///images/");
    }
  }