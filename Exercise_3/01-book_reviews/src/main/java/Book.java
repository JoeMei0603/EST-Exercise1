public class Book {
    private final String title;
    private final int rating; // Rating on a scale from 1 to 5
    private String author; // Additional Implementation

    public Book(String title, int rating, String author) { // Additional Implementation
        this.title = title;
        this.rating = rating;
        this.author = author;
    }

    public Book(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    public String getAuthor() { // Additional Implementation
        return author;
    }
}