package org.vaadin.marcus.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vaadin.marcus.service.AccountDetails;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
@Service
public class PortfolioService {
    public List<AccountDetails> getAccounts() {
        // This is a mock implementation. Replace with actual data retrieval logic.
        return Arrays.asList(
            new AccountDetails("ACC001", "Savings", "1000.00"),
            new AccountDetails("ACC002", "Checking", "500.00")
        );
    }
}
