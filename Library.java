import java.util.ArrayList;
import java.util.Collections; //CHATGPT
import java.util.Comparator; //CHATGPT

public class Library {
    //ATTRIBUTES
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<Admin> admins;
    private Member currentMember;

    //CONSTRUCTOR
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
    }

    //METHODS

    public Member getCurrentMember() {
        return this.currentMember;
    }

    //METHOD TO RETURN MEMBERS
    public ArrayList<Member> getMembers() {
        return this.members;
    }

    //METHOD TO RETURN BOOKS
    public ArrayList<Book> getBooks() {
        return this.books;
    }

    //METHOD TO ADD BOOK TO LIBRARY
    public void addBook(Book book) {
        this.books.add(book);
        Collections.sort(this.books, Comparator.comparing(Book::getTitle)); //CHATGPT
    }

    //METHOD TO REMOVE BOOK FROM LIBRARY
    public void removeBook(Book book) {
        this.books.remove(book);
    }

    //METHOD TO ADD A MEMBER
    public void addMember(Member member) {
        this.members.add(member);
        Collections.sort(this.members, Comparator.comparing(Member::getName)); //CHATGPT
    }

    //METHOD TO REMOVE MEMBER
    public void removeMember(Member member) {
        this.members.remove(member);
        System.out.println("\n ========================================");
        System.out.println("| Account has been successfully deleted! |");
        System.out.println(" ========================================");
    }

    //METHOD TO ADD AN ADMIN
    public void addAdmin(Admin admin) {
        this.admins.add(admin);
    }

    //METHOD TO SEARCH BOOK IN LIBRARY
    public boolean searchBook(Book book) {
        return this.books.contains(book);
    }

    //METHOD TO CHECK IS A MEMBER HAS AN ACCOUNT
    public boolean searchMember(Member member) {
        return this.members.contains(member);
    }

    //METHOD TO CHECK IF ADMIN EXISTS
    public boolean searchAdmin(Admin admin) {
        return this.admins.contains(admin);
    }

    //METHOD TO ASSIGN THE CURRENT MEMBER
    public void newcurrentMember(Member member){
        this.currentMember = member;
    }

    //METHOD TO DISPLAY ALL BOOKS AVAILABLE IN THE LIBRARY
    public void displayBooks() {
        if (this.members.isEmpty()) System.out.println("There are no borrowed books!");
        else {
            System.out.println("\n  -----LIST OF BOOKS-----");
            int count = 1;
            for (Book b : this.books){
                b.display(count);
                count++;
                System.out.println("-------------------------");
            }
        }
    }

    //METHOD TO DISPLAY ALL MEMBERS WITH AN ACCOUNT IN THE LIBRARY
    public void displayMembers() {
        ArrayList<Book> books;
        if (this.members.isEmpty()) System.out.println("There are no members!");
        else {
            System.out.println("\n  -----LIST OF MEMBERS-----");
            int count = 1;
            for (Member m : this.members){
                m.display(count);
                count++;
                books = m.getBorrowedBooks();
                if (!books.isEmpty()){
                    System.out.println("\nBORROWED BOOKS: ");
                    int count2 = 1;
                    for (Book b : books){
                        b.display(count2);
                        count2++;
                        System.out.println("-------------------------");
                    }
                }
                System.out.println("-------------------------");
            }

        }
    }
}
