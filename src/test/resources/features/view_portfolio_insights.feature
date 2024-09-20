Feature: View Portfolio Insights

  As a user of the SourBot DeFi portfolio management system
  I want to view insights about my portfolio
  So that I can make informed decisions about my investments

  Scenario: View portfolio insights
    Given the user is logged in to the SourBot DeFi dashboard
    When the user requests to view their portfolio insights
    Then the system should display the following information:
      | Insight Type               | Description                                            |
      | Total Portfolio Value      | The total value of the portfolio in USD                |
      | Portfolio Diversification  | The distribution of assets across different chains     |
      | Best Performing Asset      | The asset with the highest value in the portfolio      |
      | Staking Opportunities      | Suggestions for tokens that can be staked for rewards  |
    And the insights should be based on the user's current portfolio data
    And the insights should provide actionable recommendations

  Scenario: Refresh portfolio insights
    Given the user is viewing their portfolio insights
    When the user clicks the refresh button
    Then the system should fetch the latest portfolio data
    And update the displayed insights accordingly

  Scenario: Handle empty portfolio
    Given the user has no assets in their portfolio
    When the user requests to view their portfolio insights
    Then the system should display a message indicating the portfolio is empty
    And provide general advice on getting started with DeFi investments