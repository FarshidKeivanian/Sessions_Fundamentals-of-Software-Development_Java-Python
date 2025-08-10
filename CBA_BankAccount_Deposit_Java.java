class BankAccount {
    String accountHolder;
    double balance;

    BankAccount(String holder, double bal) {
        accountHolder = holder;
        balance = bal;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + "'s new balance is $" + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount cbaAccount = new BankAccount("Sarah Johnson", 1500.00);
        cbaAccount.deposit(200);
    }
}
