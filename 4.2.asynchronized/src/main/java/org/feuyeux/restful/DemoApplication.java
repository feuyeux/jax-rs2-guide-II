package org.feuyeux.restful;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .sources(DemoApplication.class)
                .run();
    }
}
