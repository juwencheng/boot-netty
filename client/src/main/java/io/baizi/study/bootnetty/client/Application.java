package io.baizi.study.bootnetty.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 7:40 下午
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
}
