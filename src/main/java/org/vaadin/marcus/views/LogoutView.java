package org.vaadin.marcus.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.vaadin.marcus.controllers.LoginController; // Import the LoginController

@Route("logout")
public class LogoutView extends Div {
    private final LoginController loginController;

    public LogoutView(LoginController loginController) {
        this.loginController = loginController;
        // Invalidate session or perform logout logic here
        // For example, clear user session or tokens

        // Redirect to the login page
        getUI().ifPresent(ui -> ui.navigate("login"));
    }
}