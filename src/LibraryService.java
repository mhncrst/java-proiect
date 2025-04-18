import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LibraryService {
    private List<Book> bookList;
    private Set<Book> sortedBooks;
    private Map<Integer, Book> bookMap;

    public LibraryService() {
        bookList = new ArrayList<>();
        sortedBooks = new TreeSet<>();
        bookMap = new HashMap<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
        sortedBooks.add(book);
        bookMap.put(book.getId(), book);
    }

    public boolean removeBookById(int id) {
        Book book = bookMap.get(id);
        if (book != null) {
            bookList.remove(book);
            sortedBooks.remove(book);
            bookMap.remove(id);
            return true;
        }
        return false;
    }

    public Book getBookById(int id) {
        return bookMap.get(id);
    }

    public boolean lendBook(int id) {
        Book book = getBookById(id);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(int id) {
        Book book = getBookById(id);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public Set<Book> getAllSortedBooks() {
        return sortedBooks;
    }

    public void displayAllBooks() {
        System.out.println("Carțile din biblioteca (ordinea adaugarii):");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void displayAllSortedBooks() {
        System.out.println("Cartile din biblioteca (sortate după id):");
        for (Book book : sortedBooks) {
            System.out.println(book);
        }
    }
}
