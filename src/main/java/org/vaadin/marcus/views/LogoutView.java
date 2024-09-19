package org.vaadin.marcus.views;


import org.vaadin.marcus.controllers.LoginController;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route; // Import the LoginController

@Route("logout")
public class LogoutView extends VerticalLayout {
    private final LoginController loginController;

    public LogoutView(LoginController loginController) {
        this.loginController = loginController;
        // Invalidate session or perform logout logic here
        // For example, clear user session or tokens
        

        // Redirect to the login page
        getUI().ifPresent(ui -> ui.navigate("login"));
    }
}