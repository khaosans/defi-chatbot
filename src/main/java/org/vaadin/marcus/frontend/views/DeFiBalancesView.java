package org.vaadin.marcus.frontend.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import org.vaadin.marcus.model.DeFiBalance;
import org.vaadin.marcus.model.TokenBalance;
import org.vaadin.marcus.service.FlightService;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Route("defi-balances")
public class DeFiBalancesView extends VerticalLayout {
    private Grid<DeFiBalance> grid = new Grid<>(DeFiBalance.class);

    public DeFiBalancesView(FlightService flightService) {
        add(new H1("DeFi Account Balances"));
        grid.setItems(convertToDeFiBalances(flightService.getDeFiAccountBalances("user123")));
        grid.addColumn(DeFiBalance::getChain).setHeader("Chain");
        grid.addColumn(DeFiBalance::getToken).setHeader("Token");
        grid.addColumn(DeFiBalance::getBalance).setHeader("Balance (USD)");
        add(grid);
    }

    private List<DeFiBalance> convertToDeFiBalances(Map<String, List<TokenBalance>> balances) {
        List<DeFiBalance> defiBalances = new ArrayList<>();
        balances.forEach((chain, tokens) -> {
            tokens.forEach(tokenBalance -> {
                double usdValue = calculateUsdValue(tokenBalance.getToken(), tokenBalance.getBalance());
                defiBalances.add(new DeFiBalance(chain, tokenBalance.getToken(), usdValue));
            });
        });
        return defiBalances;
    }

    private double calculateUsdValue(String token, double balance) {
        // Mocked USD conversion rates
        Map<String, Double> conversionRates = Map.of(
            "ETH", 2000.0,
            "USDC", 1.0,
            "BNB", 300.0,
            "MATIC", 1.5
        );
        return balance * conversionRates.getOrDefault(token, 0.0);
    }
}