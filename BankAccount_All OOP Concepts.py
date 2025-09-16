from abc import ABC, abstractmethod

# Abstract Parent Class
class BankAccount(ABC):
    def __init__(self, balance):
        self._balance = balance   # Encapsulation (hidden field)

    @abstractmethod
    def withdraw(self, amount):
        pass

    def get_balance(self):        # Encapsulation (getter)
        return self._balance


# Child 1: SavingsAccount
class SavingsAccount(BankAccount):
    def withdraw(self, amount):
        if amount <= self._balance:
            self._balance -= amount
            print("Savings withdrawal successful")
        else:
            print("Not enough funds in savings!")


# Child 2: CheckingAccount
class CheckingAccount(BankAccount):
    def withdraw(self, amount):
        self._balance -= amount   # may go negative
        print("Checking withdrawal (may go negative).")


# Polymorphism in action
accounts = [SavingsAccount(500), CheckingAccount(500)]
for acc in accounts:
    acc.withdraw(600)
    print("Final balance:", acc.get_balance())
