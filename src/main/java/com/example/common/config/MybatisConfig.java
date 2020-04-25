package com.example.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public PaginationInterceptor PaginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        //设置请求页面大于最大页面操作，true调回到首页，false继续请求，默认false
        interceptor.setOverflow(true);
        //设置最大单页限制数量，默认500条，-1不影响
        interceptor.setLimit(-1);
        return interceptor;
    }
}
