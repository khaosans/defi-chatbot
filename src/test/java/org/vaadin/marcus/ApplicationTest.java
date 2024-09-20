package org.vaadin.marcus;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // No need to exclude anything here
public class ApplicationTest {

    @Autowired
    private Application application;

    @Test
    public void contextLoads() {
        assertNotNull(application, "Application should be loaded");
    }
}