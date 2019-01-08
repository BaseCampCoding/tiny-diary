import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
            
public class Program {
    private static Scanner stdin = new Scanner(System.in);
    
    public static void main(String[] args) {
        JournalEntry entry = inputJournalEntry();
        try {
            JournalEntryRepository.save(entry);
        } catch (IOException ex) {
            System.out.println("Unable To Save File!");
        }
    }

    private static JournalEntry inputJournalEntry() {
        var author = inputAuthor();
        var title = inputTitle();
        var body = inputBody();
        return new JournalEntry(author, new Date(), body, title);
    }

    private static String inputTitle() {
        System.out.print("Title: ");
        return stdin.nextLine().trim();
    }

    private static String inputBody() {
        System.out.println("Body: (enter END on a line by itself to quit)");
        var lines = new ArrayList<String>();
        for (var line = stdin.nextLine(); !line.equals("END"); line = stdin.nextLine()) {
            lines.add(line);
        }
        return String.join("\n", lines);
    }

    private static String inputAuthor() {
        System.out.print("Author: ");
        return stdin.nextLine().trim();
    }
}
