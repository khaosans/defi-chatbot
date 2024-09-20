// src/main/java/org/vaadin/marcus/Application.java

package org.vaadin.marcus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.vaadin.marcus")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}