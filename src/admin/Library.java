package admin;

import java.util.Arrays;
public class Library {
    protected String libName;
    protected String libAddress;
    protected String libContact;
    protected String libEmail;
    protected String libOwner;
    public Book[] books;
    protected String[] issueBooks = new String[10];
    protected int j = 0;
    protected int flag = 0;

    public Library(String libName, String libAddress, String libContact, String libEmail, String libOwner) {
        super();
        this.libName = libName;
        this.libAddress = libAddress;
        this.libContact = libContact;
        this.libEmail = libEmail;
        this.libOwner = libOwner;
        this.books = new Book[1];
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getLibAddress() {
        return libAddress;
    }

    public void setLibAddress(String libAddress) {
        this.libAddress = libAddress;
    }

    public String getLibContact() {
        return libContact;
    }

    public void setLibContact(String libContact) {
        this.libContact = libContact;
    }

    public String getLibEmail() {
        return libEmail;
    }

    public void setLibEmail(String libEmail) {
        this.libEmail = libEmail;
    }

    public String getLibOwner() {
        return libOwner;
    }

    public void setLibOwner(String libOwner) {
        this.libOwner = libOwner;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] bookArrBooks) {
        this.books = bookArrBooks;
    }

    @Override
    public String toString() {
        return "Library [libName=" + libName + ", libAddress=" + libAddress + ", libContact=" + libContact
                + ", libEmail=" + libEmail + ", libOwner=" + libOwner + ", bookArrBooks=" + Arrays.toString(books)
                + "]";
    }

    public void addBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                return;
            }
        }
        books = Arrays.copyOf(books, books.length + 1);
        books[books.length - 1] = book;
    }

    public Book searchBook(String keyword) {

        try {
            for (int i = 0; i < books.length; i++) {
                if (books[i].bookName.equals(keyword) | books[i].ISBN.equals(keyword)) {
                    return books[i];
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean issueBook(String keyword) {
        try {
            for (int i = 0; i < books.length; i++) {
                if (books[i].bookName.equals(keyword) | books[i].ISBN.equals(keyword)) {
                    for (int k = 0; k < issueBooks.length; k++) {
                        if (keyword.equals(issueBooks[k])) {
                            flag = 0;
                        } else {
                            issueBooks[j++] = keyword;
                            flag = 1;
                        }
                    }
                } else {
                    flag = 0;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (flag == 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean returnBook(String keyword) {
        try {
            for (int i = 0; i < books.length; i++) {
                if (books[i].bookName.equals(keyword) | books[i].ISBN.equals(keyword)) {
                    for (int k = 0; k < issueBooks.length; k++) {
                        if (keyword.equals(issueBooks[k])) {
                            // return false;
                            flag = 1;
                            issueBooks[k] = null;
                        } else {
                            // return true;
                            flag = 0;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (flag == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void printAllBooks() {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }
    }

    public boolean deleteBook(String keyword) {
        int i, j;
        boolean bookexists = false;
        for (i = 0, j = 0; j < books.length; j++) {
            if (books[j].bookName.equals(keyword) | books[j].ISBN.equals(keyword)) {
                j++;
                bookexists = true;
            } else
                books[i++] = books[j];
        }

        books = Arrays.copyOf(books, i);

        return bookexists;
    }

//	public boolean checkifalreadyexists(String bookName, String isbn) {
//		for (int i = 0; i < books.length; i++) {
//			if (books[i].bookName.equals(bookName) | books[i].ISBN.equals(isbn)) {
//				return true;
//			}
//		}
//		return false;
//	}

}
