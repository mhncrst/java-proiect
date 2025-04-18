public class EBook extends Book {
    private String fileFormat;

    public EBook(int id, String title, String author, int year, String fileFormat) {
        super(id, title, author, year);
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {
        return "EBook [id=" + getId() + ", title=" + getTitle() + ", author=" + getAuthor() +
            ", year=" + getYear() + ", fileFormat=" + fileFormat + ", available=" + isAvailable() + "]";
    }
}
