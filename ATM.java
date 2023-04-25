import java.awt.*;
import java.awt.event.*;

public class ATM extends Frame implements ActionListener {

    private Label nameLabel;
    private Label balanceLabel;
    private TextField amountField;
    private Label messageLabel;
    private Button depositButton;
    private Button withdrawButton;
    private Button transferButton;
    private Button historyButton;
    private Button quitButton;

    private String[] transactionHistory = new String[10];
    private int transactionCount = 0;
    private double balance = 1000.00;

    public ATM() {
        // Set the title of the frame
        setTitle("ATM");

        // Set the size of the frame
        setSize(500, 500);

        // Set the layout of the frame
        setLayout(new GridLayout(6, 2));

        // Add the name label to the frame
        nameLabel = new Label("Welcome! Triveni Puri");
        add(nameLabel);

        // Add the balance label to the frame
        balanceLabel = new Label("Current balance: $" + balance);
        add(balanceLabel);

        // Add the amount field to the frame
        amountField = new TextField("0");
        add(amountField);

        // Add the deposit button to the frame
        depositButton = new Button("Deposit");
        depositButton.addActionListener(this);
        depositButton.setBackground(Color.GREEN);
        add(depositButton);

        // Add the withdraw button to the frame
        withdrawButton = new Button("Withdraw");
        withdrawButton.addActionListener(this);
        withdrawButton.setBackground(Color.RED);
        add(withdrawButton);

        // Add the transfer button to the frame
        transferButton = new Button("Transfer");
        transferButton.addActionListener(this);
        transferButton.setBackground(Color.BLUE);
        add(transferButton);

        // Add the history button to the frame
        historyButton = new Button("View history");
        historyButton.addActionListener(this);
        historyButton.setBackground(Color.ORANGE);
        add(historyButton);

        // Add the message label to the frame
        messageLabel = new Label("");
        add(messageLabel);

        // Add the quit button to the frame
        quitButton = new Button("Quit");
        quitButton.addActionListener(this);
        quitButton.setBackground(Color.GRAY);
        add(quitButton);

        // Show the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            double amount = Double.parseDouble(amountField.getText());
            balance += amount;
            transactionHistory[transactionCount] = "Deposit: $" + amount;
            transactionCount++;
            updateBalanceLabel();
            messageLabel.setText("Deposit successful.");
        } else if (e.getSource() == withdrawButton) {
            double amount = Double.parseDouble(amountField.getText());
            if (balance - amount >= 0) {
                balance -= amount;
                transactionHistory[transactionCount] = "Withdrawal: $" + amount;
                transactionCount++;
                updateBalanceLabel();
                messageLabel.setText("Withdrawal successful.");
            } else {
                messageLabel.setText("Insufficient funds.");
            }
        } else if (e.getSource() == transferButton) {
            double amount = Double.parseDouble(amountField.getText());
            if (balance - amount >= 0) {
                balance -= amount;
                transactionHistory[transactionCount] = "Transfer out: $" + amount;
                transactionCount++;
                updateBalanceLabel();
                messageLabel.setText("Transfer successful.");
            } else {
                messageLabel.setText("Insufficient funds.");
            }
        } else if (e.getSource() == historyButton) {
            String history = "";
            for (String transaction : transactionHistory) {
                if (transaction != null) {
                    history += transaction + "\n";
                }
                }
                if (history.equals("")) {
                    messageLabel.setText("No transactions yet.");
                } else {
                    messageLabel.setText(history);
                }
            } else if (e.getSource() == quitButton) {
                System.exit(0);
            }
        }

        private void updateBalanceLabel() {
            balanceLabel.setText("Current balance: $" + balance);
        }

        public static void main(String[] args) {
            ATM atm = new ATM();
        }
    }