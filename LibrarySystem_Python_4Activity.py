class Book:
    def __init__(self, title, isbn):
        self.title = title
        self.isbn = isbn
        self.status = "available"   # available or borrowed
        self.borrowed_by = None     # which Member has borrowed
        self.times_borrowed = 0     # Activity 4: lifetime borrow counter


class Member:
    def __init__(self, member_id, name, email):
        self.id = member_id
        self.name = name
        self.email = email
        self.borrowed_books = []

    def borrow(self, book):
        # Activity 1: show current count when limit blocks
        if len(self.borrowed_books) >= 5:
            print(f"{self.name} cannot borrow more than 5 books (currently {len(self.borrowed_books)}/5).")
            return
        if book.status == "borrowed":
            print(f"Book '{book.title}' is already borrowed.")
            return

        # Success path
        book.status = "borrowed"
        book.borrowed_by = self
        book.times_borrowed += 1  # Activity 4: increment lifetime counter
        self.borrowed_books.append(book)

        # Activity 2 (+Activity 4 info): show total after successful borrow + lifetime count
        print(f"{self.name} borrowed '{book.title}' (now {len(self.borrowed_books)} borrowed). "
              f"This book has been borrowed {book.times_borrowed} time(s) in total.")

    def return_book(self, book):
        if book in self.borrowed_books:
            self.borrowed_books.remove(book)
            book.status = "available"
            book.borrowed_by = None

            # Activity 3: show remaining count after return
            print(f"{self.name} returned '{book.title}' (now {len(self.borrowed_books)} borrowed).")
        else:
            print(f"{self.name} does not have '{book.title}'.")


class Library:
    def __init__(self, name, address):
        self.name = name
        self.address = address
        self.books = []

    def add_book(self, book):
        self.books.append(book)


# --- Demo usage (mirrors your Java flow) ---
if __name__ == "__main__":
    library = Library("Sydney Library", "123 George St")
    book1 = Book("Python Basics", "111")
    book2 = Book("Java Programming", "222")
    library.add_book(book1)
    library.add_book(book2)

    member = Member(1, "Farshid Keivanian", "Farshid.Keivanian@uts.edu.au")

    member.borrow(book1)        # success -> times_borrowed = 1
    member.borrow(book1)        # blocked: already borrowed
    member.return_book(book1)   # success, holding decreases

    # Activity 4 check: borrow again and show lifetime count
    member.borrow(book1)        # success -> times_borrowed = 2
    print(f"'{book1.title}' lifetime borrows = {book1.times_borrowed}")

    # Optional: hit the 5-book limit
    # At this point member holds 1 book (book1). Borrow 4 more OK, the 6th attempt blocks.
    for i in range(3, 8):  # 3..7 inclusive = 5 attempts
        extra = Book(f"Extra {i}", f"E{i}")
        library.add_book(extra)
        member.borrow(extra)    # the last attempt should hit the 5-book limit
