package com.tuyongkang.blog.config.server;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Conventions;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * 启动器
 * 配置 servlet 和 filter
 */
@Order(1)
public class CommonWebInitializer implements WebApplicationInitializer {

    private Logger logger = LoggerFactory.getLogger(CommonWebInitializer.class);

//    private ServletRegistration.Dynamic servletRegistration = null;
    private FilterRegistration.Dynamic filterRegistration = null;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("container startup ...");

        //配置 DispatcherServlet,通过WebAppInitializer实现AbstractAnnotationConfigDispatcherServletInitializer配置
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        servletRegistration = servletContext.addServlet(Conventions.getVariableName(dispatcherServlet),dispatcherServlet);
//        servletRegistration.setLoadOnStartup(1);
//        servletRegistration.addMapping("/");

        //配置编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        filterRegistration = servletContext.addFilter(Conventions.getVariableName(characterEncodingFilter),characterEncodingFilter);
        filterRegistration.setAsyncSupported(true);
        filterRegistration.addMappingForUrlPatterns(getDispatcherTypes(),false,"/*");

        //配置监听器
        servletContext.addListener(new IntrospectorCleanupListener());
//        servletContext.addListener(new QuartzInitializerListener());    //Quartz 初始化监听器
    }


    private EnumSet<DispatcherType> getDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC);
    }
}
