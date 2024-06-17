package org.qiuyun.framework.starter.bases.config;

import org.qiuyun.framework.starter.bases.ApplicationContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author qiuyun
 * @version 1.0
 * Create by 2024/6/4 9:18
 */

public class ApplicationBaseAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHolder congoApplicationContextHolder() {
        return new ApplicationContextHolder();
    }
}
