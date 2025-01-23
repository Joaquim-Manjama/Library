import java.util.Objects;

public class Admin {
    //ATTRIBUTES
    String name;
    String password;
    int key;

    //CONSTRUCTOR
    public Admin(String name, String password, int key) {
        this.name = name;
        this.password = password;
        this.key = key;
    }

    //METHODS
    public void addBook(Book book, Library library) {
        library.addBook(book);
        System.out.println("\n ===================================");
        System.out.println("| Book has been successfully added! |");
        System.out.println(" ===================================");
    }

    public void removeBook(Book book, Library library) {
        library.removeBook(book);
        System.out.println("\n =====================================");
        System.out.println("| Book has been successfully removed! |");
        System.out.println(" =====================================");
    }

    // OVERRIDING equals AND hashCode BY CHATGPT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(this.name, admin.name) && Objects.equals(this.password, admin.password) && Objects.equals(this.key, admin.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.password, this.key);
    }
}
