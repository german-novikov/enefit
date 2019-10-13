package com.german.novikov.enefit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.german.novikov.enefit")
@EntityScan(basePackages = {"com.german.novikov.enefit.model"} )
@EnableJpaRepositories(basePackages = {"com.german.novikov.enefit.repository"})
public class EnefitApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EnefitApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }

}
