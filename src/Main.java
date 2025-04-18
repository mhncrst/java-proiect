public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();

        Book book1 = new Book(3, "The Hobbit", "J.R.R. Tolkien", 1937);
        Book book2 = new Book(1, "1984", "George Orwell", 1949);
        Book book3 = new Book(2, "To Kill a Mockingbird", "Harper Lee", 1960);

        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addBook(book3);

        EBook ebook1 = new EBook(4, "Digital Fortress", "Dan Brown", 1998, "PDF");
        libraryService.addBook(ebook1);

        libraryService.displayAllBooks();
        System.out.println();

        libraryService.displayAllSortedBooks();
        System.out.println();

        System.out.println("Imprumutam cartea cu id 1...");
        libraryService.lendBook(1);

        libraryService.displayAllBooks();
        System.out.println();

        System.out.println("Returnam cartea cu id 1...");
        libraryService.returnBook(1);

        libraryService.displayAllBooks();
        System.out.println();

        System.out.println("Eliminam cartea cu id 2... ");
        libraryService.removeBookById(2);

        libraryService.displayAllBooks();
        libraryService.displayAllSortedBooks();
    }
}
