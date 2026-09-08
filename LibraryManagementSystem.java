import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

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

                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();

                    Book book = new Book(id, title, author);

                    if (library.addBook(book)) {
                        System.out.println("Book added successfully!");
                    } else {
                        System.out.println("A book with this ID already exists!");
                    }

                    break;

                case 2:
                    library.displayAllBooks();
                    break;

                case 3:
                    System.out.print("Enter book title to search: ");
                    String keyword = scanner.nextLine();

                    ArrayList<Book> searchResults = library.searchBooks(keyword);

                    if (searchResults.isEmpty()) {
                        System.out.println("Book not found.");
                    } else {
                        System.out.println("\n--- Search Results ---");

                        for (Book b : searchResults) {
                            b.displayBook();
                        }
                    }

                    break;

                case 4:
                    System.out.print("Enter book ID to remove: ");
                    int removeId = scanner.nextInt();

                    if (library.removeBook(removeId)) {
                        System.out.println("Book removed successfully!");
                    } else {
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

                    Member member = new Member(
                            memberId,
                            memberName,
                            memberEmail
                    );

                    if (library.addMember(member)) {
                        System.out.println("Member registered successfully!");
                    } else {
                        System.out.println("A member with this ID already exists!");
                    }

                    break;

                case 6:
                    library.displayAllMembers();
                    break;

                case 7:
                    System.out.print("Enter book ID to issue: ");
                    int issueBookId = scanner.nextInt();

                    System.out.print("Enter member ID: ");
                    int issueMemberId = scanner.nextInt();

                    Book bookToIssue = library.findBook(issueBookId);
                    Member memberToIssue = library.findMember(issueMemberId);

                    if (bookToIssue == null) {
                        System.out.println("Book not found.");
                    } else if (memberToIssue == null) {
                        System.out.println("Member not found.");
                    } else if (bookToIssue.isIssued()) {
                        System.out.println("Book is already issued.");
                    } else if (library.issueBook(issueBookId, issueMemberId)) {
                        System.out.println("Book issued successfully!");
                        System.out.println("Book: " + bookToIssue.getTitle());
                        System.out.println("Issued to: " + memberToIssue.getName());
                    }

                    break;

                case 8:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();

                    Book bookToReturn = library.findBook(returnBookId);

                    if (bookToReturn == null) {
                        System.out.println("Book not found.");
                    } else if (!bookToReturn.isIssued()) {
                        System.out.println("This book is not currently issued.");
                    } else if (library.returnBook(returnBookId)) {
                        System.out.println("Book returned successfully!");
                        System.out.println("Book: " + bookToReturn.getTitle());
                    }

                    break;

                case 9:
                    System.out.println(
                            "Thank you for using the Library Management System!"
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}