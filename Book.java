import java.util.Objects;

public class Book {

    //ATTRIBUTES
    private final String id;
    private final String title;
    private final String author;
    private final String genre;

    //CONSTRUCTOR
    public Book(String title, String author, String genre) {
        this.id = "\""+title+" by "+author+"\"";
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    //METHODS

    //METHOD TO RETURN BOOK TITLE
    public String getTitle() {
        return this.title;
    }

    //METHOD TO RETURN BOOK AUTHOR
    public String getAuthor() {
        return this.author;
    }

    //METHOD TO RETURN BOOK GENRE
    public String getGenre() {
        return this.genre;
    }

    //METHOD TO DISPLAY A BOOK
    public void display(int num) {
        System.out.println("\n            Book "+num);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("Genre: " + this.genre);
        System.out.println("id: " + this.id);
    }
    
    //CHECK IF BOOKS ARE THE SAME
    public boolean areBooksSame(Book book1, Book book2) {
        return book1 == book2;
    }

    // OVERRIDING equals AND hashCode BY CHATGPT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(this.title, book.title) && Objects.equals(this.author, book.author) && Objects.equals(this.genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.author, this.genre);
    }
}
