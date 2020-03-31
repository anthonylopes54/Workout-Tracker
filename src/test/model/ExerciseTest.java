package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests the functionality of the methods in the Exercise class

class ExerciseTest {
    Exercise testExercise;

    @BeforeEach
    public void runBefore(){
        testExercise = new Exercise("OHP", 4, 10);
    }

    @Test
    public void testConstructor(){
        assertEquals(4, testExercise.getSets());
        assertEquals(10, testExercise.getReps());
        assertTrue(testExercise.getName().equals("OHP"));
    }

    @Test
    public void testChangeReps(){
        changeReps(12);
        assertEquals(12, testExercise.getReps());


        changeReps(8);
        assertEquals(8, testExercise.getReps());

    }

    @Test
    public void testChangeRepsLessThanZero() {
        changeReps(-1);
        assertEquals(0, testExercise.getReps());
    }

    @Test
    public void testChangeRepsEqualToZero() {
        changeReps(0);
        assertEquals(0, testExercise.getReps());
    }


    @Test
    public void testChangeSets(){
        testExercise.changeSets(5);
        assertEquals(5, testExercise.getSets());

        testExercise.changeSets(1);
        assertEquals(1, testExercise.getSets());
    }

    @Test
    public void testChangeSetsLessThanZeroInput() {
        testExercise.changeSets(-1);
        assertEquals(0,testExercise.getSets());
    }

    @Test
    public void testChangeSetsEqualToZeroInput() {
        testExercise.changeSets(0);
        assertEquals(0, testExercise.getSets());
    }

    @Test
    public void testAddNote() {
        testExercise.addNote("slow-eccentric");
        String notes = testExercise.getNotes();
        assertTrue(notes.equals("The notes are as follows:\n- slow-eccentric" + "\n"));
        assertEquals(1, testExercise.getListOfNote().size());

    }

    @Test
    public void testGetNotesWhenListOfNoteIsEmpty() {
        assertTrue(testExercise.getNotes().equals("There are no notes for this exercise."));
    }

    @Test
    public void testGetNotesGUINotEmpty() {
        for (int i=0; i<10; i++) {
            testExercise.addNote("slow-eccentric" + i);
        }
        String notes = "";

        for (Note next : testExercise.getListOfNote()) {
            notes += "- " + next.getNote() + "\n";
        }

        assertEquals(10, testExercise.getListOfNote().size());
        String comparisonTest = testExercise.getNotesGUI();
        assertTrue((notes.equals(comparisonTest)));
    }

    @Test
    public void testGetNotesGIOEmpty() {
        assertTrue(testExercise.getNotesGUI().equals(""));
    }

    @Test
    public void testAddMultipleNotes() {
        for (int i=0; i<10; i++) {
            testExercise.addNote("slow-eccentric" + i);
        }
        String notes = "The notes are as follows:\n";

        for (Note next : testExercise.getListOfNote()) {
            notes += "- " + next.getNote() + "\n";
        }

        assertEquals(10, testExercise.getListOfNote().size());
        String comparisonTest = testExercise.getNotes();
        assertTrue(notes.equals(comparisonTest));

    }

    @Test
    public void testRemoveNote() {
        testExercise.addNote("slow-eccentric");
        String notes = testExercise.getNotes();
        assertTrue(notes.equals("The notes are as follows:\n- slow-eccentric" + "\n"));
        assertEquals(1, testExercise.getListOfNote().size());

        testExercise.addNote("tempo");
        testExercise.addNote("another note");
        assertEquals(3, testExercise.getListOfNote().size());

        assertTrue(testExercise.removeNote("another note"));
        assertEquals(2, testExercise.getListOfNote().size());

        assertTrue(testExercise.removeNote("slow-eccentric"));
        assertEquals(1,testExercise.getListOfNote().size());
        assertTrue(testExercise.getNotes().equals("The notes are as follows:\n- tempo" + "\n"));
    }

    @Test
    public void testRemoveNoteWithNoteParameter() {
        testExercise.addNote("slow-eccentric");
        String notes = testExercise.getNotes();
        assertTrue(notes.equals("The notes are as follows:\n- slow-eccentric" + "\n"));
        assertEquals(1, testExercise.getListOfNote().size());

        testExercise.addNote("tempo");
        testExercise.addNote("another note");
        assertEquals(3, testExercise.getListOfNote().size());

        Note newNote1 = new Note("another note");
        assertTrue(testExercise.removeNote(newNote1));
        assertEquals(2, testExercise.getListOfNote().size());

        Note newNote2 = new Note("slow-eccentric");
        assertTrue(testExercise.removeNote(newNote2));
        assertEquals(1,testExercise.getListOfNote().size());
        assertTrue(testExercise.getNotes().equals("The notes are as follows:\n- tempo" + "\n"));
    }

    @Test
    public void testRemoveNoteWithNoteNotInList() {
        testExercise.addNote("another note");
        assertFalse(testExercise.removeNote("hello"));
    }

    @Test
    public void testRemoveNoteWithNoteNotInListWithNoteParameter() {
        Note newNote = new Note("another noe");
        testExercise.addNote("another note");
        assertFalse(testExercise.removeNote(newNote));
    }

    @Test
    public void testChangeName() {
        testExercise.changeName("anthony");
        assertTrue(testExercise.getName().equals("anthony"));
    }

    // HELPERS

    // MODIFIES: this
    // EFFECTS: calls Exercise.changeSets with given int

    private void changeReps(int i) {
        testExercise.changeReps(i);
    }
}