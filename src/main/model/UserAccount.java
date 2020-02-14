package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

public class UserAccount {
    private String username;
    private String password;
    private WorkoutList workoutList;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        workoutList = new WorkoutList();
    }

    // EFFECTS: returns true if workoutList is empty; otherwise false;

//    public boolean isEmpty() {
//        return false;
//    }

    // EFFECTS: returns password

    public String getPassword() {
        return password;
    }

    // EFFECTS: returns username

    public String getUsername() {
        return username;
    }

    // EFFECTS: return true if the given string matches the password

    public boolean doesPasswordMatch(String userEntry) {
        return (password.equals(userEntry));
    }

    // EFFECTS: returns the size of the workoutList

    public int getSize() {
        return workoutList.getSize();
    }


}
