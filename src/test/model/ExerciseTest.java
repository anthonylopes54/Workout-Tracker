package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        testExercise.changeReps(12);
        assertEquals(12, testExercise.getReps());

        testExercise.changeReps(8);
        assertEquals(8, testExercise.getReps());
    }

    @Test
    public void testChangeSets(){
        testExercise.changeSets(5);
        assertEquals(5, testExercise.getSets());

        testExercise.changeSets(1);
        assertEquals(1, testExercise.getSets());
    }

    @Test
    public void testAddNote() {
        testExercise.addNote("slow-eccentric");
        String notes = testExercise.getNotes();
        assertTrue(notes.equals("slow-eccentric"));
        assertEquals(1, testExercise.listOfNotes.size());

    }

    @Test
    public void testGetNotesWhenListOfNoteIsEmpty() {
        assertTrue(testExercise.getNotes().equals("There are no notes for this exercise."));
    }

    @Test
    public void testAddMultipleNotes() {
        for (int i=0; i<10; i++) {
            testExercise.addNote("slow-eccentric" + i);
        }
        String notes = "";

        for (String next: testExercise.listOfNotes) {
            notes += (next + "/n");
        }

        assertEquals(10, testExercise.listOfNotes.size());
        assertTrue(notes.equals(testExercise.getNotes()));

    }

    @Test
    public void testRemoveNote() {
        testExercise.addNote("slow-eccentric");
        String notes = testExercise.getNotes();
        assertTrue(notes.equals("slow-eccentric"));
        assertEquals(1, testExercise.listOfNotes.size());

        testExercise.addNote("tempo");
        testExercise.addNote("another note");
        assertEquals(2, testExercise.listOfNotes.size());

        assertTrue(testExercise.removeNote("tempo"));
        assertEquals(2, testExercise.listOfNotes.size());

        assertTrue(testExercise.removeNote("slow-eccentric"));
        assertEquals(1,testExercise.listOfNotes.size());
        assertTrue(testExercise.getNotes().equals("another note"));
    }
}