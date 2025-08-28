class Book:
    def __init__(self, title, isbn):
        self.title = title
        self.isbn = isbn
        self.status = "available"   # available or borrowed
        self.borrowed_by = None     # which Member has borrowed

class Member:
    def __init__(self, member_id, name, email):
        self.id = member_id
        self.name = name
        self.email = email
        self.borrowed_books = []

    def borrow(self, book):
        if len(self.borrowed_books) >= 5:
            print(f"{self.name} cannot borrow more than 5 books.")
            return
        if book.status == "borrowed":
            print(f"Book '{book.title}' is already borrowed.")
            return
        book.status = "borrowed"
        book.borrowed_by = self
        self.borrowed_books.append(book)
        print(f"{self.name} borrowed '{book.title}'.")

    def return_book(self, book):
        if book in self.borrowed_books:
            self.borrowed_books.remove(book)
            book.status = "available"
            book.borrowed_by = None
            print(f"{self.name} returned '{book.title}'.")
        else:
            print(f"{self.name} does not have '{book.title}'.")

class Library:
    def __init__(self, name, address):
        self.name = name
        self.address = address
        self.books = []

    def add_book(self, book):
        self.books.append(book)


# --- Example usage ---
library = Library("Sydney Library", "123 George St")
book1 = Book("Python Basics", "111")
book2 = Book("Java Programming", "222")
library.add_book(book1)
library.add_book(book2)

member = Member(1, "Armin", "armin@example.com")

member.borrow(book1)   #  borrow success
member.borrow(book1)   #  already borrowed
member.return_book(book1)  #  return success
