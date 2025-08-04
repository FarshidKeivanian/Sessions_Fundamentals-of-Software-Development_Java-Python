class Account:
    def __init__(self, balance):
        self.__balance = balance  # Hidden (private)

    def get_balance(self):
        return self.__balance     # Safe way to access balance
