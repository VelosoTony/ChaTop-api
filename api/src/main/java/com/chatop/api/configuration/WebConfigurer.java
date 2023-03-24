package com.chatop.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Value("${upload.folder}")
  private String UPLOAD_FOLDER;
  
  // Add new resource to allow accessing to uploaded images http://<hostname>:<port>/<UPLOAD_FOLDER>/
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
      registry.addResourceHandler(String.format("%s/**",UPLOAD_FOLDER))
      .addResourceLocations(String.format("file://%s/",UPLOAD_FOLDER));
    }
  }