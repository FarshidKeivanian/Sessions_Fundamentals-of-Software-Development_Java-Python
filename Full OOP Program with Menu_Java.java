import java.util.Scanner;

// Abstract Parent Class
abstract class BankAccount {
    protected double balance; // Encapsulation

    BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void withdraw(double amount);
}

// Child Class: SavingsAccount
class SavingsAccount extends BankAccount {
    private String accountNumber;
    private double interestRate;

    SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(balance);
        this.accountNumber = accountNumber;
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Savings withdrawal successful");
        } else {
            System.out.println("Not enough funds");
        }
    }

    public void addInterest() {
        balance += balance * interestRate;
        System.out.println("Interest added!");
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

// Main with Menu
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SavingsAccount acc = new SavingsAccount("12345", 1000, 0.05);

        System.out.println("Account created: " + acc.getAccountNumber());

        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Show Balance\n4. Add Interest\n5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Amount: ");
                    double dep = Double.parseDouble(sc.nextLine());
                    acc.deposit(dep);
                    break;
                case "2":
                    System.out.print("Amount: ");
                    double wd = Double.parseDouble(sc.nextLine());
                    acc.withdraw(wd);
                    break;
                case "3":
                    System.out.println("Balance: " + acc.getBalance());
                    break;
                case "4":
                    acc.addInterest();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    sc.close();
                    return; // exit program
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
