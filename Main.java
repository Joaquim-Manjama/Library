import java.io.*;

public class Main {
    public static void main(String [] args) {
        //DECLARING ENTITIES
        Library library = new Library();
        FileHandler fl = new FileHandler();
        
        fl.uploadAdmin(library);
        fl.uploadMembers(library);
        fl.uploadBooks(library);

        //OPEN LOGIN MENU
        login(library, fl);
    }

    //MAIN MENU
    public static void menu(Library library, FileHandler fl) {
        int option;

        //MENU
        do {
            System.out.println("\n  ****  LIBRARY  ****");
            System.out.println("1. Check books available");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Check list of borrowed books");
            System.out.println("5. Donate a book to our library");
            System.out.println("6. Logout");

            //GET OPTION FROM USER
           option = getInt(": ");
            
            switch(option) {
                //DISPLAY AVAILABLE BOOKS
                case 1 -> {
                    library.displayBooks();
                }
                
                //ALLOW USER TO BORROW A BOOK
                case 2 -> {
                    //GET THE NUMBER OF THE BOOK
                    System.out.println("    **** BORROW A BOOK  ****");
                    int bookId = getInt("Enter the book number: ");
                    int count = 0;
                    //CHECK IF BOOK EXISTS
                    if(bookId >= 1 && bookId <= library.getBooks().size()){
                        //FIND BOOK AND ALLOW USER TO BORROW
                        for (Book b: library.getBooks()) {
                            if (count == bookId - 1){
                                for (Member m: library.getMembers()) {
                                    if ((library.getCurrentMember().getId() == null ? m.getId() == null : library.getCurrentMember().getId().equals(m.getId())) & (library.getCurrentMember().getName() == null ? m.getName() == null : library.getCurrentMember().getName().equals(m.getName()))) {
                                        m.borrowBook(b, library);
                                        System.out.println("\n ======================================");
                                        System.out.println("| Book has been successfully borrowed! |");
                                        System.out.println(" ======================================");
                                        library.newcurrentMember(m);
                                        fl.downloadBooks(library);
                                        fl.downloadMembers(library);
                                        break;
                                    }
                                }
                                break;        
                            }
                            count++;
                        }
                    //BOOK DOES NOT EXIST
                    } else System.out.println("Book does not exist!");
                }
                
                //ALLOW USER TO RETURN A BOOK
                case 3 -> {
                    for (Member m: library.getMembers())
                    {
                        if ((library.getCurrentMember() == null ? m.getId() == null : library.getCurrentMember().getId().equals(m.getId())) & (library.getCurrentMember().getName() == null ? m.getName() == null : library.getCurrentMember().getName().equals(m.getName()))) {
                            if (!m.getBorrowedBooks().isEmpty()) {
                                System.out.println("    **** RETURN A BOOK  ****");
                                String title = getString("title");
                                String author = getString("author");
                                String genre = getString("genre");
                                Book book = new Book(title, author, genre);

                                if (m.serachBorrowedBook(book)) {
                                    for (Book b : m.getBorrowedBooks()) {
                                        if ((book.getTitle() == null ? b.getTitle() == null : book.getTitle().equals(b.getTitle())) & (book.getAuthor() == null ? b.getAuthor() == null : book.getAuthor().equals(b.getAuthor())) & (book.getGenre() == null ? b.getGenre() == null : book.getGenre().equals(b.getGenre()))) {
                                            m.returnBook(book, library);
                                            System.out.println("\n ======================================");
                                            System.out.println("| Book has been successfully returned! |");
                                            System.out.println(" ======================================");
                                            library.newcurrentMember(m);
                                            fl.downloadBooks(library);
                                            fl.downloadMembers(library);
                                            break;
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("\n ===============");
                                    System.out.println("| Invalid Book! |");
                                    System.out.println(" ===============");
                                }
                            }
                        }
                    }
                }
                
                //DISPLAY LIST OF BORROWED BOOKS
                case 4 -> {
                    for (Member m: library.getMembers())
                    {
                        if ((library.getCurrentMember() == null ? m.getId() == null : library.getCurrentMember().getId().equals(m.getId())) & (library.getCurrentMember().getName() == null ? m.getName() == null : library.getCurrentMember().getName().equals(m.getName()))) {
                            int count = 1;
                            if (m.getBorrowedBooks().isEmpty()) System.out.println("No borrowed books!");
                            else {
                                System.out.println("\nBORROWED BOOKS: ");
                                for (Book b: m.getBorrowedBooks()) {
                                    b.display(count);
                                    count++;
                                }
                            }
                        }
                    }
                }

                //DONATE BOOK TO LIBRARY
                case 5 -> {
                    System.out.println("    ****  DONATE A BOOK  ****");
                    String title = getString("title");
                    String author = getString("author");
                    String genre = getString("genre");
                    Book book = new Book(title, author, genre);
                    if (library.searchBook(book)) {
                        System.out.println("\n ======================");
                        System.out.println("| Book already exists! |");
                        System.out.println(" ======================");
                    }
                    else {
                        library.addBook(book);
                        System.out.println("\n ============================");
                        System.out.println("| Book donated successfully! |");
                        System.out.println(" ============================");
                        fl.downloadBooks(library);
                    }
                }
                case 6 -> System.out.println("Thanks for visitig our library. We hope to see you soon!");
                default -> System.out.println("Invalid Option!");
            }
        } while (option != 6);
    }

    //ADMIN MENU
    public static void admin(Library library, FileHandler fl) {
        Book book = new Book(null, null, null);
        Admin admin = new Admin(null, null, 0);
        int option;

        do { 
            //MENU
            System.out.println("\n  ****  ADMIN  ****");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Check list of books");
            System.out.println("4. Check list of members");
            System.out.println("5. Logout");

            option = getInt(": ");

            switch(option) {
                //OPTION 1 (ADD BOOK)
                case 1 -> {
                    System.out.println("    ****  ADD A BOOK  ****");
                    String title = getString("title");
                    String author = getString("author");
                    String genre = getString("genre");
                    book = new Book(title, author, genre);
                    if (library.searchBook(book)) {
                        System.out.println("\n ======================");
                        System.out.println("| Book already exists! |");
                        System.out.println(" ======================");
                    }
                    else {
                        admin.addBook(book, library);
                        fl.downloadBooks(library);
                    }
                }

                //OPTION 2 (REMOVE BOOK)
                case 2 -> {
                    System.out.println("    ****  REMOVE A BOOK  ****");
                    int bookId = getInt("Enter the book number: ");
                    if (library.getBooks().size() > bookId){
                        int count = 0;
                        for (Book b: library.getBooks()) {
                            if (count == (bookId - 1)) {
                                admin.removeBook(book, library);
                                fl.downloadBooks(library);
                                break;      
                            }   
                            count++;
                        }
                          
                    }
                    else {
                        System.out.println("\n ======================");
                        System.out.println("| Book does not exist! |");
                        System.out.println(" ======================");
                    }
                }

                //OPTION 3 (DISPLAY LIST OF BOOKS)
                case 3 -> library.displayBooks();

                //OPTION 4 (DISPLAY MEMBERS)
                case 4 -> library.displayMembers();

                case 5 -> {}

                default -> System.out.println("Invalid option!");
            }

        } while (option != 5);
    }

    //LOGIN MENU
    public static void login(Library library, FileHandler fl) {
        //DECLARING VARIABLES
        int option;
        String name = "";
        String id = "";
        int key;
        Member member = new Member(null, null);

        //MENU
        do { 
            System.out.println("\n    ****  LIBRARY  ****");
            System.out.println("1. Login");
            System.out.println("2. Create new account");
            System.out.println("3. Delete account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");

            //GET THE OPTION FROM THE USER
            option = getInt(": ");

            //GET NAME AND PASSWORD IF OPTION IS 1 OR 2 OR 3 OR 4
            if (option == 1 || option == 2 || option == 3 || option == 4){
                if (option == 1) System.out.print("\n   ****  LOGIN  ****");
                if (option == 2) System.out.print("\n   ****  CREATE NEW ACCOUNT  ****");
                if (option == 3) System.out.print("\n   ****  DELETE ACCOUNT  ****");
                if (option == 4) System.out.print("\n   ****  ADMIN  ****");
                name = getString("name");
                id = getString("password"); 
                member = new Member(id, name);
            }
               
            switch(option) {
                //OPTION 1 (LOGIN)
                case 1 -> {
                    //CHECK IF THE MEMBER HAS AN ACCOUNT
                    if (library.searchMember(member)) {
                        //ALLOW MEMBER IN
                        library.newcurrentMember(member);
                        System.out.println("\n\n------------------------------------------");
                        System.out.println("    Welcome back "+member.getName()+" !");
                        System.out.println("------------------------------------------");
                        menu(library, fl);
                    }
                    else {
                        //MAKE MEMBER CREATE AN ACCOUNT
                        System.out.println("\n ===================================");
                        System.out.println("|      Account does not exist!      |");
                        System.out.println("| Create an account to get started! |");
                        System.out.println(" ===================================");
                    }
                }
                //OPTION 2 (CREATE NEW ACCOUNT)
                case 2 -> {
                     //CHECK IF THE MEMBER HAS AN ACCOUNT
                    if (library.searchMember(member)) {
                        //MAKE MEMBER LOGIN
                        System.out.println("\n =========================");
                        System.out.println("| Account already exists! |");
                        System.out.println(" =========================");
                    }
                    else {
                        //CREATE ACCOUNT FOR MEMBER
                        System.out.println("\n ========================================");
                        System.out.println("| Account has been successfully created! |");
                        System.out.println("|         Login to get started!          |");
                        System.out.println(" ========================================");
                        library.addMember(member);
                        fl.downloadMembers(library);
                    }
                }
                //DELETE MEMBER ACCOUNT
                case 3 -> {
                    //CHECK IF MEMBER EXISTS
                    if (library.searchMember(member)) {
                        if (member.returnedAll()) {
                            library.removeMember(member);
                            fl.downloadMembers(library);
                        } else {
                            System.out.println("\n ===========================================");
                            System.out.println("| Return all books before deleting account! |");
                            System.out.println(" ===========================================");
                        }
                    } else {
                        System.out.println("\n =========================");
                        System.out.println("| Account does not exist! |");
                        System.out.println(" =========================");
                    }
                }
                //ADMIN LOGIN
                case 4 -> {
                    key = getInt("Enter the key: ");
                    Admin admin = new Admin(name, id, key);
                    //CHECK IF THE MEMBER HAS AN ACCOUNT
                    if (library.searchAdmin(admin)) {
                        //MAKE ADMIN LOGIN
                        System.out.println("\n\n------------------------------------------");
                        System.out.println("    Welcome back ADMIN !");
                        System.out.println("------------------------------------------");
                        admin(library, fl);
                    } else System.out.println("Invalid!");
                }

                //OPTION 5 (EXIT LIBRARY)
                case 5 -> {
                    System.out.println("Thanks for visitig our library. We hope to see you soon!");
                    System.exit(0);
                }

                //IN CASE OF INVALID OPTION
                default -> System.out.println("Invalid Option!");
            }
        } while (option != 5);
    }

    //METHOD TO GET STRING VALUES FROM MEMBERS
    public static String getString(String input) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nEnter your "+input+": ");
            return br.readLine();
        }catch (IOException e) {
            System.out.println("Nicole :)");
            return "Nicole :)";
        }
    }

    //METHOD TO GET INT VALUES FROM MEMBERS
    public static int getInt(String input) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\n"+input);
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Nicole :)");
            return -1;
        } catch (IOException e) {
            return 0;
        }
    }
}