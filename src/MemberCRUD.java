import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class    MemberCRUD {
    private static MemberCRUD instance;

    private MemberCRUD() {}

    public static MemberCRUD getInstance() {
        if (instance == null) {
            instance = new MemberCRUD();
        }
        return instance;
    }

    public void insertMember(Member member) {
        String sql = "INSERT INTO member (id, name, address, registration_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, member.getId());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getAddress());
            stmt.setDate(4, Date.valueOf(member.getRegistrationDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Member member = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getDate("registrationDate").toLocalDate()
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void updateMember(Member member) {
        String sql = "UPDATE member SET name = ?, address = ?, registrationDate = ? WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getAddress());
            stmt.setDate(3, Date.valueOf(member.getRegistrationDate()));
            stmt.setInt(4, member.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM member WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
