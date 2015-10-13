package org.feuyeux.restful;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .showBanner(false)
                .sources(DemoApplication.class)
                .run();
    }
}
