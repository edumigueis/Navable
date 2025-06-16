package com.unicamp.navable_api;

import com.unicamp.navable_api.utils.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CorsConfiguration.class, SecurityConfig.class})
public class NavableApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavableApiApplication.class, args);
    }

}
