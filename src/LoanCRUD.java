import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanCRUD {
    private static LoanCRUD instance;

    private LoanCRUD() {}

    public static LoanCRUD getInstance() {
        if (instance == null) {
            instance = new LoanCRUD();
        }
        return instance;
    }

    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO loan (id, bookId, memberId, dueDate, returned) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getId());
            stmt.setInt(2, loan.getBookId());
            stmt.setInt(3, loan.getMemberId());
            stmt.setDate(4, Date.valueOf(loan.getDueDate()));
            stmt.setBoolean(5, loan.isReturned());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loan";
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Loan loan = new Loan(
                    rs.getInt("id"),
                    rs.getInt("bookId"),
                    rs.getInt("memberId"),
                    rs.getDate("dueDate").toLocalDate()
                );
                loan.setReturned(rs.getBoolean("returned"));
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    public void updateLoan(Loan loan) {
        String sql = "UPDATE loan SET bookId = ?, memberId = ?, dueDate = ?, returned = ? WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getBookId());
            stmt.setInt(2, loan.getMemberId());
            stmt.setDate(3, Date.valueOf(loan.getDueDate()));
            stmt.setBoolean(4, loan.isReturned());
            stmt.setInt(5, loan.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLoan(int id) {
        String sql = "DELETE FROM loan WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
