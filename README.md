================================================================================
                           LIBRARY MANAGEMENT SYSTEM
================================================================================

A console-based Library Management System developed using Java, Object-Oriented 
Programming (OOP), JDBC, and MySQL.

--------------------------------------------------------------------------------
1. FEATURES
--------------------------------------------------------------------------------
- Book Management  : Add, view, search by title, and remove books.
- Member Management: Register and view library members.
- Transactions     : Issue books to members and process returns.
- Data Persistence : Relational data storage using MySQL and JDBC connectivity.


--------------------------------------------------------------------------------
2. TECHNOLOGIES USED
--------------------------------------------------------------------------------
- Language       : Java
- Concepts       : Object-Oriented Programming (OOP)
- Database       : MySQL
- Driver         : JDBC (MySQL Connector/J)
- IDE            : IntelliJ IDEA
- Version Control: Git & GitHub


--------------------------------------------------------------------------------
3. PROJECT STRUCTURE
--------------------------------------------------------------------------------
LibraryManagementSystem/
│
├── Book.java
├── Member.java
├── Library.java
├── DatabaseConnection.java
├── LibraryManagementSystem.java
├── README.md
└── .gitignore


--------------------------------------------------------------------------------
4. DATABASE SETUP
--------------------------------------------------------------------------------
Database Name: library_management

--- Books Table ---
CREATE TABLE books (
    id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    issued BOOLEAN DEFAULT FALSE,
    issued_to_member_id INT DEFAULT NULL
);

--- Members Table ---
CREATE TABLE members (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);


--------------------------------------------------------------------------------
5. HOW TO RUN
--------------------------------------------------------------------------------
1. Install Java Development Kit (JDK) and MySQL Server.
2. Create the database "library_management" in MySQL.
3. Execute the SQL statements above to create the tables.
4. Open the project in IntelliJ IDEA and add the MySQL Connector/J dependency.
5. Set the environment variable for database authentication:
     export LIBRARY_DB_PASSWORD="your_mysql_password"
6. Run LibraryManagementSystem.java.


--------------------------------------------------------------------------------
6. CONCEPTS DEMONSTRATED
--------------------------------------------------------------------------------
- Core OOP           : Encapsulation, classes, and objects
- Data Structures    : ArrayList and Collection APIs
- Database Operations: JDBC, PreparedStatement, SQL CRUD operations
- Software Design    : Exception handling, separation of responsibilities


--------------------------------------------------------------------------------
7. AUTHOR
--------------------------------------------------------------------------------
Harshit Kumar Singh
================================================================================