 ____                  ____        _   
/ ___|  ___  _   _ _ _| __ )  ___ | |_ 
\___ \ / _ \| | | | '__|  _ \ / _ \| __|
 ___) | (_) | |_| | |  | |_) | (_) | |_ 
|____/ \___/ \__,_|_|  |____/ \___/ \__|

# AI-Powered Customer Support and DeFi Portfolio Management

SourBot: AI-Powered Customer Support and DeFi Portfolio Management

This application demonstrates an advanced AI-powered system that combines customer support for SourBot, a fictional AI assistant, with DeFi portfolio management features. It showcases intelligent AI integration for handling customer inquiries, managing tasks efficiently, and providing DeFi investment insights and strategies.

## Key Features

### AI-Powered Customer Support
- **AI-Powered Chat Interface:** Intelligent conversational agent for customer support
- **Comprehensive Task Management:** Tools for tracking, updating, and managing task statuses
- **In-Memory Chat History:** Maintains chat context within a session for coherent interactions
- **Task Confirmation Workflow:** Guides users through a streamlined task confirmation process
- **Retrieval Augmented Generation (RAG):** Accesses relevant information like terms and conditions
- **Integrated Tool Usage:** Utilizes Java methods to perform actions based on user requests
- **Dynamic Task Status Updates:** Real-time updates of task statuses with color-coded indicators
- **Searchable Task Grid:** Easily find and manage tasks with a powerful search feature
- **Task Status Counts:** Quick overview of tasks in different statuses

### DeFi Portfolio Management
- **View DeFi Account Balances:** Fetch and display aggregated DeFi balances across multiple chains
- **Portfolio Insights:** AI-generated insights on portfolio diversification and investment opportunities
- **Token Filtering:** Ability to filter and view tokens by specific blockchain networks
- **Strategy Generation:** AI-powered generation of trading strategies, including swing trading
- **Strategy Execution:** Simulated execution of AI-generated trading strategies
- **Risk Management:** Upcoming features for assessing and managing investment risks

### New Features in Development (MVP)
1. **View DeFi Account Balances:** Fetch and display DeFi balances using mocked data (3 points) [===========>----] 60%
2. **View Portfolio Insights:** Provide portfolio insights using mocked data (3 points) [========>---------] 45%
3. **Filter Tokens by Chain:** Allow users to filter tokens by chain and show token balances (2 points) [========>---------] 45%
4. **Execute Mocked Strategy Advice:** Provide users with actionable advice based on portfolio data (3 points) [=>----------------] 5%
5. **Generate Swing Trading Strategy:** AI-powered generation of swing trading strategies for given trading pairs (5 points) [=>----------------] 5%
6. **Execute Trade Based on Strategy:** Simulate trade execution based on generated strategies using mock data (4 points) [=>----------------] 5%

#### Development Progress
- **View DeFi Account Balances:** [===========>----] 60%
- **View Portfolio Insights:** [========>---------] 45%
- **Filter Tokens by Chain:** [========>---------] 45%
- **Execute Mocked Strategy Advice:** [=>----------------] 5%
- **Generate Swing Trading Strategy:** [=>----------------] 5%
- **Execute Trade Based on Strategy:** [=>----------------] 5%
- **Overall MVP Progress:** [=====>-------------] 33%
- **Total Story Points:** 20

### BDD Test Cases
We are implementing Behavior-Driven Development (BDD) test cases for each of our new features. These test cases are stored in the `tests` folder. Here's a summary of the scenarios we're testing:

1. **View DeFi Account Balances:**
   - **Scenario:** View account balances for multiple chains
   - **File:** `tests/view_defi_account_balances.feature`
2. **View Portfolio Insights:**
   - **Scenario:** Provide insights for diversification and staking
   - **File:** `tests/view_portfolio_insights.feature`
3. **Filter Tokens by Chain:**
   - **Scenario:** Filter tokens on Ethereum
   - **File:** `tests/filter_tokens_by_chain.feature`
4. **Execute Mocked Strategy Advice:**
   - **Scenario:** Execute mocked swing trading strategy
   - **File:** `tests/execute_mocked_strategy_advice.feature`
5. **Generate Swing Trading Strategy:**
   - **Scenario:** Generate swing trading strategy for BTC/USD
   - **File:** `tests/generate_swing_trading_strategy.feature`
6. **Execute Trade Based on Strategy:**
   - **Scenario:** Execute trade for BTC based on swing trading strategy
   - **File:** `tests/execute_trade_based_on_strategy.feature`

These BDD test cases ensure that our features are working as expected from a user's perspective. They are written in Gherkin syntax and can be executed using a BDD framework like Cucumber.

## AI Disclosure

This project utilizes artificial intelligence (AI) technologies, including but not limited to:

- **Natural Language Processing (NLP):** For understanding and generating human-like text
- **Machine Learning models:** For portfolio analysis and strategy generation
- **AI-assisted code generation and optimization**

