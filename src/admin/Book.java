package admin;

public class Book{
    protected String bookName;
    protected String ISBN;
    protected double price;
    protected int noOfPages;
    protected String pubString;
    Author author;

    public Book(String bookName, String iSBN, double price, int noOfPages, String pubString, Author author) {
        super();
        this.bookName = bookName;
        this.ISBN = iSBN;
        this.price = price;
        this.noOfPages = noOfPages;
        this.pubString = pubString;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }


    public String getPubString() {
        return pubString;
    }
    public void setPubString(String pubString) {
        this.pubString = pubString;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return bookName + " " +  ISBN + " " + price + " "+ noOfPages + " "+ pubString+ " " + author;
    }
}