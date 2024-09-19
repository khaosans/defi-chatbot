package org.vaadin.marcus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.dependency.CssImport;

@SpringBootApplication
@CssImport("./styles/shared-styles.css") // Ensure you have your styles imported
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
