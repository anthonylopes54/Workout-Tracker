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
}