import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        try {
            DatabaseConnection.getConnection().close();
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return;
        }

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

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 9.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter book ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid book ID. Please enter a number.");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (library.findBook(id) != null) {
                        System.out.println("A book with this ID already exists!");
                        break;
                    }

                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    if (title.isBlank()) {
                        System.out.println("Book title cannot be empty.");
                        break;
                    }

                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();

                    if (author.isBlank()) {
                        System.out.println("Author name cannot be empty.");
                        break;
                    }

                    Book book = new Book(id, title, author);

                    if (library.addBook(book)) {
                        System.out.println("Book added successfully!");
                    } else {
                        System.out.println("Unable to add book.");
                    }

                    break;

                case 2:
                    library.displayAllBooks();
                    break;

                case 3:
                    System.out.print("Enter book title to search: ");
                    String keyword = scanner.nextLine();

                    if (keyword.isBlank()) {
                        System.out.println("Search keyword cannot be empty.");
                        break;
                    }

                    ArrayList<Book> searchResults =
                            library.searchBooks(keyword);

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

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid book ID. Please enter a number.");
                        scanner.nextLine();
                        break;
                    }

                    int removeId = scanner.nextInt();
                    scanner.nextLine();

                    if (library.removeBook(removeId)) {
                        System.out.println("Book removed successfully!");
                    } else {
                        System.out.println("Book not found.");
                    }

                    break;

                case 5:
                    System.out.print("Enter member ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid member ID. Please enter a number.");
                        scanner.nextLine();
                        break;
                    }

                    int memberId = scanner.nextInt();
                    scanner.nextLine();

                    if (library.findMember(memberId) != null) {
                        System.out.println("A member with this ID already exists!");
                        break;
                    }

                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();

                    if (memberName.isBlank()) {
                        System.out.println("Member name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter member email: ");
                    String memberEmail = scanner.nextLine();

                    if (memberEmail.isBlank()) {
                        System.out.println("Email cannot be empty.");
                        break;
                    }

                    Member member = new Member(
                            memberId,
                            memberName,
                            memberEmail
                    );

                    if (library.addMember(member)) {
                        System.out.println("Member registered successfully!");
                    } else {
                        System.out.println("Unable to register member.");
                    }

                    break;

                case 6:
                    library.displayAllMembers();
                    break;

                case 7:

                    System.out.print("Enter book ID to issue: ");

                    if (!scanner.hasNextInt()) {

                        System.out.println("Invalid book ID. Please enter a number.");

                        scanner.nextLine();

                        break;

                    }

                    int issueBookId = scanner.nextInt();

                    System.out.print("Enter member ID: ");

                    if (!scanner.hasNextInt()) {

                        System.out.println("Invalid member ID. Please enter a number.");

                        scanner.nextLine();

                        break;

                    }

                    int issueMemberId = scanner.nextInt();

                    scanner.nextLine();

                    if (library.issueBook(issueBookId, issueMemberId)) {

                        System.out.println("Book issued successfully!");

                    } else {

                        System.out.println("Unable to issue book.");

                        System.out.println(

                                "Check the book/member ID or whether the book is already issued."

                        );

                    }

                    break;

                case 8:

                    System.out.print("Enter book ID to return: ");

                    if (!scanner.hasNextInt()) {

                        System.out.println("Invalid book ID. Please enter a number.");

                        scanner.nextLine();

                        break;

                    }

                    int returnBookId = scanner.nextInt();

                    scanner.nextLine();

                    if (library.returnBook(returnBookId)) {

                        System.out.println("Book returned successfully!");

                    } else {

                        System.out.println("Unable to return book.");

                        System.out.println(

                                "Check the book ID or whether the book is currently issued."

                        );

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

                            "Invalid choice. Please enter a number from 1 to 9."

                    );

            }

        }

    }

}