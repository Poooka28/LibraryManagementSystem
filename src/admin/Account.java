package admin;

import java.io.Serializable;

public class Account implements Serializable{
    private String userName;
    private String libraryId;

    public Account(String userName, String libraryId) {
        super();
        this.userName = userName;
        this.libraryId = libraryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getlibraryId() {
        return libraryId;
    }

    public void setUserPassword(String userPassword) {
        this.libraryId = libraryId;
    }

    @Override
    public String toString() {
        return  userName + "@@11" + libraryId + "@@11";
    }
}
