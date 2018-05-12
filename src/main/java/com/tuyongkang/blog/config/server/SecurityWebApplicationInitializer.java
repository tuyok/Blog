package com.tuyongkang.blog.config.server;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 配置spring security
 */
@Order(3)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
