import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Member> members = new ArrayList<>();

        System.out.println("================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("================================");

        while (true) {

            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Register Member");
            System.out.println("6. View Members");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    boolean idExists = false;

                    for (Book b : books) {
                        if (b.getId() == id) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        System.out.println("A book with this ID already exists!");
                        break;
                    }

                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();

                    Book book = new Book(id, title, author);
                    books.add(book);

                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        System.out.println("\n--- Available Books ---");

                        for (Book b : books) {
                            b.displayBook();

                            if (b.isIssued()) {
                                for (Member m : members) {
                                    if (m.getId() == b.getIssuedtoMemberId()) {
                                        System.out.println("Issued To: " + m.getName());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter book title to search: ");
                    String keyword = scanner.nextLine();

                    boolean found = false;

                    for (Book b : books) {
                        if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                            b.displayBook();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Book not found.");
                    }

                    break;

                case 4:
                    System.out.print("Enter book ID to remove: ");
                    int removeId = scanner.nextInt();

                    boolean removed = false;

                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getId() == removeId) {
                            books.remove(i);
                            removed = true;
                            System.out.println("Book removed successfully!");
                            break;
                        }
                    }

                    if (!removed) {
                        System.out.println("Book not found.");
                    }

                    break;

                case 5:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();

                    System.out.print("Enter member email: ");
                    String memberEmail = scanner.nextLine();

                    Member member = new Member(memberId, memberName, memberEmail);
                    members.add(member);

                    System.out.println("Member registered successfully!");
                    break;

                case 6:
                    if (members.isEmpty()) {
                        System.out.println("No members registered.");
                    } else {
                        System.out.println("\n--- Registered Members ---");

                        for (Member m : members) {
                            m.displayMember();
                        }
                    }
                    break;

                case 7:
                    System.out.print("Enter book ID to issue: ");
                    int issueBookId = scanner.nextInt();

                    System.out.print("Enter member ID: ");
                    int issueMemberId = scanner.nextInt();

                    Book bookToIssue = null;
                    Member memberToIssue = null;

                    // Find the book
                    for (Book b : books) {
                        if (b.getId() == issueBookId) {
                            bookToIssue = b;
                            break;
                        }
                    }

                    // Find the member
                    for (Member m : members) {
                        if (m.getId() == issueMemberId) {
                            memberToIssue = m;
                            break;
                        }
                    }

                    if (bookToIssue == null) {
                        System.out.println("Book not found.");
                    } else if (memberToIssue == null) {
                        System.out.println("Member not found.");
                    } else if (bookToIssue.isIssued()) {
                        System.out.println("Book is already issued.");
                    } else {
                        bookToIssue.issueBook(issueMemberId);
                        System.out.println("Book issued successfully!");
                        System.out.println("Book: " + bookToIssue.getTitle());
                        System.out.println("Issued to: " + memberToIssue.getName());
                    }

                    break;

                case 8:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();

                    boolean returned = false;

                    for (Book b : books) {
                        if (b.getId() == returnBookId) {

                            if (!b.isIssued()) {
                                System.out.println("This book is not currently issued.");
                            } else {
                                b.returnBook();
                                System.out.println("Book returned successfully!");
                                System.out.println("Book: " + b.getTitle());
                            }

                            returned = true;
                            break;
                        }
                    }

                    if (!returned) {
                        System.out.println("Book not found.");
                    }

                    break;

                case 9:
                    System.out.println("Thank you for using the Library Management System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}