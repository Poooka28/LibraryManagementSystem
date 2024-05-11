package user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import admin.*;

public class LibraryGUI {

    private static final String validUsername = "admin";
    private static final String validPassword = "Passw0rd";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("Central Library", "123 Main St", "555-1234", "library@example.com", "John Doe");
        boolean start = true;
        int choice;

        try {
            System.out.println("LOGIN PAGE");
            System.out.println("Enter Username: ");
            String usernameString = scanner.nextLine();
            System.out.println("Enter Password: ");
            String passwordString = scanner.nextLine();

            validateCredentials(usernameString, passwordString);
            System.out.println("Login Successful!!");
            System.err.println("Welcome to Library System " + validUsername.toUpperCase());

        } catch (InvalidCredentialsException e) {
            System.out.println("Invalid credentials: " + e);
            start = false;
            main(args);
        }

        while (start) {
            System.out.println("Menu:");
            System.out.println("1. Add book");
            System.out.println("2. Search book by id or name");
            System.out.println("3. Print all books");
            System.out.println("4. Delete book");
            System.out.println("5. Issue book");
            System.out.println("6. Return book");
            System.out.println("7. Add a User");
            System.out.println("8. Print all Existing Users");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

//			readCharacterData("src/files/books.txt", library);

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(library);
                    break;
                case 2:
                    searchBook(library);
                    break;
                case 3:
                    printAllBooks(library);
                    break;
                case 4:
                    deleteBook(library);
                    break;
                case 5:
                    issueBook(library);
                    break;
                case 6:
                    returnBook(library);
                    break;
                case 7:
                    addValidUsers();
                    break;
                case 8:
                    printValidUsers();
                    break;
                case 0:
                    System.out.println("Exited from the System....");
                    start = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }

    }

    private static void addValidUsers() throws IOException {

        Scanner scanner = new Scanner(System.in);
        ObjectOutputStream out = null;
        System.out.println("Enter the User Name you want to add: ");
        String userName = scanner.nextLine();
        System.out.println("Enter the Library ID you want to add: ");
        String libraryId = scanner.nextLine();
        Account account = new Account(userName, libraryId);

        try (FileOutputStream fout = new FileOutputStream("src/files/accounts.txt")) {

            out = new ObjectOutputStream(fout);
            out.writeObject(account);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static void printValidUsers() throws IOException, ClassNotFoundException {

        FileInputStream fin = new FileInputStream("src/files/accounts.txt");
        ObjectInputStream in = new ObjectInputStream(fin);
        Account allAccount = (Account) in.readObject();
        String[] validUser = allAccount.toString().split("@@11");

        for (int i = 0; i < validUser.length-1; i++) {

            System.out.println("User Name: " + validUser[i] + " " + "Library ID: " + validUser[i+1]);

        }
        in.close();
    }

    private static void readCharacterData(String fileName, Library library) throws IOException {

        FileReader fileReader = new FileReader(fileName);
        int ch = 0;
        String detail = "";

        while ((ch = fileReader.read()) != -1) {
            detail += (char) ch;
        }
        if (detail != "") {

            String[] detailsarr = detail.split("||");
            for (int i = 0; i < detailsarr.length; i++) {
                String[] elementarr = detailsarr[i].split(" ");
                String bookName = elementarr[0];
                String isbn = elementarr[1];
                double price = Double.parseDouble(elementarr[2]);
                int pages = Integer.parseInt(elementarr[3]);
                String pubName = elementarr[4];
                String authorName = elementarr[5];
                int authorId = Integer.parseInt(elementarr[6]);
                String authorContact = elementarr[7];
                String authorEmail = elementarr[8];

                Author author = new Author(authorName, authorId, authorContact, authorEmail);
                Book book = new Book(bookName, isbn, price, pages, pubName, author);

                library.addBook(book);
            }

        }

    }

    private static void addBook(Library library) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book details:");
        System.out.print("Book name: ");
        String bookName = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Number of pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Publisher name: ");
        String pubName = scanner.nextLine();
        System.out.print("Author name: ");
        String authorName = scanner.nextLine();
        System.out.print("Author ID: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Author contact: ");
        String authorContact = scanner.nextLine();
        System.out.print("Author email: ");
        String authorEmail = scanner.nextLine();

        Author author = new Author(authorName, authorId, authorContact, authorEmail);
        Book book = new Book(bookName, isbn, price, pages, pubName, author);

        library.addBook(book);

        writeCharacterData("src/files/books.txt", book);

        System.out.println("Book added successfully!");

    }

    private static void writeCharacterData(String fileName, Book book) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(book.toString());
            fileWriter.write("||");
            fileWriter.write(System.lineSeparator());

        }

    }

    private static void searchBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name or ID to search: ");
        String keyword = scanner.nextLine();
        Book foundBook = library.searchBook(keyword);
        if (foundBook != null) {
            System.out.println("Book found:");
            System.out.println(foundBook);
        } else
            System.out.println("Book Not Found");
    }

    private static void printAllBooks(Library library) {
        System.out.println("All books in the library:");
        library.printAllBooks();
    }

    private static void deleteBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name or ID to delete: ");
        String keyword = scanner.nextLine();
        boolean deleted = library.deleteBook(keyword);
        if (deleted) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found or could not be deleted.");
        }
    }

    private static void issueBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name or ID to issue: ");
        String keyword = scanner.nextLine();
        boolean issued = library.issueBook(keyword);
        if (issued) {
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not found or could not be issued.");
        }
    }

    private static void returnBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name or ID to return: ");
        String keyword = scanner.nextLine();
        boolean returned = library.returnBook(keyword);
        if (returned) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or could not be returned.");
        }
    }

    private static void validateCredentials(String usernameString, String passwordString)
            throws InvalidCredentialsException, IOException {

        if (!usernameString.equals(validUsername) || !passwordString.equals(validPassword)) {
            throw new InvalidCredentialsException("Username or password is incorrect.");
        }

    }

}