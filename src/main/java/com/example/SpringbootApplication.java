package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*启用事务管理器*/
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@MapperScan("com.example.*.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  涛哥项目启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.    ==================       \n" +
                " |        \\           ||           \n" +
                " |         \\          ||           \n" +
                " |         //         ||            \n" +
                " |        //          ||            \n" +
                " |       //           ||            \n" +
                " |      //            ||           \n" +
                " |     //             ||            \n" +
                "  ----                ||            ");
    }

}
