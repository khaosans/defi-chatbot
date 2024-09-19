package org.vaadin.marcus.components;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout {

    public Header() {
        // Create a title for the header
        H1 title = new H1("My Application");
        title.getStyle().set("margin", "0");
        title.getStyle().set("color", "#ffffff"); // Set title color

        // Create a gear icon for the dropdown
        Icon gearIcon = new Icon(VaadinIcon.COG);
        gearIcon.getStyle().set("color", "#ffffff"); // Set icon color
        ContextMenu contextMenu = new ContextMenu(gearIcon);
        contextMenu.setOpenOnClick(true);

        // Add logout option to the dropdown
        contextMenu.addItem("Logout", event -> {
            // Logic for logout (e.g., redirect to login page)
            getUI().ifPresent(ui -> {
                // Implement logout logic here, e.g., clear session
                Notification.show("Logging out...");
                ui.navigate("login"); // Redirect to login page
            });
        });

        // Add components to the header layout
        add(title, gearIcon);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setWidthFull();
        
        // Set styles for fixed positioning
        getStyle().set("background-color", "#4A90E2"); // Set background color
        getStyle().set("padding", "10px"); // Set padding
        getStyle().set("position", "fixed"); // Make the header fixed
        getStyle().set("top", "0"); // Stick to the top
        getStyle().set("left", "0"); // Align to the left
        getStyle().set("right", "0"); // Align to the right
        getStyle().set("z-index", "1000"); // Ensure it stays above other content
    }
}