class BankAccount:
    def __init__(self, account_holder, balance):
        self.account_holder = account_holder
        self.balance = balance
    
    def deposit(self, amount):
        self.balance += amount
        print(f"{self.account_holder}'s new balance is ${self.balance:.2f}")

# Example use
cba_account = BankAccount("Sarah Johnson", 1500.00)
cba_account.deposit(200)
