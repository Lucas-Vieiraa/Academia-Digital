package me.dio.academia.academiadigital.Configurations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class ModelMapperConfig {
   @Bean
   public ModelMapper modelMapper() {
   return new ModelMapper();
   }
}

