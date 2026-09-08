public class Book {

    private int id;
    private String title;
    private String author;
    private boolean issued;
    private int issuedtoMemberId;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
        this.issuedtoMemberId = -1;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issueBook(int memberId) {
        issued = true;
        issuedtoMemberId = memberId;
    }

    public int getIssuedtoMemberId() {
        return issuedtoMemberId;
    }

    public void returnBook() {
        issued = false;
        issuedtoMemberId = -1;
    }

    public void displayBook() {
        String status = issued ? "Issued" : "Available";

        System.out.println(
                "ID: " + id +
                        " | Title: " + title +
                        " | Author: " + author +
                        " | Status: " + status +
                        (issued ? " | Issued To Member ID: " + issuedtoMemberId : "")
        );
    }
}