package org.sour.view;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView(LoginForm loginForm) {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER); // Added justify content mode for better alignment
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

    public boolean attemptLogin(String string, String string2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attemptLogin'");
    }
}