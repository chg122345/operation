/**
 * @author (c) 2018,
 * @date 2018/11/16  15:59
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.config;

import org.jleopard.datam.interceptor.PermissionInter;
import org.jleopard.datam.util.PathUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 系统配置信息
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInter()).addPathPatterns("/**").excludePathPatterns("/login","/register","/layui/**","/public/**");
    }

    /**
     * 请求头映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是存放的真实路径
        registry.addResourceHandler("/avatar/**","/download/**").addResourceLocations("file:"+ PathUtils.getBasePath());
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

    /**
     * 配置编码
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("100MB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
}