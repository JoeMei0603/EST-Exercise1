import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookManagerTest {

    private BookManager bookManager;
    private List<Book> allBooks;
    private List <String> expectedUniqueAuthors;

    @BeforeEach
    public void testSetup() {
        this.allBooks = new ArrayList<>();
        this.expectedUniqueAuthors = new ArrayList<>();

        BookRatingsFetcher fetcher = new BookRatingsFetcherStub(allBooks);
        this.bookManager = new BookManager(fetcher);
    }

    @Test
    public void testHighRatedBooksEmpty() {
        List<Book> highRatedBooks = this.bookManager.highRatedBooks();

        assertEquals(0, highRatedBooks.size());
    }

    @Test
    public void testHighRatedBooksNull() {
        this.allBooks = null;

        List<Book> highRatedBooks = this.bookManager.highRatedBooks();

        assertEquals(0, highRatedBooks.size());
    }

    @Test
    public void testHighRatedBooksOneSmaller() {
        this.allBooks.add(new Book("Book 1", 3));

        List<Book> highRatedBooks = this.bookManager.highRatedBooks();

        assertEquals(0, highRatedBooks.size());
    }

    @Test
    public void testHighRatedBooksOneGreater() {
        this.allBooks.add(new Book("Book 1", 4));

        List<Book> highRatedBooks = this.bookManager.highRatedBooks();

        assertEquals(1, highRatedBooks.size());
    }

    @Test
    public void testHighRatedBooksMixed() {
        this.allBooks.addAll(Arrays.asList(
                new Book("Book 1", 5),
                new Book("Book 2", 4),
                new Book("Book 3", 3),
                new Book("Book 4", 2)
        ));

        List<Book> highRatedBooks = this.bookManager.highRatedBooks();

        assertEquals(2, highRatedBooks.size());
    }

    @Test
    public void testUniqueAuthorsEmpty() {
        List<String> actualUniqueAuthors = this.bookManager.uniqueAuthors();

        assertEquals(this.expectedUniqueAuthors, actualUniqueAuthors);
    }

    @Test
    public void testUniqueAuthorsNoAuthor() {
        this.allBooks.add(new Book("Book 1", 5));
        this.expectedUniqueAuthors.add(null);

        List<String> actualUniqueAuthors = this.bookManager.uniqueAuthors();

        assertEquals(this.expectedUniqueAuthors, actualUniqueAuthors);
    }

    @Test
    public void testUniqueAuthorsOneElement() {
        this.allBooks.add(new Book("Book 1", 5, "testAuthor"));
        this.expectedUniqueAuthors.add("testAuthor");

        List<String> actualUniqueAuthors = this.bookManager.uniqueAuthors();

        assertEquals(this.expectedUniqueAuthors, actualUniqueAuthors);
    }

    @Test
    public void testUniqueAuthorsTwoElementsUnique() {
        this.allBooks.addAll(Arrays.asList(
                new Book("Book 1", 5, "testAuthor"),
                new Book("Book 2", 4, "testAuthor2")
        ));
        this.expectedUniqueAuthors.addAll(Arrays.asList("testAuthor", "testAuthor2"));

        List<String> actualUniqueAuthors = this.bookManager.uniqueAuthors();

        assertEquals(this.expectedUniqueAuthors, actualUniqueAuthors);
    }

    @Test
    public void testUniqueAuthorsTwoElementsDuplicate() {
        this.allBooks.addAll(Arrays.asList(
                new Book("Book 1", 5, "testAuthor"),
                new Book("Book 2", 4, "testAuthor")
        ));
        this.expectedUniqueAuthors.add("testAuthor");

        List<String> actualUniqueAuthors = this.bookManager.uniqueAuthors();

        assertEquals(this.expectedUniqueAuthors, actualUniqueAuthors);
    }
}
