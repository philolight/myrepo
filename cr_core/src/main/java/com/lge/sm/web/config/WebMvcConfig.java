package com.lge.sm.web.config;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Web 연동을 위해 필요한 컨트롤러들을 Bean 객체 형태로 등록하는 클래스
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
/*    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
    	System.out.println("responseBodyConverter()");
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
 
    @Bean
    public Filter characterEncodingFilter() {
    	System.out.println("characterEncodingFilter()");
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }*/
}