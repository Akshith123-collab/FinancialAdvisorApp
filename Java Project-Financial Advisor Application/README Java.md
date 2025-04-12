
# Financial Advisor Application 

The Financial Advisor Application is designed to help individuals make informed investment decisions by providing personalized financial plans based on their unique circumstances. 



## Features

- User Input Interface: A form to capture details like name, age, salary, expenses, financial goals, and risk tolerance.
- Investment Plan Generation: Creates a personalized investment plan with recommendations, expected returns, and timeframes.
- Banking Services Information: Access FAQs, legal terms, and educational content on stock markets.



## Tech Stack

- Java: Core language for application development.
- Swing: Used to build the graphical user interface.
- JFreeChart: Generates dynamic charts for visualizing stock data.
- JSON: Parses data from APIs for real-time stock updates.
- Apache Commons IO: Handles data input/output operations, particularly URL data fetching.


## Installation

- Clone the repository:
bash

```bash
  git clone [repository-url]

```
- Open the project in your preferred Java IDE.
- Run the application to launch the user interface.
## Motivation

With an overwhelming array of investment choices, individuals often struggle with:

- Determining suitable investment options.
- Making informed choices aligned with their financial goals and risk tolerance.
This application seeks to simplify financial planning and make investment advice accessible and user-friendly.
## Objectives

The primary objectives of the Financial Advisor Application are:

- Personalized Financial Plans: Generate investment plans based on age, salary, expenses, goals, and risk preferences.
- Educational Resources: Provide users with knowledge on financial products and access to FAQs.
## Implementation Structure

- Abstract Class: FinancialPlan - Defines the structure of financial plans.
- InvestmentPlan Class: Extends FinancialPlan to generate custom investment advice.
- Banking Services Interface: Interface for banking services, FAQs, and legal terms.
- Main Application Class: FinancialAdvisorApp - The entry point, handling the GUI and user interactions.
## Functional Requirements

- User Interface: A user-friendly form with validation for input.
- Financial Planning: Generates investment plans based on user attributes.
- Banking Services Information: Provides educational resources on investing.
- User Actions: Buttons for generating plans, viewing information, and live stock graphs.
- Error Handling: Manages input errors and connectivity issues gracefully.
- Performance: Ensures timely responses and real-time data updates. 
## Future Intentions

- Future development may include additional financial planning tools, expanded educational resources, and enhanced support for real-time stock market data.
## Main Feature

 public String generatePlan() {
        try {
            // Path to 023+the CSV file
            File finfile = new File("C:\\Users\\Nan\\Downloads\\modified_investment_plans.csv");
            BufferedReader reader = new BufferedReader(new FileReader(finfile));
            String line;
            String[] columns;
            line = reader.readLine(); // Skip the header
            StringBuilder result = new StringBuilder();
    
            while ((line = reader.readLine()) != null) {
                columns = line.split(",");
                
                // Check if riskPreference matches and age falls within the plan's age range
                if (columns[3].equalsIgnoreCase(riskPreference)) {
                    String[] ageArray = columns[6].split("-");
                    int minAge = Integer.parseInt(ageArray[0]);
                    int maxAge = Integer.parseInt(ageArray[1]);
                    
                    if (age > minAge && age < maxAge) {
                        // Append details of the matching plan
                        result.append("---------------------\n");
                        result.append("Plan Name: ").append(columns[0]).append("\n");
                        result.append("Bank: ").append(columns[1]).append("\n");
                        result.append("Interest Rate: ").append(columns[2]).append("%\n");
                        result.append("Maturity: ").append(columns[4]).append("\n");

                        // Interest Rate from the CSV file
                        double interestRate = Double.parseDouble(columns[2]) / 100; // Convert to decimal

                        // Calculate annual savings (salary - expenditure)
                        double annualSavings = salary - expenditure;

                        // Calculate the estimated number of years to reach the financial goal
                        int years = 0;
                        double accumulatedSavings = 0;

                        // Using compound interest formula to accumulate savings annually
                        while (accumulatedSavings < financialGoal) {
                            accumulatedSavings += annualSavings; // Add savings each year
                            accumulatedSavings += accumulatedSavings * interestRate; // Apply interest
                            years++; // Count the year
                        }

                        result.append("Estimated Years to Reach Goal: ").append(years).append(" years\n");
                        result.append("Accumulated Savings at Goal: Rs.").append(String.format("%.2f", accumulatedSavings)).append("\n");

                        break; // Exit after finding the first match
                    }
                }
            }