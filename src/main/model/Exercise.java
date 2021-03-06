package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

// This class holds all the info pertaining to an exercise (name, sets, reps, and a list of notes)

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private ArrayList<Note> listOfNote;

    public Exercise(String name, int sets, int reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        listOfNote = new ArrayList<>();
    }

    public Exercise(String name, int sets, int reps, ArrayList<Note> listOfNote) {
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
        for (Note next : listOfNote) {
            notes += next.returnFormattedNote();
        }
        return notes;
    }

    // EFFECTS: returns all the elements in listOfNote
    public String getNotesGUI() {
        if (listOfNote.size() == 0) {
            return "";
        }
        String notes = "";
        for (Note next : listOfNote) {
            notes += next.returnFormattedNote();
        }
        return notes;
    }

    // MODIFIES: this
    // EFFECTS: adds given String to listOfNote

    public void addNote(String note) {
        Note newNote = new Note(note);
        listOfNote.add(newNote);

    }

    // MODIFIES: this
    // EFFECTS: removes given string from listOfNote and returns true;
    //          otherwise return false

    public boolean removeNote(String note) {
        Note noteToRemove = new Note(note);
        for (Note next : listOfNote) {
            if (next.getNote() == noteToRemove.getNote()) {
                noteToRemove = next;
                break;
            }
        }
        if (!listOfNote.contains(noteToRemove)) {
            return false;
        }
        listOfNote.remove(noteToRemove);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: removes given string from listOfNote and returns true;
    //          otherwise return false

    public boolean removeNote(Note note) {
        Note noteToRemove = null;
        for (Note next : listOfNote) {
            if (next.getNote().equals(note.getNote())) {
                noteToRemove = next;
                break;
            }
        }
        if (!listOfNote.contains(noteToRemove)) {
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

    public ArrayList<Note> getListOfNote() {
        return listOfNote;
    }

    // EFFECTS: encodes the given list of exercises into the given JSONArray

    public static void save(ArrayList<Exercise> listOfExercise, JSONArray objectToEncodeIn) {
        for (Exercise next : listOfExercise) {
            JSONObject obj = new JSONObject();
            obj.put("name", next.getName());
            obj.put("sets", next.getSets());
            obj.put("reps", next.getReps());

            JSONArray listOfNoteEncoded = new JSONArray();
            for (Note note: next.getListOfNote()) {
                note.save(note, listOfNoteEncoded);
            }
            obj.put("listOfNote", listOfNoteEncoded);
            objectToEncodeIn.add(obj);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses the given JSONArray into an ArrayList of exercises

    public static void read(JSONArray jsonExercises, ArrayList<Exercise> listOfExercise) {
        for (Object obj : jsonExercises) {
            JSONObject exercise = (JSONObject) obj;

            String name = (String) exercise.get("name");
            int sets = ((Long) exercise.get("sets")).intValue();
            int reps = ((Long) exercise.get("reps")).intValue();
            JSONArray listOfEncodedNote = (JSONArray) exercise.get("listOfNote");

            ArrayList<Note> listOfNote = new ArrayList<>();
            for (Object encodedNote: listOfEncodedNote) {
                Note.read(encodedNote, listOfNote);
            }
            Exercise thisExercise = new Exercise(name, sets, reps, listOfNote);
            listOfExercise.add(thisExercise);
        }
    }
}
