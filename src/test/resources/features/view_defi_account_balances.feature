Feature: View DeFi Account Balances

  As a user of the SourBot DeFi portfolio management system
  I want to view my DeFi account balances across multiple chains
  So that I can have a clear overview of my cryptocurrency holdings

  Scenario: View account balances for multiple chains
    Given the user is logged in to the SourBot DeFi dashboard
    When the user requests to view their DeFi account balances
    Then the system should display the following information:
      | Chain    | Token | Balance |
      | Ethereum | ETH   | 10.5    |
      | Ethereum | USDC  | 1000    |
      | Binance  | BNB   | 5.2     |
      | Polygon  | MATIC | 500     |
    And the total portfolio value in USD should be displayed
    And each balance should have a corresponding USD value

  Scenario: Refresh account balances
    Given the user is viewing their DeFi account balances
    When the user clicks the refresh button
    Then the system should fetch the latest balance information
    And update the displayed balances accordingly

  Scenario: Handle connection error
    Given the user is logged in to the SourBot DeFi dashboard
    And there is a connection issue with the blockchain data provider
    When the user requests to view their DeFi account balances
    Then the system should display an error message
    And provide an option to retry the balance fetch