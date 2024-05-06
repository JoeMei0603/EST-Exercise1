import java.util.List;
import static java.util.stream.Collectors.toList;

public class BookManager {

    private final BookRatingsFetcher fetcher;

    public BookManager(BookRatingsFetcher bookRatingsFetcher) {
        this.fetcher = bookRatingsFetcher;
    }

    public List<Book> highRatedBooks() {

        try {
            List<Book> allBooks = this.fetcher.all();
            return allBooks.stream()
                    .filter(book -> book.getRating() >= 4)
                    .collect(toList());
        } finally {
            this.fetcher.close();
        }
    }

    // Additional Implementation
    public List<String> uniqueAuthors() {
        try {
            List<Book> allBooks = this.fetcher.all();
            return allBooks.stream()
                    .map(Book::getAuthor)
                    .distinct()
                    .collect(toList());
        } finally {
            this.fetcher.close();
        }
    }
}
