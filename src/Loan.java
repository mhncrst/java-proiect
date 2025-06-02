import java.time.LocalDate;

public class Loan {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate dueDate;
    private boolean returned;

    public Loan(int id, int bookId, int memberId, LocalDate dueDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Loan [id=" + id + ", bookId=" + bookId + ", memberId=" + memberId +
            ", dueDate=" + dueDate + ", returned=" + returned + "]";
    }
}
