package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {
    Workout testWorkout;
    Exercise exercise1;
    Exercise exercise2;
    Exercise exercise3;
    Exercise exercise4;

    @BeforeEach
    public void runBefore() {
        testWorkout = new Workout("Back Blast", "Lots of back with a hypertrophy focus!", false);
        exercise1 = new Exercise("Lat Pulldown", 3, 10);
        exercise2 = new Exercise("Dumbbell Row", 4, 12);
        exercise3 = new Exercise("Face Pulls", 3, 12);
        exercise4 = new Exercise("Pull Ups", 3, 8);

    }

    @Test
    public void testConstructor() {
        assertTrue(testWorkout.getDescription().equals("Lots of back with a hypertrophy focus!"));
        assertTrue(testWorkout.getName().equals("Back Blast"));
        assertEquals(0, testWorkout.getListOfExercise().size());
    }

    @Test
    public void testAddExercise() {
        testWorkout.addExercise(exercise1);
        assertEquals(1, testWorkout.getListOfExercise().size());
        assertTrue(testWorkout.getListOfExercise().contains(exercise1));
    }

    @Test
    public void testAddExerciseMultiple() {
        for (int i=0; i<5; i++) {
            testWorkout.addExercise(exercise1);
        }
        assertEquals(5, testWorkout.getListOfExercise().size());
        assertEquals(exercise1, testWorkout.getListOfExercise().get(0));
        assertEquals(exercise1, testWorkout.getListOfExercise().get(4));

        for (int i=0; i<5; i++) {
            testWorkout.addExercise(exercise2);

        }
        assertEquals(10, testWorkout.getListOfExercise().size());
        assertEquals(exercise1, testWorkout.getListOfExercise().get(4));
        assertEquals(exercise2, testWorkout.getListOfExercise().get(5));
        assertEquals(exercise2, testWorkout.getListOfExercise().get(9));
    }

    @Test
    public void testRemoveExerciseEmptyList() {
        assertFalse(testWorkout.removeExercise(exercise1));
    }

    @Test
    public void testRemoveExerciseSingleExercise() {
        testWorkout.addExercise(exercise1);
        testWorkout.addExercise(exercise2);

        assertTrue(testWorkout.removeExercise(exercise1));
        assertEquals(1, testWorkout.getListOfExercise().size());
        assertEquals(exercise2, testWorkout.getListOfExercise().get(0));

        testWorkout.addExercise(exercise3);
        testWorkout.addExercise(exercise4);
        assertEquals(3, testWorkout.getListOfExercise().size());
        assertTrue(testWorkout.removeExercise(exercise3));
        assertFalse(testWorkout.getListOfExercise().contains(exercise3));
    }

    @Test
    public void testRemoveExerciseMultiple(){
        addExercises();

        assertTrue(testWorkout.removeExercise(exercise3));
        assertTrue(testWorkout.removeExercise(exercise2));
        assertTrue(testWorkout.removeExercise(exercise1));

        assertFalse(testWorkout.getListOfExercise().contains(exercise3));
        assertFalse(testWorkout.getListOfExercise().contains(exercise2));
        assertFalse(testWorkout.getListOfExercise().contains(exercise1));
        assertEquals(1, testWorkout.getListOfExercise().size());
    }

    @Test
    public void testPrintWorkoutNoExercise() {
        assertTrue(testWorkout.printWorkout().equals("There are no exercises in this workout!"));
    }

    @Test
    public void testPrintWorkoutSingleExercise() {
        testWorkout.addExercise(exercise1);

        String workout = "Exercise: " + exercise1.getName() + " Sets: " + exercise1.getSets() + " Reps: "
                + exercise1.getReps() + "\n";

        assertTrue(workout.equals(testWorkout.printWorkout()));
    }


    @Test
    public void testPrintWorkoutMultipleExercises() {
        addExercises();

        String workout = "Exercise: " + exercise4.getName() + " Sets: " + exercise4.getSets()
                + " Reps: " + exercise4.getReps() + "\n"
                + "Exercise: " + exercise3.getName() + " Sets: " + exercise3.getSets()
                + " Reps: " + exercise3.getReps() + "\n"
                + "Exercise: " + exercise2.getName() + " Sets: " + exercise2.getSets()
                + " Reps: " + exercise2.getReps() + "\n"
                + "Exercise: " + exercise1.getName() + " Sets: " + exercise1.getSets()
                + " Reps: " + exercise1.getReps() + "\n";

        assertTrue(testWorkout.printWorkout().equals(workout));
    }

    @Test
    public void testAddToFavourites() {
        testWorkout.addToFavourites();
        assertTrue(testWorkout.getFavourite());
    }

    @Test
    public void testRemoveFromFavourites() {
        testWorkout.addToFavourites();
        testWorkout.removeFromFavourites();
        assertFalse(testWorkout.getFavourite());
    }



    // HELPERS

    // EFFECTS: adds exercises 1 through 4 to listOfExercise

    private void addExercises() {
        testWorkout.addExercise(exercise4);
        testWorkout.addExercise(exercise3);
        testWorkout.addExercise(exercise2);
        testWorkout.addExercise(exercise1);
    }
}
