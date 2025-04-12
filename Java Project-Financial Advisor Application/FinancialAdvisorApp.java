import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

// Abstract Class for Financial Plan with Generics
abstract class FinancialPlan<T> {
    protected String name;
    protected int age;
    protected double salary;
    protected double expenditure;
    protected long financialGoal;
    protected T riskPreference; // Using Generics for risk preference

    public FinancialPlan(String name, int age, double salary, double expenditure, long financialGoal, T riskPreference) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.expenditure = expenditure;
        this.financialGoal = financialGoal;
        this.riskPreference = riskPreference;
    }

    public abstract String generatePlan();
}

class InvestmentPlan extends FinancialPlan<String> { // Using String for risk preference
    public InvestmentPlan(String name, int age, double salary, double expenditure, long financialGoal, String riskPreference) {
        super(name, age, salary, expenditure, financialGoal, riskPreference);
    }

    @Override
    public String generatePlan() {
        try {
            // Path to 023+the CSV file
            File finfile = new File("C:\\Users\\akshith\\OneDrive\\Desktop\\Java Project-Financial Advisor Application\\modified_investment_plans.csv");

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
                        double interestRate = Double.parseDouble(columns[2]) / 100; // Convert to decimalcsv

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
            reader.close();
    
            if (result.length() == 0) {
                return "No suitable investment plan found for the specified age and risk preference.";
            }
            return result.toString();
    
        } catch (IOException e) {
            return "Error reading the investment plans file: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Error: Please enter valid numbers for age, salary, expenditure, and financial goal.";
        } catch (Exception e) {
            return "Unexpected error occurred: " + e.getMessage();
        }
    }
}

interface BankingServices {
    void showQueryAnswers();
    void showLegalTerms();
    void showStockEducation();
}

