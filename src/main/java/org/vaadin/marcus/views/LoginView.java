package org.vaadin.marcus.views;

import org.vaadin.marcus.controllers.LoginController; // Import the LoginController
import org.vaadin.marcus.langchain4j.LangChain4jTools;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("login") // Ensure this is correct
public class LoginView extends VerticalLayout {
    private final LangChain4jTools langChain4jTools;
    private final LoginController loginController;

    public LoginView(LangChain4jTools langChain4jTools, LoginController loginController) {
        this.langChain4jTools = langChain4jTools;
        this.loginController = loginController;

        // UI components for login
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login", event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            if (loginController.login(username, password)) {
                Notification.show("Login successful!");
                getUI().ifPresent(ui -> ui.navigate("chat")); // Navigate to chat view
            } else {
                Notification.show("Invalid credentials!", 3000, Notification.Position.MIDDLE);
            }
        });

        add(usernameField, passwordField, loginButton);
    }
}