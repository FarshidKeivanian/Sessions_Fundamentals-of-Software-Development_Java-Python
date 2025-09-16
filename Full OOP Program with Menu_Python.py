from abc import ABC, abstractmethod

# Abstract Parent Class
class BankAccount(ABC):
    def __init__(self, balance):
        self._balance = balance   # Encapsulation

    @abstractmethod
    def withdraw(self, amount):
        pass

    def deposit(self, amount):
        self._balance += amount

    def get_balance(self):
        return self._balance


# Child Class: SavingsAccount
class SavingsAccount(BankAccount):
    def __init__(self, account_number, balance, interest_rate=0.05):
        super().__init__(balance)
        self.account_number = account_number
        self.interest_rate = interest_rate

    def withdraw(self, amount):
        if amount <= self._balance:
            self._balance -= amount
            print("Savings withdrawal successful")
        else:
            print("Not enough funds")

    def add_interest(self):
        self._balance += self._balance * self.interest_rate
        print("Interest added!")


# Menu Pattern
def menu():
    acc = SavingsAccount("12345", 1000, 0.05)
    print(f"Account created: {acc.account_number}")
    
    while True:
        print("\n1. Deposit\n2. Withdraw\n3. Show Balance\n4. Add Interest\n5. Exit")
        choice = input("Choose: ")
        if choice == "1":
            amt = float(input("Amount: "))
            acc.deposit(amt)
        elif choice == "2":
            amt = float(input("Amount: "))
            acc.withdraw(amt)
        elif choice == "3":
            print("Balance:", acc.get_balance())
        elif choice == "4":
            acc.add_interest()
        elif choice == "5":
            print("Goodbye!")
            break
        else:
            print("Invalid choice, try again.")

menu()
