import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Library {

    private final ArrayList<Book> books;
    private final ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();

        loadBooksFromDatabase();
        loadMembersFromDatabase();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    // =========================
    // BOOK METHODS
    // =========================

    public boolean addBook(Book book) {

        if (findBook(book.getId()) != null) {
            return false;
        }

        String sql = "INSERT INTO books " +
                "(id, title, author, issued, issued_to_member_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setBoolean(4, book.isIssued());

            if (book.isIssued()) {
                statement.setInt(5, book.getIssuedtoMemberId());
            } else {
                statement.setNull(5, java.sql.Types.INTEGER);
            }

            statement.executeUpdate();

            books.add(book);
            return true;

        } catch (SQLException e) {
            System.out.println("Database error while adding book.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeBook(int id) {

        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            Book book = findBook(id);

            if (book != null) {
                books.remove(book);
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Database error while removing book.");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Book> searchBooks(String keyword) {

        ArrayList<Book> results = new ArrayList<>();

        String sql = "SELECT id, title, author, issued, issued_to_member_id " +
                "FROM books WHERE title LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author")
                    );

                    if (resultSet.getBoolean("issued")) {
                        book.issueBook(
                                resultSet.getInt("issued_to_member_id")
                        );
                    }

                    results.add(book);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error while searching books.");
            e.printStackTrace();
        }

        return results;
    }

    public boolean issueBook(int bookId, int memberId) {

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null || member == null || book.isIssued()) {
            return false;
        }

        String sql = "UPDATE books " +
                "SET issued = ?, issued_to_member_id = ? " +
                "WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, true);
            statement.setInt(2, memberId);
            statement.setInt(3, bookId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            book.issueBook(memberId);

            return true;

        } catch (SQLException e) {
            System.out.println("Database error while issuing book.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(int bookId) {

        Book book = findBook(bookId);

        if (book == null || !book.isIssued()) {
            return false;
        }

        String sql = "UPDATE books " +
                "SET issued = ?, issued_to_member_id = NULL " +
                "WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, false);
            statement.setInt(2, bookId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            book.returnBook();

            return true;

        } catch (SQLException e) {
            System.out.println("Database error while returning book.");
            e.printStackTrace();
            return false;
        }
    }

    public void displayAllBooks() {

        String sql = "SELECT id, title, author, issued, issued_to_member_id " +
                "FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No books available.");
                return;
            }

            System.out.println("\n--- Library Books ---");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                boolean issued = resultSet.getBoolean("issued");

                System.out.println(
                        "ID: " + id +
                                " | Title: " + title +
                                " | Author: " + author +
                                " | Status: " +
                                (issued ? "Issued" : "Available")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error while displaying books.");
            e.printStackTrace();
        }
    }

    // =========================
    // MEMBER METHODS
    // =========================

    public boolean addMember(Member member) {

        if (findMember(member.getId()) != null) {
            return false;
        }

        String sql = "INSERT INTO members (id, name, email) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, member.getId());
            statement.setString(2, member.getName());
            statement.setString(3, member.getEmail());

            statement.executeUpdate();

            members.add(member);
            return true;

        } catch (SQLException e) {
            System.out.println("Database error while registering member.");
            e.printStackTrace();
            return false;
        }
    }

    public void displayAllMembers() {

        String sql = "SELECT id, name, email FROM members";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No members registered.");
                return;
            }

            System.out.println("\n--- Registered Members ---");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println(
                        "ID: " + id +
                                " | Name: " + name +
                                " | Email: " + email
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error while displaying members.");
            e.printStackTrace();
        }
    }

    // =========================
    // DATABASE LOADING
    // =========================

    private void loadBooksFromDatabase() {

        String sql = "SELECT id, title, author, issued, issued_to_member_id " +
                "FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author")
                );

                if (resultSet.getBoolean("issued")) {
                    book.issueBook(
                            resultSet.getInt("issued_to_member_id")
                    );
                }

                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Database error while loading books.");
            e.printStackTrace();
        }
    }

    private void loadMembersFromDatabase() {

        String sql = "SELECT id, name, email FROM members";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Member member = new Member(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );

                members.add(member);
            }

        } catch (SQLException e) {
            System.out.println("Database error while loading members.");
            e.printStackTrace();
        }
    }

    // =========================
    // FIND METHODS
    // =========================

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