package org.qiuyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.qiuyun.identify.dao.mapper")
public class IdentifyApplicationService
{
    public static void main( String[] args )
    {
        SpringApplication.run(IdentifyApplicationService.class,args);
    }
}
