import java.util.ArrayList;

public class Library {

    private final ArrayList<Book> books;
    private final ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public boolean addBook(Book book) {
        if (findBook(book.getId()) != null) {
            return false;
        }

        books.add(book);
        return true;
    }

    public boolean addMember(Member member) {
        if (findMember(member.getId()) != null) {
            return false;
        }

        members.add(member);
        return true;
    }

    public boolean removeBook(int id) {
        Book book = findBook(id);

        if (book == null) {
            return false;
        }

        books.remove(book);
        return true;
    }

    public ArrayList<Book> searchBooks(String keyword) {

        ArrayList<Book> results = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    public boolean issueBook(int bookId, int memberId) {

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null || member == null || book.isIssued()) {
            return false;
        }

        book.issueBook(memberId);
        return true;
    }

    public boolean returnBook(int bookId) {

        Book book = findBook(bookId);

        if (book == null || !book.isIssued()) {
            return false;
        }

        book.returnBook();
        return true;
    }

    public void displayAllBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- Library Books ---");

        for (Book book : books) {
            book.displayBook();
        }
    }

    public void displayAllMembers() {

        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n--- Registered Members ---");

        for (Member member : members) {
            member.displayMember();
        }
    }

    public Book findBook(int id) {

        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    public Member findMember(int id) {

        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }

        return null;
    }
}