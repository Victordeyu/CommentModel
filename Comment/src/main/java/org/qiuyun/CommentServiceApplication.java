package org.qiuyun;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.qiuyun.comment.dao.mapper")
public class CommentServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CommentServiceApplication.class,args);
    }
}
