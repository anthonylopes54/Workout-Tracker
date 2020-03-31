package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class Note {
    private String note;

    public Note(String note) {
        this.note = note;
    }

    // EFFECTS: encodes the given Note into a JSONObject and adds the JSONObject to the JSONArray

    public static void save(Note note, JSONArray listOfNoteEncoded) {
        JSONObject noteToAdd = new JSONObject();
        noteToAdd.put("note", note.getNote());
        listOfNoteEncoded.add(noteToAdd);
    }

    // MODIFIES: this
    // EFFECTS: parses the given JSONObject into a Note and adds the Note to the given ArrayList

    public static void read(Object encodedNote, ArrayList<Note> listOfNote) {
        JSONObject obj = (JSONObject) encodedNote;
        String note = (String) obj.get("note");
        Note parsedNote = new Note(note);
        listOfNote.add(parsedNote);
    }

    // EFFECTS: returns note in String format with a leading dash and new line escape character

    public String returnFormattedNote() {
        return "- " + note + "\n";
    }

    // EFFECTS: returns note

    public String getNote() {
        return this.note;
    }

    // EFFECTS: overrides equals so that it compares the note field

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Note note1 = (Note) o;

        return note != null ? note.equals(note1.note) : note1.note == null;
    }

    @Override
    public int hashCode() {
        return note != null ? note.hashCode() : 0;
    }
}
