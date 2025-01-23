import java.io.*;
import java.util.ArrayList;

public class FileHandler {  
    //CONSTRUCTOR
    public FileHandler(){}
    
    //METHODS

    //STORING AND READING BOOKS
    public void downloadBooks(Library library)  {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Books.txt", false));
            ArrayList<Book> books = library.getBooks();
            for (Book b : books) {
                bw.write(b.getTitle()+"\n"+b.getAuthor()+"\n"+b.getGenre()+"\n");
            }
            bw.close();
        } catch (IOException e)  {
            System.out.println("Nicole :)");
        }
    }

    public void uploadBooks(Library library)  {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Books.txt"));
            String [] lines = new String[3];
            Book book;
            String line;
            int count = 0;

            while((line = br.readLine()) != null) {
                if (count <= 2) {
                    lines[count] = line;
                }
                if (count == 2){
                    book = new Book(lines[0], lines[1], lines[2]);
                    library.addBook(book);
                    count = -1;
                }

                count++;
            }
            br.close();
        } catch (IOException e)  {
            System.out.println("Nicole :)");
        }
    }

    //STORING AND READING MEMBERS
    public void downloadMembers(Library library) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Members.txt", false));
            ArrayList<Member> members = library.getMembers();
            ArrayList<Book> borrowedBooks;
            for (Member m : members) {
                borrowedBooks = m.getBorrowedBooks();
                bw.write(m.getId()+"\n"+m.getName()+"\n"+borrowedBooks.size()+"\n");

                for (Book b: borrowedBooks) {
                    bw.write(b.getTitle()+"\n"+b.getAuthor()+"\n"+b.getGenre()+"\n");
                }
            }
            bw.close();
        } catch (IOException e)  {
            System.out.println("Nicole :)");
        }
    }
    
    public void uploadMembers(Library library)  {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Members.txt"));
            String [] lines = new String[35];
            String line;
            int numBorrowedBooks = 0;
            int count = 0;
            Member member = new Member(null, null);
            Book book;
            while ((line = br.readLine()) != null) {
                if (count == 2) {
                    numBorrowedBooks = Integer.parseInt(line);
                    member = new Member(lines[0], lines[1]);
                    library.addMember(member);
                    if (numBorrowedBooks == 0) count = -1;
                }
                else {
                    if (count < 2) lines[count] = line;
                    else {
                        if (count <= (2 + numBorrowedBooks*3)) {
                            lines[count] = line;
                            if (count == (2 + numBorrowedBooks*3)) {
                                for (int i=0; i < numBorrowedBooks; i++) {
                                    book = new Book(lines[(i+1) * 3], lines[((i+1) * 3) + 1], lines[((i+1) * 3) + 2]);
                                    member.borrowBook(book, library);
                                    count = -1;
                                }
                            }
                        }
                    }
                }
                count++;
            }
            br.close();
        } catch (IOException e)  {
            System.out.println("Nicole :)");
        }
    }

    //READING ADMIN
    public void uploadAdmin(Library library) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Admins.txt"));
            Admin admin;
            String [] lines = new String[2];
            String line;
            int key;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (count <= 1) {
                    lines[count] = line;
                }
                else{
                    key = Integer.parseInt(line);
                    admin = new Admin(lines[0], lines[1], key);
                    library.addAdmin(admin);
                    count = -1;
                }

                count++;
            }
            br.close();
        } catch (IOException e)  {
            System.out.println("Nicole :)");
        }
    }
}  
