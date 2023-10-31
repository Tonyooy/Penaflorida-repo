package BankAccount;

public class BankAccount {
    private int accountNumber;
    private String accountName;
    private double balance;

    public BankAccount(int accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }

    public void displayBalance() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountName);
        System.out.println("Balance: $" + balance);
    }

    public static void main(String[] args) {

        BankAccount myAccount = new BankAccount(546524351, "Matt Murdock");

        myAccount.deposit(750.0);
        myAccount.displayBalance();

        myAccount.withdraw(300.0);
        myAccount.displayBalance();
    }
}
