package model;

import java.util.ArrayList;

public class Exercise {
    String name;
    int sets;
    int reps;
    ArrayList<String> listOfNotes;

    public Exercise(String name, int sets, int reps){  //Should I add a description of the exercise?

    }

    // EFFECTS: returns the number of sets for the given exercise

    public int getSets() {
        return 0;
    }

    //EFFECTS: returns the number of reps for the given exercise

    public int getReps() {
        return 0;
    }

    // EFFECTS: returns the name of the exercise

    public String getName() {
        return "";
    }

    // EFFECTS: returns all the elements in listOfNote

    public String getNotes() {
        return "";
    }

    // SHould i have a get note getter?

    // MODIFIES: this
    // EFFECTS: adds given String to listOfNote

    public void addNote(String note) {

    }

    // MODIFIES: this
    // EFFECTS: removes given string from listOfNote

    public void removeNote(String note) {

    }

    // MODIFIES: this
    // EFFECTS: changes the value of the reps field with given parameter;
    //          if r <= 0, change value of reps to 0

    public void changeReps(int r) {

    }

    // MODIFIES: this
    // EFFECTS: changes the value of the sets field with the given parameter;
    //          if s <= 0, change value of sets to 0

    public void changeSets(int s) {

    }
}
