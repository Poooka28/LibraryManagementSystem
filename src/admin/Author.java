package admin;
public class Author {
    protected String authorName;
    protected int authorId;
    protected String authorContact;
    protected String authorEmail;

    public Author(String authorName, int authorId, String authorContact, String authorEmail) {
        super();
        this.authorName = authorName;
        this.authorId = authorId;
        this.authorContact = authorContact;
        this.authorEmail = authorEmail;
    }
    @Override
    public String toString() {
        return authorName + " " + authorId + " " + authorContact + " " + authorEmail;
    }
}
