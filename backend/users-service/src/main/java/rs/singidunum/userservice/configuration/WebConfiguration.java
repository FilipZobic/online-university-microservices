package rs.singidunum.userservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
      .setConnectTimeout(Duration.ofMillis(5000))
      .setReadTimeout(Duration.ofMillis(5000))
      .build();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:3000","http://localhost:4200") // React, Angular
      .allowedMethods("GET", "POST", "PUT", "DELETE");
    WebMvcConfigurer.super.addCorsMappings(registry);
  }

  @Bean(name="messageSource")
  public ResourceBundleMessageSource bundleMessageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }
}
