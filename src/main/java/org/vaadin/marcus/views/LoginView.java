package org.vaadin.marcus.views;

import org.vaadin.marcus.controller.LoginController;
import org.vaadin.marcus.langchain4j.LangChain4jTools;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox; // Import Checkbox
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;

@Route("login") // Ensure this is correct
public class LoginView extends VerticalLayout {
    private final LangChain4jTools langChain4jTools;
    private final LoginController loginController;

    public LoginView(LangChain4jTools langChain4jTools, LoginController loginController) {
        this.langChain4jTools = langChain4jTools;
        this.loginController = loginController;

        // UI components for login
        H1 title = new H1("Login to Your Account"); // Added title for better UX
        title.addClassName("lumo-header"); // Apply Lumo header style
        TextField usernameField = new TextField("Username");
        usernameField.addClassName("lumo-text-field mb-4"); // Apply Lumo text field style and margin
        PasswordField passwordField = new PasswordField("Password");
        passwordField.addClassName("lumo-text-field mb-4"); // Apply Lumo text field style and margin
        Checkbox rememberMeCheckbox = new Checkbox("Remember Me"); // Added Remember Me checkbox
        Button loginButton = new Button("Login", event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            if (loginController.login(username, password)) {
                Notification.show("Login successful!", 3000, Notification.Position.MIDDLE);
                // Store session ID in local storage if Remember Me is checked
                if (rememberMeCheckbox.getValue()) {
                    String sessionId = loginController.getCurrentSessionId(); // Get the current session ID
                    // Store session ID in local storage (or cookies)
                    getUI().ifPresent(ui -> ui.getPage().executeJs("localStorage.setItem('sessionId', $0)", sessionId));
                }
                getUI().ifPresent(ui -> ui.navigate("chat")); // Navigate to chat view
            } else {
                Notification.show("Invalid credentials! Please try again.", 3000, Notification.Position.MIDDLE);
            }
        });
        loginButton.addClassName("lumo-button"); // Apply Lumo button style
        loginButton.setAriaLabel("Login to your account"); // Set ARIA label for accessibility

        add(title, usernameField, passwordField, rememberMeCheckbox, loginButton);
    }
}