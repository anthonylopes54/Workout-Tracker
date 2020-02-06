package model;

import java.util.ArrayList;

public class Exercise {
    String name;
    int sets;
    int reps;
    ArrayList<String> listOfNotes;

    public Exercise(String name, int sets, int reps){  //Should I add a description of the exercise?
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        listOfNotes = new ArrayList<>();
    }

    // EFFECTS: returns the number of sets for the given exercise

    public int getSets() {
        return this.sets;
    }

    //EFFECTS: returns the number of reps for the given exercise

    public int getReps() {
        return this.reps;
    }

    // EFFECTS: returns the name of the exercise

    public String getName() {
        return this.name;
    }

    // EFFECTS: returns all the elements in listOfNote

    public String getNotes() {
        if (listOfNotes.size() == 0) {
            return "There are no notes for this exercise.";
        }
        String notes = "";
        for (String next : listOfNotes) {
            notes += next + "\n";
        }
        return notes;
    }

    // SHould i have a get note getter?

    // MODIFIES: this
    // EFFECTS: adds given String to listOfNote

    public void addNote(String note) {
        listOfNotes.add(note);

    }

    // MODIFIES: this
    // EFFECTS: removes given string from listOfNote and returns true;
    //          otherwise return false

    public boolean removeNote(String note) {
        if (!listOfNotes.contains(note)) {
            return false;
        } else {
            for (String next: listOfNotes) {
                if (next.equals(note)) {
                    listOfNotes.remove(next);
                }
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: changes the value of the reps field with given parameter;
    //          if r <= 0, change value of reps to 0

    public void changeReps(int r) {
        if (r <= 0) {
            reps = 0;
        } else {
            reps = r;
        }

    }

    // MODIFIES: this
    // EFFECTS: changes the value of the sets field with the given parameter;
    //          if s <= 0, change value of sets to 0

    public void changeSets(int s) {
        if (s <= 0) {
            sets = 0;
        } else {
            sets = s;
        }
    }
}
