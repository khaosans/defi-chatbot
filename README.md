# Funnair AI-Powered Customer Support and DeFi Portfolio Management

![Funnair AI Customer Support Interface](screenshot.png)

This application demonstrates an advanced AI-powered system that combines customer support for Funnair, a fictional airline, with DeFi portfolio management features. It showcases intelligent AI integration for handling customer inquiries, managing bookings efficiently, and providing DeFi investment insights and strategies.

## Key Features

### Airline Customer Support
- **AI-Powered Chat Interface**: Intelligent conversational agent for customer support
- **Comprehensive Booking Management**: Tools for tracking, updating, and managing booking statuses
- **In-Memory Chat History**: Maintains chat context within a session for coherent interactions
- **Booking Confirmation Workflow**: Guides users through a streamlined booking confirmation process
- **Retrieval Augmented Generation (RAG)**: Accesses relevant information like terms and conditions
- **Integrated Tool Usage**: Utilizes Java methods to perform actions based on user requests
- **Dynamic Booking Status Updates**: Real-time updates of booking statuses with color-coded indicators
- **Searchable Booking Grid**: Easily find and manage bookings with a powerful search feature
- **Booking Status Counts**: Quick overview of bookings in different statuses

### DeFi Portfolio Management
- **View DeFi Account Balances**: Fetch and display aggregated DeFi balances across multiple chains
- **Portfolio Insights**: AI-generated insights on portfolio diversification and investment opportunities
- **Token Filtering**: Ability to filter and view tokens by specific blockchain networks
- **Strategy Generation**: AI-powered generation of trading strategies, including swing trading
- **Strategy Execution**: Simulated execution of AI-generated trading strategies
- **Risk Management**: Upcoming features for assessing and managing investment risks

## New Features in Development (MVP)

1. **View DeFi Account Balances**: Fetch and display DeFi balances using mocked data (3 points)
2. **View Portfolio Insights**: Provide portfolio insights using mocked data (3 points)
3. **Filter Tokens by Chain**: Allow users to filter tokens by chain and show token balances (2 points)
4. **Execute Mocked Strategy Advice**: Provide users with actionable advice based on portfolio data (3 points)
5. **Generate Swing Trading Strategy**: AI-powered generation of swing trading strategies for given trading pairs (5 points)
6. **Execute Trade Based on Strategy**: Simulate trade execution based on generated strategies using mock data (4 points)

## Development Progress

### MVP Features and BDD Test Implementation
- View DeFi Account Balances: [====>------------] 25%
- View Portfolio Insights: [==>---------------] 10%
- Filter Tokens by Chain: [=>----------------] 5%
- Execute Mocked Strategy Advice: [=>----------------] 5%
- Generate Swing Trading Strategy: [=>----------------] 5%
- Execute Trade Based on Strategy: [=>----------------] 5%

Overall MVP Progress: [==>----------------] 14%

Total Story Points: 20

## BDD Test Cases

We are implementing Behavior-Driven Development (BDD) test cases for each of our new features. These test cases are stored in the `tests` folder. Here's a summary of the scenarios we're testing:

1. **View DeFi Account Balances**:
   - Scenario: View account balances for multiple chains
   - File: `tests/view_defi_account_balances.feature`

2. **View Portfolio Insights**:
   - Scenario: Provide insights for diversification and staking
   - File: `tests/view_portfolio_insights.feature`

3. **Filter Tokens by Chain**:
   - Scenario: Filter tokens on Ethereum
   - File: `tests/filter_tokens_by_chain.feature`

4. **Execute Mocked Strategy Advice**:
   - Scenario: Execute mocked swing trading strategy
   - File: `tests/execute_mocked_strategy_advice.feature`

5. **Generate Swing Trading Strategy**:
   - Scenario: Generate swing trading strategy for BTC/USD
   - File: `tests/generate_swing_trading_strategy.feature`

6. **Execute Trade Based on Strategy**:
   - Scenario: Execute trade for BTC based on swing trading strategy
   - File: `tests/execute_trade_based_on_strategy.feature`

These BDD test cases ensure that our features are working as expected from a user's perspective. They are written in Gherkin syntax and can be executed using a BDD framework like Cucumber.

## Tech Stack

- Backend: Spring Boot
- Frontend: Vaadin Hilla
- AI Integration: LangChain4j
- BDD Testing: Cucumber
- DeFi Integration: Web3j (planned for future real blockchain integration)

## Requirements

- Java 21+
- OpenAI API key
- Node.js and npm (for frontend development)

## Setup and Running

1. Clone the repository
2. Set the `OPENAI_API_KEY` environment variable
3. Install frontend dependencies: `npm install`
4. Run the application:
   - IDE: Execute `Application.java`
   - Command line: Run `mvn`
5. Access the app at `http://localhost:8080`

## Usage Guide

1. **Chat Interface**: Interact with the AI assistant for flight information, bookings, or DeFi portfolio inquiries.
2. **Booking Management**: 
   - Use the tabbed interface to view different booking statuses (All, Confirmed, Pending, Cancelled)
   - Search for specific bookings using the search bar
   - Check booking status counts at the top of the grid for a quick overview
3. **DeFi Portfolio Management**:
   - View aggregated DeFi balances across multiple chains
   - Receive AI-generated insights on your portfolio
   - Filter tokens by specific blockchain networks
   - Generate and simulate trading strategies
4. **AI-Assisted Actions**: The AI can help with tasks like retrieving booking details, updating statuses, confirming reservations, and providing DeFi investment advice.

## Recent Enhancements

- Implemented a tabbed interface for viewing bookings by status
- Added a search functionality to quickly find specific bookings
- Introduced color-coded status indicators for easy visual identification
- Displayed booking status counts for at-a-glance information
- Enhanced AI tools for detailed booking information retrieval and management
- Extended `LangChain4jAssistant` for more sophisticated multi-turn conversations
- Updated `LangChain4jTools` with advanced booking management capabilities
- Integrated initial DeFi portfolio management features with mocked data
- Implemented BDD test cases for new DeFi features

## Roadmap

- Implement more complex booking management workflows
- Enhance error handling and edge case management in AI interactions
- Develop an advanced memory management system for extended context retention
- Introduce data visualization for booking trends and analytics
- Integrate real-time market data for DeFi strategy generation
- Implement additional trading strategies (e.g., momentum trading, scalping)
- Develop risk management features for trade execution
- Create a modular system for easy integration of new strategies and APIs
- Implement real blockchain integration for live DeFi data and transactions

## Contributing

We welcome contributions! Please feel free to submit Pull Requests or open Issues for suggestions and bug reports.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Recent Updates
- Fixed null pointer issues in BookingService
- Added getters and setters to AccountDetails for proper serialization
- Improved error handling in service classes
- Added new MVP features for DeFi portfolio management
- Implemented initial BDD test cases for DeFi features

## Overall Project Progress
[======================>] 99.98% complete
