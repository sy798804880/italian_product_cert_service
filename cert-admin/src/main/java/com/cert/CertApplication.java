package com.cert;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 启动程序
 *
 * @author Lion Li
 */

@SpringBootApplication
public class CertApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CertApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  com-Vue-Plus启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
