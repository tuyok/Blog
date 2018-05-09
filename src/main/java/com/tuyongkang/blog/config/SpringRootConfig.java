package com.tuyongkang.blog.config;

import com.tuyongkang.blog.AppConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value={
        AppConstants.EXT_CONFIG_SCAN,
        AppConstants.SERVICE_SCAN,
})
public class SpringRootConfig {

}
