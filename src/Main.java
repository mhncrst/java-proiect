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
            System.out.println("1. Adaugă carte");
            System.out.println("2. Adaugă EBook");
            System.out.println("3. Devino membru");
            System.out.println("4. Împrumută carte");
            System.out.println("5. Returnează carte");
            System.out.println("6. Afișează toate cărțile");
            System.out.println("7. Șterge carte");
            System.out.println("8. Ieșire");
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
                    System.out.println("Carte adăugată.");
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
                    System.out.print("Format fișier (ex: PDF): ");
                    String format = scanner.nextLine();

                    EBook ebook = new EBook(id, title, author, year, format);
                    libraryService.addBook(ebook);
                    System.out.println("EBook adăugat.");
                }

                case 3 -> {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nume: ");
                    String name = scanner.nextLine();
                    System.out.print("Adresă: ");
                    String address = scanner.nextLine();
                    LocalDate date = LocalDate.now();

                    Member member = new Member(id, name, address, date);
                    MemberCRUD.getInstance().insertMember(member);
                    System.out.println("Membru adăugat.");
                }

                case 4 -> {
                    System.out.print("ID carte: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID membru: ");
                    int memberId = Integer.parseInt(scanner.nextLine());

                    boolean success = libraryService.lendBook(bookId);
                    if (success) {
                        System.out.println("Carte împrumutată!");
                    } else {
                        System.out.println("Cartea nu este disponibilă.");
                    }
                }

                case 5 -> {
                    System.out.print("ID carte de returnat: ");
                    int bookId = Integer.parseInt(scanner.nextLine());

                    boolean success = libraryService.returnBook(bookId);
                    if (success) {
                        System.out.println("Carte returnată.");
                    } else {
                        System.out.println("Returnare eșuată.");
                    }
                }

                case 6 -> {
                    System.out.println("\n--- Cărți în bibliotecă ---");
                    libraryService.displayAllBooks();
                }

                case 7 -> {
                    System.out.print("ID carte de șters: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean deleted = libraryService.removeBookById(id);
                    if (deleted) {
                        System.out.println("Carte ștearsă.");
                    } else {
                        System.out.println("ID inexistent.");
                    }
                }

                case 8 -> {
                    System.out.println("La revedere!");
                    running = false;
                }

                default -> System.out.println("Opțiune invalidă!");
            }
        }

        scanner.close();
    }
}
