import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EBookCRUD {
    private static EBookCRUD instance;
    private final Connection connection;

    private EBookCRUD() throws SQLException {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static EBookCRUD getInstance() throws SQLException {
        if (instance == null) {
            instance = new EBookCRUD();
        }
        return instance;
    }

    public void create(EBook ebook) throws SQLException {
        String sql = "INSERT INTO ebooks (id, title, author, year, is_available, fileFormat) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ebook.getId());
            stmt.setString(2, ebook.getTitle());
            stmt.setString(3, ebook.getAuthor());
            stmt.setInt(4, ebook.getYear());
            stmt.setBoolean(5, ebook.isAvailable());
            stmt.setString(6, ebook.getFileFormat());
            stmt.executeUpdate();
        }
    }

    public EBook read(int id) throws SQLException {
        String sql = "SELECT * FROM ebooks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EBook(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year"),
                    rs.getString("fileFormat")
                );
            }
        }
        return null;
    }

    public List<EBook> readAll() throws SQLException {
        List<EBook> list = new ArrayList<>();
        String sql = "SELECT * FROM ebooks";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new EBook(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year"),
                    rs.getString("fileFormat")
                ));
            }
        }
        return list;
    }

    public void update(EBook ebook) throws SQLException {
        String sql = "UPDATE ebooks SET title = ?, author = ?, year = ?, available = ?, fileFormat = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ebook.getTitle());
            stmt.setString(2, ebook.getAuthor());
            stmt.setInt(3, ebook.getYear());
            stmt.setBoolean(4, ebook.isAvailable());
            stmt.setString(5, ebook.getFileFormat());
            stmt.setInt(6, ebook.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ebooks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
