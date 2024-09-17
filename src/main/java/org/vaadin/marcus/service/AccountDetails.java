package org.vaadin.marcus.service;

public interface AccountDetails {
    String getId();
    String getCurrency();
    double getBalance();
    double getAvailable();
    double getHold();
    String getProfileId();
}