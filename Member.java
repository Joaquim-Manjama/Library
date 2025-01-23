import java.util.ArrayList;
import java.util.Objects;

public class Member {
    
    //ATTRIBUTES
    private final String id;
    private final String name;
    private ArrayList<Book> borrowedBooks;

    //CONSTRUCTOR
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    //METHODS
    public void borrowBook(Book book, Library library){
        this.borrowedBooks.add(book);
        library.removeBook(book);
    }

    public void returnBook(Book book, Library library){
        this.borrowedBooks.remove(book);
        library.addBook(book);
    }

    //METHOD TO RETURN NAME
    public String getName() {
        return this.name;
    }

    //METHOD TO RETURN ID
    public String getId() {
        return this.id;
    }

    //METHOD TO RETURN BORROWED BOOKS
    public ArrayList<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    //METHOD TO DISPLAY A MEMBER
    public void display(int num) {
        System.out.println("            Member "+num);
        System.out.println("Name: " + this.name + "\nId: " + this.id);
    }

    public boolean returnedAll() {
        return this.borrowedBooks.isEmpty();
    }

    public boolean serachBorrowedBook(Book book) {
        return this.borrowedBooks.contains(book);
    }

    // OVERRIDING equals AND hashCode BY CHATGPT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(this.id, member.id) && Objects.equals(this.name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
} 
