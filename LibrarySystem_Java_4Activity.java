import java.util.ArrayList;
import java.util.List;

class Book {
    String title;
    String isbn;
    String status = "available";   // "available" or "borrowed"
    Member borrowedBy = null;      // which Member has borrowed it
    int timesBorrowed = 0;         // Activity 4: lifetime borrow counter

    Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }
}

class Member {
    int id;
    String name;
    String email;
    List<Book> borrowedBooks = new ArrayList<>();

    Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    void borrow(Book book) {
        // Activity 1: show current count when limit blocks
        if (borrowedBooks.size() >= 5) {
            System.out.println(name + " cannot borrow more than 5 books. You already have "
                    + borrowedBooks.size() + " book(s).");
            return;
        }
        if ("borrowed".equals(book.status)) {
            System.out.println("Book '" + book.title + "' is already borrowed.");
            return;
        }

        // Success path
        book.status = "borrowed";
        book.borrowedBy = this;
        book.timesBorrowed++; // Activity 4: increment lifetime counter
        borrowedBooks.add(book);

        // Activity 2: show total after successful borrow (+ show lifetime count)
        System.out.println(name + " borrowed '" + book.title + "'. Now holding "
                + borrowedBooks.size() + " book(s). This book has been borrowed "
                + book.timesBorrowed + " time(s) in total.");
    }

    void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.status = "available";
            book.borrowedBy = null;

            // Activity 3: show remaining count after return
            System.out.println(name + " returned '" + book.title + "'. Now holding "
                    + borrowedBooks.size() + " book(s).");
        } else {
            System.out.println(name + " does not have '" + book.title + "'.");
        }
    }
}

class Library {
    String name;
    String address;
    List<Book> books = new ArrayList<>();

    Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    void addBook(Book book) {
        books.add(book);
    }
}

public class LibrarySystem_Java {
    public static void main(String[] args) {
        // --- Demo usage ---
        Library library = new Library("Sydney Library", "123 George St");
        Book book1 = new Book("Python Basics", "111");
        Book book2 = new Book("Java Programming", "222");
        library.addBook(book1);
        library.addBook(book2);

        Member member = new Member(1, "Farshid", "Farshid.Keivanian@uts.edu.au");

        // Borrow / already borrowed / return
        member.borrow(book1);      // success -> timesBorrowed = 1
        member.borrow(book1);      // blocked: already borrowed
        member.returnBook(book1);  // success, holding decreases

        // Activity 4 check: borrow again and show lifetime count
        member.borrow(book1);      // success -> timesBorrowed = 2
        System.out.println("'" + book1.title + "' lifetime borrows = " + book1.timesBorrowed);

        // Optional: trigger Activity 1 limit message
        // Currently member holds 1 book (book1). Borrow 4 more distinct books (OK),
        // then the 6th attempt should be blocked with the limit message.
        for (int i = 3; i <= 7; i++) {
            Book extra = new Book("Extra " + i, "E" + i);
            library.addBook(extra);
            member.borrow(extra); // the last attempt (i==7) should hit the 5-book limit
        }
    }
}
