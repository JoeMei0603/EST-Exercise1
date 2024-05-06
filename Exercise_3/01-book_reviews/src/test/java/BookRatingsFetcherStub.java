import java.util.List;

public class BookRatingsFetcherStub extends BookRatingsFetcher {

    private final List<Book> books;

    public BookRatingsFetcherStub(List<Book> books) {
        super(null);
        this.books = books;
    }

    @Override
    public List<Book> all() {
        return this.books;
    }

    @Override
    public void close() {
        //Null
    }
}
