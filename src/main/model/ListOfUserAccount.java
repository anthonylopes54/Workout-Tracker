package model;

import java.util.ArrayList;

public class ListOfUserAccount {
    ArrayList<UserAccount> listOfUserAccount;

    public ListOfUserAccount() {
        listOfUserAccount = new ArrayList<>();
    }

    // EFFECTS: returns true if given string is a username in the listOfUserAccount

    public boolean doesUserExist(String username) {
        return false;
    }
}