class BankingInfo implements BankingServices {
    @Override
    public void showQueryAnswers() {
        StringBuilder faqs = new StringBuilder();
        faqs.append("FAQs:\n\n");

        // Questions and Answers added here...
        faqs.append("1. Getting Started with Investing\n");
        faqs.append("   - Why should I invest?\n");
        faqs.append("      People often wonder about the purpose and benefits of investing compared to saving in a regular bank account.\n");
        faqs.append("   - How much money do I need to start investing?\n");
        faqs.append("      Beginners often want to know if they need a large sum to start or if there are options for smaller investments.\n");
        faqs.append("   - What types of investments should I consider as a beginner?\n");
        faqs.append("      Choosing between stocks, mutual funds, ETFs, or bonds can be overwhelming for someone new to investing.\n");
        faqs.append("   - How do I open an investment account?\n");
        faqs.append("      Many are unfamiliar with how to set up brokerage or retirement accounts, especially with online platforms.\n\n");

        faqs.append("2. Understanding Investment Types\n");
        faqs.append("   - What’s the difference between stocks, bonds, mutual funds, and ETFs?\n");
        faqs.append("      People often struggle with the distinctions between various asset types and how each aligns with different financial goals.\n");
        faqs.append("   - What is an index fund, and why is it popular?\n");
        faqs.append("      Many beginners hear about index funds as a simple way to invest but are unsure of how they work and their advantages.\n");
        faqs.append("   - Are cryptocurrencies and forex good investments?\n");
        faqs.append("      With the rise of digital assets, people are curious about the risks and rewards of non-traditional investments like crypto and forex.\n\n");

        faqs.append("3. Risk and Return\n");
        faqs.append("   - How much risk should I take on?\n");
        faqs.append("      New investors often want to understand risk tolerance and how to balance it with potential returns.\n");
        faqs.append("   - What’s the safest way to invest my money?\n");
        faqs.append("      People look for low-risk options, especially if they are wary of losing money.\n");
        faqs.append("   - How much can I expect to earn from my investments?\n");
        faqs.append("      Questions about expected returns, realistic growth, and the effects of compounding are common.\n\n");

        faqs.append("4. Investment Strategies\n");
        faqs.append("   - What is diversification, and why is it important?\n");
        faqs.append("      Many know that “not putting all eggs in one basket” is essential but want to know how to apply this concept in practice.\n");
        faqs.append("   - How do I build an investment portfolio?\n");
        faqs.append("      People often ask for a roadmap or step-by-step approach to building a balanced portfolio.\n");
        faqs.append("   - What is dollar-cost averaging, and should I use it?\n");
        faqs.append("      New investors may hear about dollar-cost averaging as a strategy for consistency and want to know if it’s suitable for them.\n");
        faqs.append("   - Should I invest for the short term or the long term?\n");
        faqs.append("      Deciding between immediate gains and long-term wealth-building is a common point of confusion.\n\n");

        faqs.append("5. Personal Finance Basics\n");
        faqs.append("   - How can I save more money to invest?\n");
        faqs.append("      People are interested in budgeting and other strategies to allocate funds towards investments.\n");
        faqs.append("   - How do taxes work on investments?\n");
        faqs.append("      Understanding capital gains tax, tax-efficient investing, and deductions can be challenging for beginners.\n");
        faqs.append("   - Should I pay off debt or start investing first?\n");
        faqs.append("      Many wonder if they should focus on eliminating debt (like credit cards or student loans) before they start investing.\n");
        faqs.append("   - How much should I invest relative to my income?\n");
        faqs.append("      There are common questions about what percentage of income should ideally go into investments versus savings.\n\n");

        faqs.append("6. Banking and Financial Products\n");
        faqs.append("   - What are the benefits of different types of bank accounts (checking, savings, high-yield savings)?\n");
        faqs.append("      People often need clarity on which accounts are best for short-term cash versus emergency savings.\n");
        faqs.append("   - What is a retirement account, and how is it different from a regular investment account?\n");
        faqs.append("      Questions often focus on accounts like 401(k)s, IRAs, and Roth IRAs, especially regarding tax advantages and withdrawal rules.\n");
        faqs.append("   - Should I have an emergency fund, and how much should it be?\n");
        faqs.append("      People frequently ask about building a financial safety net and how it affects their ability to invest.\n\n");

        faqs.append("7. Advanced Topics\n");
        faqs.append("   - How do I know when to sell my investments?\n");
        faqs.append("      Investors often seek guidance on exit strategies for when to realize gains or cut losses.\n");
        faqs.append("   - What are the signs of a stock market bubble?\n");
        faqs.append("      Many want to understand warning signs of overvaluation and how to protect themselves.\n");
        faqs.append("   - How do I plan for retirement?\n");
        faqs.append("      Questions about retirement accounts, strategies for generating income in retirement, and withdrawals are common.\n");
        faqs.append("   - How can I track my investments and performance?\n");
        faqs.append("      Many want tools or methods to monitor their investments, review returns, and make informed decisions moving forward.\n");
        faqs.append("   - What is a financial advisor, and do I need one?\n");
        faqs.append("      New investors often wonder if they should seek professional help or if they can navigate investments independently.\n");
        faqs.append("   - How often should I review my investment portfolio?\n");
        faqs.append("      People are curious about the frequency of portfolio reviews and what events should prompt a reassessment.\n\n");

        faqs.append("8. Common Misconceptions\n");
        faqs.append("   - Do I need to be wealthy to start investing?\n");
        faqs.append("      People often think that investing is only for the rich and need reassurance that they can start with small amounts.\n");
        faqs.append("   - Is investing in the stock market like gambling?\n");
        faqs.append("      Many confuse investing with gambling and seek clarity on the differences in strategy and risk.\n");
        faqs.append("   - Can I time the market for the best returns?\n");
        faqs.append("      Investors frequently ponder whether they can predict market movements and achieve superior gains through timing.\n");
        faqs.append("   - Are all investment advisors trustworthy?\n");
        faqs.append("      Questions arise about how to identify credible advisors and avoid scams in the financial industry.\n");

        JTextArea faqsArea = new JTextArea(30, 70);
        faqsArea.setText(faqs.toString());
        faqsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(faqsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, scrollPane, "Frequently Asked Questions", JOptionPane.INFORMATION_MESSAGE);
    }

     
    @Override
    
