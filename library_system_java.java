import java.util.ArrayList;
import java.util.List;

class Book {
    String title;
    String isbn;
    String status = "available";  // available or borrowed
    Member borrowedBy = null;

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
        if (borrowedBooks.size() >= 5) {
            System.out.println(name + " cannot borrow more than 5 books.");
            return;
        }
        if (book.status.equals("borrowed")) {
            System.out.println("Book '" + book.title + "' is already borrowed.");
            return;
        }
        book.status = "borrowed";
        book.borrowedBy = this;
        borrowedBooks.add(book);
        System.out.println(name + " borrowed '" + book.title + "'.");
    }

    void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.status = "available";
            book.borrowedBy = null;
            System.out.println(name + " returned '" + book.title + "'.");
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

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library("Sydney Library", "123 George St");
        Book b1 = new Book("Python Basics", "111");
        Book b2 = new Book("Java Programming", "222");
        library.addBook(b1);
        library.addBook(b2);

        Member m = new Member(1, "Armin", "armin@example.com");

        m.borrow(b1);    // borrow success
        m.borrow(b1);    // already borrowed
        m.returnBook(b1); // return success
    }
}
