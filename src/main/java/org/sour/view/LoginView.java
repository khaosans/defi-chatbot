package org.sour.view;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("login")
public class LoginView extends VerticalLayout {

    private final LoginForm loginForm;

    @Autowired
    public LoginView(LoginForm loginForm) {
        this.loginForm = loginForm;
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e.getUsername(), e.getPassword());
            if (isAuthenticated) {
                Notification.show("Login successful!");
                // Navigate to the main view
            } else {
                Notification.show("Login failed. Please check your username and password and try again.");
                loginForm.setError(true);
            }
        });

        add(loginForm);
    }

    protected boolean authenticate(String username, String password) {
        // Implement authentication logic here
        return "user".equals(username) && "password".equals(password);
    }
}