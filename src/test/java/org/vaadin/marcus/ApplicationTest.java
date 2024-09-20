package org.vaadin.marcus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private Application application; // Ensure the application is properly injected

    @Test
    public void contextLoads() {
        
    }
}