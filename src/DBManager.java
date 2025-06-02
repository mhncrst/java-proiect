import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    public void createTables() {
        String createBookTable = """
            CREATE TABLE IF NOT EXISTS book (
                id INT PRIMARY KEY,
                title VARCHAR(255),
                author VARCHAR(255),
                year INT,
                is_available BOOLEAN
            );
        """;

        String createMemberTable = """
            CREATE TABLE IF NOT EXISTS member (
                id INT PRIMARY KEY,
                name VARCHAR(255),
                address VARCHAR(255),
                registration_date DATE
            );
        """;

        String createLoanTable = """
            CREATE TABLE IF NOT EXISTS loan (
                id INT PRIMARY KEY,
                book_id INT,
                member_id INT,
                due_date DATE,
                returned BOOLEAN,
                FOREIGN KEY (book_id) REFERENCES books(id),
                FOREIGN KEY (member_id) REFERENCES members(id)
            );
        """;

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createBookTable);
            stmt.execute(createMemberTable);
            stmt.execute(createLoanTable);
            System.out.println("Tabelele au fost create cu succes.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
