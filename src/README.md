PARTEA 1:
-   lista cu 10 actiuni: 
1. addBook(Book b) – adauga o carte in sistem

2. removeBookById(int id) – sterge o carte dupa ID

3. getBookById(int id) – returneaza o carte dupa ID

4. searchBooksByTitle(String title) – cauta carti dupa titlu

5. listAllBooks() – listeaza toate cartile

6. listAvailableBooks() – listeaza doar cartile disponibile

7. lendBook(int id, int memberId) – împrumuta o carte unui membru

8. returnBook(int id, int memberId) – marcheaza o carte returnata

9. reserveBook(int id, int memberId) – rezerva o carte pentru un membru

10. cancelReservation(int reservationId) – anuleaza o rezervare

- Lista cu cel puain 8 tipuri de obiecte din sistem

1. Book – carte tipica (atribute: id, titlu, autor, an, disponibilitate)

2. EBook – carte electronica (extinde Book, plus atributul fileFormat)

3. Member – membru al bibliotecii (id, nume, adresa, dată inscriere)

4. Librarian – bibliotecar (id, nume, nivel acces)

5. Loan – imprumut (id, bookId, memberId, dueDate, returned)

6. Reservation – rezervare (id, bookId, memberId, reservationDate)

7. Category – categorie de carte (id, numeCategorie)

8. Author – autor (id, nume, tara)