While AI enhances the capabilities of this application, all critical decisions and financial advice should be verified by human experts. The AI components are tools to assist and augment human decision-making, not to replace it entirely.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

**MIT License**

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Disclaimer

This software is for educational and demonstration purposes only. It is not intended for use in real-world financial transactions or as a substitute for professional financial advice. Always consult with a qualified financial advisor before making investment decisions.

## Tech Stack

- **Backend:** Spring Boot
- **Frontend:** Vaadin Hilla
- **AI Integration:** LangChain4j
- **BDD Testing:** Cucumber
- **DeFi Integration:** Web3j (planned for future real blockchain integration)

## Requirements

- **Java 21+**
- **OpenAI API key**
- **Node.js and npm** (for frontend development)

## Setup and Running

1. **Clone the repository**
2. **Set the `OPENAI_API_KEY` environment variable**
3. **Set the `DEBANK_API_KEY` environment variable**
4. **Install frontend dependencies:**
   ```bash
   npm install
   ```
5. **Run the application:**
   - **IDE:** Execute `Application.java`
   - **Command line:** Run `mvn spring-boot:run`
6. **Access the app** at `http://localhost:8080`

## Usage Guide

1. **Chat Interface:** Interact with SourBot for task information, management, or DeFi portfolio inquiries.
2. **Web3 Integration:**
   - Use the **Connect Wallet** button to connect your Web3 wallet (e.g., MetaMask).
   - The button will display the connected wallet address once successfully connected.
3. **Task Management:** 
   - Use the tabbed interface to view different task statuses (All, In Progress, Completed, Cancelled).
   - Search for specific tasks using the search bar.
   - Check task status counts at the top of the grid for a quick overview.
   - View aggregated DeFi balances across multiple chains.
   - Receive AI-generated insights on your portfolio.
   - Filter tokens by specific blockchain networks.
   - Generate and simulate trading strategies.
4. **AI-Assisted Actions:** SourBot can help with tasks like retrieving task details, updating statuses, confirming completions, and providing DeFi investment advice.

## Recent Enhancements

- Implemented a tabbed interface for viewing bookings by status
- Added a search functionality to quickly find specific bookings
- Introduced color-coded status indicators for easy visual identification
- Displayed booking status counts for a quick overview
- Enhanced AI tools for detailed booking information retrieval and management
- Extended `LangChain4jAssistant` for more sophisticated multi-turn conversations
- Updated `LangChain4jTools` with advanced booking management capabilities
- Integrated initial DeFi portfolio management features with mocked data
- Implemented BDD test cases for new DeFi features
- **Implemented "View DeFi Account Balances" feature**
- **Created `TokenBalance` and `DeFiBalance` models**
- **Updated `FlightService` and `LangChain4jTools` with DeFi balance retrieval methods**

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

## Future Features and Enhancements

### 1. Real-Time Error Handling and Notifications
- **Feature:** Provide real-time notifications for any errors in the system or failed API calls (e.g., if live data from Coinbase or CoinGecko fails).
- **Benefit:** This will make error management smoother and ensure that users are always informed about potential issues or downtime.
- **Implementation:** Use a notification system (e.g., WebSocket or push notifications) to alert users in real-time. Integrate with monitoring tools to detect and report errors.

### 2. Advanced Data Visualization for DeFi
- **Feature:** Introduce dynamic data visualizations for your DeFi portfolio, showing live price movements, trading volumes, and potential investment opportunities in a chart form.
- **Benefit:** Helps users get a better grasp of their assets, risks, and opportunities, improving the decision-making process.
- **Implementation:** Use charting libraries like D3.js or Chart.js to create interactive and real-time visualizations. Integrate with APIs to fetch live data.

### 3. Multi-Strategy Support for Trading
- **Feature:** Allow the AI to generate multiple types of trading strategies (e.g., swing trading, momentum trading, and day trading) simultaneously for different pairs.
- **Benefit:** Provides users with diverse strategies to choose from based on their preferences and current market conditions.
- **Implementation:** Extend the AI model to support various trading strategies. Allow users to select and customize strategies based on their needs.

### 4. Predictive AI Models for Market Trends
- **Feature:** Integrate predictive AI models to forecast market trends, using historical data and patterns.
- **Benefit:** Helps users make more informed decisions and gives early signals on when to buy or sell.
- **Implementation:** Use machine learning models like LSTM or ARIMA for time series forecasting. Train models on historical market data and integrate predictions into the user interface.

### 5. AI-Driven Task Automation
- **Feature:** Allow users to set up custom automated workflows with the AI agents that will automatically trigger certain tasks when predefined conditions are met (e.g., send reports or execute a trade when conditions match).
- **Benefit:** Reduces manual intervention and allows users to automate recurring tasks or conditions.
- **Implementation:** Create a workflow engine that allows users to define triggers and actions. Integrate with the AI agents to execute tasks based on the defined workflows.

