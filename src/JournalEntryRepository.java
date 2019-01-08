import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JournalEntryRepository {
    private static String entryFormat = "Author: %s\nDate: %s\n\n%s";

    public static void save(JournalEntry entry) throws IOException {
        Path path = pathFor(entry);
        createRequiredDirectories(path);
        while (titleAlreadyExists(path)) {
            path = addMarkerToAvoidOverwriting(path);
        }
        Files.write(path, contentsFor(entry));
    }

    private static Path addMarkerToAvoidOverwriting(Path path) {
        return Paths.get(path.toString() + '!');
    }

    private static boolean titleAlreadyExists(Path path) {
        return path.toFile().exists();
    }

    private static void createRequiredDirectories(Path path) {
        path.getParent().toFile().mkdirs();
    }

    private static Path pathFor(JournalEntry entry) {
        return Paths.get(System.getProperty("user.home"),
                        ".diaries",
                        slugify(entry.author),
                        slugify(entry.title));
    }

    private static String slugify(String text) {
        return replaceSpacesWithHyphens(removePunctuation(text));
    }

    private static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    private static String replaceSpacesWithHyphens(String text) {
        return text.replaceAll(" ", "-");
    }

    private static byte[] contentsFor(JournalEntry entry) {
        return String.format(entryFormat, entry.author, entry.date, entry.body).getBytes();
    }
}
