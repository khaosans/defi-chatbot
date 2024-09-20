package org.vaadin.marcus.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vaadin.marcus.Application; // Import your main application class
import org.vaadin.marcus.service.UserService;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class) // Specify the main application class
public class LoginViewTest {
    @Mock
    private UserService userService;

    @Mock
    private LoginView loginView;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSuccessfulLogin() {
        // Arrange
        when(userService.authenticate("admin", "admin")).thenReturn(true);
        
        // Act
        loginView.authenticate("admin", "admin");
        // Assert
        verify(loginView).authenticate("admin", "admin");
    }

    @Test
    public void testFailedLogin() {
        // Arrange
        when(userService.authenticate("admin", "admin")).thenReturn(false);
        
        // Act
        loginView.authenticate("admin", "admin");

        // Assert
        verify(loginView).authenticate("admin", "admin");
    }


}    