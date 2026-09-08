import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();

        System.out.println("================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("================================");

        while (true) {

            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Exit");

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
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter book ID to search: ");
                    int searchId = scanner.nextInt();

                    boolean found = false;

                    for (Book b : books) {
                        if (b.getId() == searchId) {
                            b.displayBook();
                            found = true;
                            break;
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

                    for (Book b : books) {
                        if (b.getId() == removeId) {
                            books.remove(b);
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
                    System.out.println("Thank you for using the Library Management System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}