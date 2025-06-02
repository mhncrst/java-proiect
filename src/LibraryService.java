import java.time.LocalDate;
import java.util.*;

public class LibraryService {
    private List<Book> bookList;
    private Set<Book> sortedBooks;
    private Map<Integer, Book> bookMap;

    private Map<Integer, Member> memberMap;
    private List<Loan> loanList;

    public LibraryService() {
        bookList = new ArrayList<>();
        sortedBooks = new TreeSet<>();
        bookMap = new HashMap<>();

        memberMap = new HashMap<>();
        loanList = new ArrayList<>();
    }

    // ====== BOOK MANAGEMENT ======

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

    public boolean lendBook(int bookId) {
        Book book = getBookById(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(int bookId) {
        Book book = getBookById(bookId);
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
        System.out.println("Cărțile din bibliotecă (ordinea adăugării):");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void displayAllSortedBooks() {
        System.out.println("Cărțile din bibliotecă (sortate după id):");
        for (Book book : sortedBooks) {
            System.out.println(book);
        }
    }

    // ====== MEMBER MANAGEMENT ======

    public void addMember(Member member) {
        memberMap.put(member.getId(), member);
    }

    public Member getMemberById(int id) {
        return memberMap.get(id);
    }

    public void displayAllMembers() {
        System.out.println("Membrii înregistrați:");
        for (Member m : memberMap.values()) {
            System.out.println(m);
        }
    }

    // ====== LOAN MANAGEMENT ======

    public void createLoan(int loanId, int bookId, int memberId, LocalDate dueDate) {
        Book book = getBookById(bookId);
        Member member = getMemberById(memberId);

        if (book != null && member != null && book.isAvailable()) {
            Loan loan = new Loan(loanId, bookId, memberId, dueDate);
            loanList.add(loan);
            book.setAvailable(false);
            System.out.println("Împrumut creat: " + loan);
        } else {
            System.out.println("Împrumut eșuat: verifică disponibilitatea cărții și existența membrului.");
        }
    }

    public void returnLoan(int loanId) {
        for (Loan loan : loanList) {
            if (loan.getId() == loanId && !loan.isReturned()) {
                loan.setReturned(true);
                Book book = getBookById(loan.getBookId());
                if (book != null) {
                    book.setAvailable(true);
                }
                System.out.println("Împrumut returnat: " + loan);
                return;
            }
        }
        System.out.println("Împrumutul nu a fost găsit sau deja returnat.");
    }

    public void displayAllLoans() {
        System.out.println("Împrumuturi:");
        for (Loan loan : loanList) {
            System.out.println(loan);
        }
    }
}
