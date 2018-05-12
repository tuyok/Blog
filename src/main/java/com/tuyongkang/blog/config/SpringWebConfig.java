package com.tuyongkang.blog.config;

import com.tuyongkang.blog.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // 启用SpringMVC
@ComponentScan(value = AppConstants.CONTROLLER_SCAN)
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
        jspViewResolver.setCache(false); // 解析视图名称是否启用缓存(默认启用)
        jspViewResolver.setSuffix(".jsp");
        jspViewResolver.setPrefix("/WEB-INF/pages/");
        jspViewResolver.setContentType("text/html;charset=utf-8");
        jspViewResolver.setRequestContextAttribute("requestContext");
        return jspViewResolver;
    }

    /**
     * 是否启用容器默认Servlet处理器
     * 配置静态资源的处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置匹配规则
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

}