    public void showLegalTerms() {
        ArrayList<String> lawsInfo = new ArrayList<>();
    
        // Adding laws protecting banks from customers
        lawsInfo.add("Laws Protecting Banks from Customers:\n");
        lawsInfo.add("Anti-Money Laundering (AML) and Know Your Customer (KYC) Regulations\n" +
                     "Enforce identity verification and monitor transactions to prevent fraud, money laundering, and financial crimes. " +
                     "These help banks avoid risks associated with illegitimate or high-risk customers.\n");
        lawsInfo.add("Fair Debt Collection Practices Act (FDCPA) (U.S.)\n" +
                     "While it focuses on customer protection from abusive debt collection, it also allows banks to collect outstanding debts " +
                     "legally without being accused of unlawful practices.\n");
        lawsInfo.add("Banking Regulation Act, 1949 (India)\n" +
                     "Provides the regulatory framework for banking operations in India, ensuring customers do not engage in activities " +
                     "that harm the bank’s operations or reputation, thereby safeguarding banks.\n");
        lawsInfo.add("Negotiable Instruments Act, 1881 (India)\n" +
                     "Governs the use of cheques, promissory notes, and bills of exchange, ensuring customers cannot misuse these " +
                     "instruments and protecting banks against wrongful claims and losses from bounced cheques.\n");
    
        // Adding laws protecting customers from banks
        lawsInfo.add("\nLaws Protecting Customers from Banks:\n");
        lawsInfo.add("Consumer Financial Protection Act (U.S.)\n" +
                     "Establishes the Consumer Financial Protection Bureau (CFPB), which ensures banks operate fairly and transparently, " +
                     "protecting customers from deceptive and unfair practices in financial services.\n");
        lawsInfo.add("Fair Credit Billing Act (FCBA) (U.S.)\n" +
                     "Allows customers to dispute billing errors, ensuring banks address and correct any mistakes, safeguarding customers " +
                     "from unfair charges or misrepresentations on credit accounts.\n");
        // (Add remaining customer protection laws similarly...)
    
        // Adding laws providing mutual protection
        lawsInfo.add("\nLaws Providing Mutual Protection for Banks and Customers:\n");
        lawsInfo.add("Reserve Bank of India Act, 1934 (India)\n" +
                     "Establishes the Reserve Bank of India (RBI) to oversee banking regulations and maintain stability in the financial " +
                     "system, which protects both banks and customers by ensuring overall financial security.\n");
        lawsInfo.add("Dodd-Frank Wall Street Reform and Consumer Protection Act (U.S.)\n" +
                     "Enforces comprehensive banking reforms, including customer protections and financial stability measures that benefit both " +
                     "customers and banks, ensuring sound banking practices and minimizing systemic risks.\n");
        // (Add remaining mutual protection laws similarly...)
    
        // Constructing the full text from the list
        StringBuilder fullText = new StringBuilder();
        for (String law : lawsInfo) {
            fullText.append(law);
        }
    
        // Displaying the laws in a dialog box
        JTextArea textArea = new JTextArea(30, 70);
        textArea.setText(fullText.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
        JOptionPane.showMessageDialog(null, scrollPane, "Laws & Terms", JOptionPane.INFORMATION_MESSAGE);
    }
    

    @Override
public void showStockEducation() {
    String educationInfo = "Stock Education:\n" +
            "1. Educate Yourself\n" +
            "Learn the Basics: Understand key concepts like stocks, dividends, market capitalization, and price-to-earnings (P/E) ratio.\n" +
            "Read Books and Articles: Look for books aimed at beginners, such as The Intelligent Investor by Benjamin Graham or A Random Walk Down Wall Street by Burton Malkiel.\n\n" +
            "2. Set Clear Goals\n" +
            "Define Your Objectives: Determine why you want to invest (e.g., retirement, a major purchase, etc.) and your time horizon.\n" +
            "Know Your Risk Tolerance: Assess how much risk you are comfortable taking based on your financial situation and investment goals.\n\n" +
            "3. Start Small\n" +
            "Invest an Amount You Can Afford to Lose: As a beginner, it’s wise to start with a small investment to get a feel for the market without risking significant capital.\n" +
            "Consider Dollar-Cost Averaging: Invest a fixed amount regularly (e.g., monthly) to spread out your purchases over time, reducing the impact of market volatility.\n\n" +
            "4. Diversify Your Portfolio\n" +
            "Spread Your Investments: Don't put all your money into one stock. Invest in various sectors (e.g., technology, healthcare, consumer goods) to mitigate risk.\n" +
            "Consider Index Funds or ETFs: These funds track a market index and can provide instant diversification at a lower cost than buying individual stocks.\n\n" +
            "5. Use a Reliable Brokerage\n" +
            "Choose the Right Broker: Look for a brokerage that offers a user-friendly platform, educational resources, low fees, and good customer support.\n" +
            "Consider Commission-Free Trading: Many brokers now offer commission-free trading, which can save you money on small trades.\n\n" +
            "6. Stay Informed\n" +
            "Follow Market Trends: Keep an eye on news and trends that may impact your investments. Resources like financial news websites and apps can be helpful.\n" +
            "Join Investment Communities: Engage with online forums or local investment clubs to share insights and experiences with other investors.\n\n" +
            "7. Be Patient and Stay Disciplined\n" +
            "Avoid Emotional Investing: Don’t let fear or greed drive your investment decisions. Stick to your plan and avoid reacting to short-term market fluctuations.\n" +
            "Long-Term Focus: Remember that investing is generally a long-term game. Try not to focus too much on daily market movements.\n\n" +
            "8. Review and Adjust\n" +
            "Regularly Monitor Your Portfolio: Check in on your investments periodically and assess whether they still align with your goals.\n" +
            "Be Open to Adjustments: If a stock or investment strategy isn't working, be willing to reevaluate and make changes as necessary.\n\n" +
            "9. Understand the Fees\n" +
            "Be Aware of Costs: Familiarize yourself with potential fees associated with trading, fund management, and your brokerage account.\n\n" +
            "10. Consult with Professionals\n" +
            "Seek Guidance if Needed: If you feel overwhelmed, consider speaking with a financial advisor to help you create a tailored investment plan.";
    JOptionPane.showMessageDialog(null, educationInfo);
}
}


public class FinancialAdvisorApp {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField salaryField;
    private JTextField expenditureField;
    private JTextField goalField;
    private JComboBox<String> riskComboBox;
    private JTextArea resultArea;

