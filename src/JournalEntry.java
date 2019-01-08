import java.util.Date;

public class JournalEntry {
    public final String author;
    public final Date date;
    public final String body;
    public final String title;

    public JournalEntry(String author, Date date, String body, String title) {
        this.author = author;
        this.date = date;
        this.body = body;
        this.title = title;
    }
}
