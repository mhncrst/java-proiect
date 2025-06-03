import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        dbManager.createTables();

        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Biblioteca - Meniu Principal ===");
            System.out.println("1. Adauga carte");
            System.out.println("2. Adauga EBook");
            System.out.println("3. Devino membru");
            System.out.println("4. Imprumuta carte");
            System.out.println("5. Returneaza carte");
            System.out.println("6. Afiseaza toate cartile");
            System.out.println("7. Sterge carte");
            System.out.println("8. Iesire");
            System.out.print("Alegerea ta: ");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Titlu: ");
                    String title = scanner.nextLine();
                    System.out.print("Autor: ");
                    String author = scanner.nextLine();
                    System.out.print("An publicare: ");
                    int year = Integer.parseInt(scanner.nextLine());

                    Book book = new Book(id, title, author, year);
                    BookCRUD.getInstance().insertBook(book);
                    libraryService.addBook(book);
                    System.out.println("Carte adaugata.");
                }

                case 2 -> {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Titlu: ");
                    String title = scanner.nextLine();
                    System.out.print("Autor: ");
                    String author = scanner.nextLine();
                    System.out.print("An publicare: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Format fisier (ex: PDF): ");
                    String format = scanner.nextLine();

                    EBook ebook = new EBook(id, title, author, year, format);
                    libraryService.addBook(ebook);
                    System.out.println("EBook adaugat.");
                }

                case 3 -> {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nume: ");
                    String name = scanner.nextLine();
                    System.out.print("Adresa: ");
                    String address = scanner.nextLine();
                    LocalDate date = LocalDate.now();

                    Member member = new Member(id, name, address, date);
                    MemberCRUD.getInstance().insertMember(member);
                    System.out.println("Membru adaugat.");
                }

                case 4 -> {
                    System.out.print("ID carte: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID membru: ");
                    int memberId = Integer.parseInt(scanner.nextLine());

                    boolean success = libraryService.lendBook(bookId);
                    if (success) {
                        System.out.println("Carte împrumutata!");
                    } else {
                        System.out.println("Cartea nu este disponibila.");
                    }
                }

                case 5 -> {
                    System.out.print("ID carte de returnat: ");
                    int bookId = Integer.parseInt(scanner.nextLine());

                    boolean success = libraryService.returnBook(bookId);
                    if (success) {
                        System.out.println("Carte returnata.");
                    } else {
                        System.out.println("Returnare esuata.");
                    }
                }

                case 6 -> {
                    System.out.println("\n--- Carti în biblioteca ---");
                    libraryService.displayAllBooks();
                }

                case 7 -> {
                    System.out.print("ID carte de șters: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean deleted = libraryService.removeBookById(id);
                    if (deleted) {
                        System.out.println("Carte ștearsa.");
                    } else {
                        System.out.println("ID inexistent.");
                    }
                }

                case 8 -> {
                    System.out.println("La revedere!");
                    running = false;
                }

                default -> System.out.println("Opțiune invalida!");
            }
        }

        scanner.close();
    }
}
