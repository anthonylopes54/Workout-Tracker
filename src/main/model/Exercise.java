package model;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

// This class holds all the info pertaining to an exercise (name, sets, reps, and a list of notes)

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private ArrayList<String> listOfNote;

    public Exercise(String name, int sets, int reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        listOfNote = new ArrayList<>();
    }

    public Exercise(String name, int sets, int reps, ArrayList<String> listOfNote) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.listOfNote = listOfNote;
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
        if (listOfNote.size() == 0) {
            return "There are no notes for this exercise.";
        }
        String notes = "The notes are as follows:\n";
        for (String next : listOfNote) {
            notes += "- " + next + "\n";
        }
        return notes;
    }

    // EFFECTS: returns all the elements in listOfNote
    public String getNotesGUI() {
        if (listOfNote.size() == 0) {
            return "";
        }
        String notes = "";
        for (String next : listOfNote) {
            notes += "- " + next + "\n";
        }
        return notes;
    }

    // MODIFIES: this
    // EFFECTS: adds given String to listOfNote

    public void addNote(String note) {
        listOfNote.add(note);

    }

    // MODIFIES: this
    // EFFECTS: removes given string from listOfNote and returns true;
    //          otherwise return false

    public boolean removeNote(String note) {
        String noteToRemove = "";
        for (String next : listOfNote) {
            if (next.equals(note)) {
                noteToRemove = next;
                break;
            }
        }
        if (!listOfNote.contains(note)) {
            return false;
        }
        listOfNote.remove(noteToRemove);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: changes this name to the given name

    public void changeName(String name) {
        this.name = name;
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

    // EFFECTS: returns listOfNote

    public ArrayList<String> getListOfNote() {
        return listOfNote;
    }
}