### 6. Portfolio Rebalancing Tool
- **Feature:** Implement an AI-based rebalancing tool that can suggest or execute portfolio rebalancing based on user-defined risk tolerance and market conditions.
- **Benefit:** Ensures that the user's portfolio remains optimal without too much manual oversight.
- **Implementation:** Develop algorithms to analyze portfolio composition and suggest rebalancing actions. Allow users to set risk tolerance and preferences.

### 7. Advanced User Permissions
- **Feature:** Provide role-based access control, allowing certain users more permissions (e.g., executing trades) and others to have read-only access.
- **Benefit:** Increases security and lets different stakeholders access only what they need.
- **Implementation:** Implement role-based access control (RBAC) in the backend. Define roles and permissions and enforce them in the application.

### 8. Customizable Alerts for Market Movements
- **Feature:** Enable users to set up specific alerts (e.g., price crosses a certain threshold or a strategy hits a certain profit/loss percentage) and customize how they are notified (email, SMS, in-app, etc.).
- **Benefit:** Helps users stay on top of market changes without needing to constantly monitor their portfolio.
- **Implementation:** Create an alert system that allows users to define conditions and notification methods. Integrate with third-party services for email and SMS notifications.

### 9. Historical Performance Analysis
- **Feature:** Implement a feature that allows users to review their past trades and performance over a period of time. This can include statistics like win rate, average return, and risk-adjusted performance metrics.
- **Benefit:** Users can analyze their performance and refine their trading strategies based on their history.
- **Implementation:** Store historical trade data and develop analytics tools to calculate performance metrics. Provide visualizations and reports to help users understand their trading history.

### 10. Risk Profiling and Tolerance Configuration
- **Feature:** Allow users to configure their risk tolerance, and have the AI adjust its recommendations or trading strategies accordingly.
- **Benefit:** Customizes strategies to fit user risk profiles, ensuring that the AI suggestions match their preferences.
- **Implementation:** Develop a risk profiling tool that assesses user risk tolerance through questionnaires and historical data analysis. Integrate this tool with the AI to tailor recommendations and strategies.

## Contributing

We welcome contributions! Please feel free to submit Pull Requests or open Issues for suggestions and bug reports.

## Overall Project Progress

[======================>] 99.98% complete

## Project Structure

The project follows a modular structure to separate concerns and improve maintainability:

```
/client/
  /components/       # Reusable UI components created with Vaadin
  /views/            # Vaadin views representing different screens or pages of the application
  /assets/           # Static assets like images, fonts, and other resources used by the Vaadin UI
/data/
  /models/           # Define data models and schemas, annotated according to lang4j conventions
  /migrations/       # Database migration scripts
  /repositories/     # Data access layer to interact with the database
/config/
  /env/              # Environment-specific configuration files (e.g., development, production)
  /settings/         # General configuration settings for the project
/services/
  /api/              # API integration services
  /business/         # Core business logic and service implementations
  /utils/            # Utility functions and helpers
/agents/
  /tasks/            # Specific tasks or jobs assigned to agents
  /models/           # AI model configurations and custom models used by agents
  /orchestration/    # Workflow orchestration files, defining how agents interact
/tests/
  /unit/             # Unit tests for individual components and functions
  /integration/      # Tests that ensure different parts of the system work together
  /e2e/              # End-to-end tests that simulate real user scenarios
```

### BDD Test Cases

We are implementing Behavior-Driven Development (BDD) test cases for each of our new features. These test cases are stored in the `tests` folder. Here's a summary of the scenarios we're testing:

1. **View DeFi Account Balances:**
   - **Scenario:** View account balances for multiple chains
   - **File:** `tests/view_defi_account_balances.feature`
2. **View Portfolio Insights:**
   - **Scenario:** Provide insights for diversification and staking
   - **File:** `tests/view_portfolio_insights.feature`
3. **Filter Tokens by Chain:**
   - **Scenario:** Filter tokens on Ethereum
   - **File:** `tests/filter_tokens_by_chain.feature`
4. **Execute Mocked Strategy Advice:**
   - **Scenario:** Execute mocked swing trading strategy
   - **File:** `tests/execute_mocked_strategy_advice.feature`
5. **Generate Swing Trading Strategy:**
   - **Scenario:** Generate swing trading strategy for BTC/USD
   - **File:** `tests/generate_swing_trading_strategy.feature`
6. **Execute Trade Based on Strategy:**
   - **Scenario:** Execute trade for BTC based on swing trading strategy
   - **File:** `tests/execute_trade_based_on_strategy.feature`

We follow this approach for every feature to ensure that our features are working as expected from a user's perspective. They are written in Gherkin syntax and can be executed using a BDD framework like Cucumber.