    public FinancialAdvisorApp() {
        frame = new JFrame("Financial Advisor");
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        nameField = new JTextField();
        ageField = new JTextField();
        salaryField = new JTextField();
        expenditureField = new JTextField();
        goalField = new JTextField();

        String[] riskLevels = { "Very Low", "Low", "Medium", "High", "Very High" };
        riskComboBox = new JComboBox<>(riskLevels);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Annual Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Annual Expenditure:"));
        panel.add(expenditureField);
        panel.add(new JLabel("Financial Goal:"));
        panel.add(goalField);
        panel.add(new JLabel("Risk Preference:"));
        panel.add(riskComboBox);

        JButton generateButton = new JButton("Generate Plan");
        generateButton.addActionListener(e -> generatePlan());
        panel.add(generateButton);

        JButton showQueryButton = new JButton("Show Query Answers");
        showQueryButton.addActionListener(e -> new BankingInfo().showQueryAnswers());
        panel.add(showQueryButton);

        JButton showLegalButton = new JButton("Show Legal Terms");
        showLegalButton.addActionListener(e -> new BankingInfo().showLegalTerms());
        panel.add(showLegalButton);

        JButton showStockEducationButton = new JButton("Show Stock Education");
        showStockEducationButton.addActionListener(e -> new BankingInfo().showStockEducation());
        panel.add(showStockEducationButton);

        JButton liveStockGraphButton = new JButton("Live Stock Market Trends Graph");
        liveStockGraphButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URL("https://in.tradingview.com/").toURI());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Unable to open the website. Please check your internet connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(liveStockGraphButton);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void generatePlan() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double salary = Double.parseDouble(salaryField.getText());
            double expenditure = Double.parseDouble(expenditureField.getText());
            long goal = Long.parseLong(goalField.getText());
            String risk = (String) riskComboBox.getSelectedItem();

            FinancialPlan<String> plan = new InvestmentPlan(name, age, salary, expenditure, goal, risk);
            String planDetails = plan.generatePlan();
            resultArea.setText(planDetails);
        } catch (NumberFormatException e) {
            resultArea.setText("Error: Invalid input. Please ensure all fields are filled with correct values.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinancialAdvisorApp::new);
    }
}
