package com.tuyongkang.blog.config.server;

import com.tuyongkang.blog.config.SpringRootConfig;
import com.tuyongkang.blog.config.SpringWebConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置 DispatcherServlet
 */
@Order(2)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 此方法返回的带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下文中的Bean
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {SpringRootConfig.class};
    }


    /**
     * 当DispatcherServlet启动的时候，它会创建Spring应用上下文，并加载配置文件或配置类中所声明的Bean。
     * DispatcherServlet加载应用上下文时，使用定义在WebConfig配置类中的Bean
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {SpringWebConfig.class}; // 指定配置类
    }

    /**
     * 将一个或多个路径映射到DispatcherServlet上
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};	// 将dispatcherServlet映射到 "/"
    }

}
