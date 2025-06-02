import java.time.LocalDate;

public class Member {
    private int id;
    private String name;
    private String address;
    private LocalDate registrationDate;

    public Member(int id, String name, String address, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", name=" + name + ", address=" + address +
            ", registrationDate=" + registrationDate + "]";
    }
}
