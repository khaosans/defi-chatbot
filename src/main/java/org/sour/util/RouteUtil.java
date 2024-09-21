package org.sour.util;

import org.springframework.stereotype.Component;

import com.vaadin.flow.router.RouteConfiguration; // Import the Component annotation

@Component // Add this annotation to make RouteUtil a Spring bean
public class RouteUtil {

    public boolean isRouteAllowed(String route) {
        // Check if the route is registered in the current session's route configuration
        return RouteConfiguration.forSessionScope().getRoute(route) != null; // Use getRoute() instead
    }
}