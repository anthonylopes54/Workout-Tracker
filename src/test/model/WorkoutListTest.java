package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutListTest {

    WorkoutList testWorkoutList;
    Workout testWorkout1;
    Workout testWorkout2;
    Workout testWorkout3;

    @BeforeEach
    public void runBefore() {
        testWorkoutList = new WorkoutList();
        testWorkout1 = new Workout("Back Blast", "Hypertrophy-based", false);
        testWorkout2 = new Workout("Chest Blast", "Strength-Based", true);
        testWorkout3 = new Workout("HIIT", "Non-Traditional Cardio", false);
    }

    @Test
    public void testConstructor(){
        assertEquals(0, testWorkoutList.getSize());
    }

    @Test
    public void testPrintListOfWorkoutsSingleWorkout() {
        testWorkoutList.addWorkout(testWorkout1);
        String workout = "Workout: " + testWorkout1.getName() + "\n      Description: " + testWorkout1.getDescription()
                + "\n";
        assertTrue(testWorkoutList.printListOfWorkouts().equals(workout));
    }

    @Test
    public void testPrintListOfWorkoutsMultipleWorkout() {
        addWorkouts();
        String workout = "Workout: " + testWorkout1.getName() + "\n      Description: " + testWorkout1.getDescription() + "\n"
               + "Workout: " + testWorkout2.getName() + "\n      Description: " + testWorkout2.getDescription() + "\u066D\n"
                + "Workout: " + testWorkout3.getName() + "\n      Description: " + testWorkout3.getDescription() + "\n";

        assertTrue(workout.equals(testWorkoutList.printListOfWorkouts()));


    }

    @Test
    public void testAddSingleWorkout() {
        testWorkoutList.addWorkout(testWorkout1);
        assertEquals(1, testWorkoutList.getListOfWorkout().size());
        assertTrue(testWorkoutList.getListOfWorkout().contains(testWorkout1));
    }

    @Test
    public void testAddMultipleWorkout() {
        for (int i=0 ; i<5 ; i++) {
            testWorkoutList.addWorkout(testWorkout1);
        }

        assertEquals(5, testWorkoutList.getSize());
        assertEquals(testWorkout1, testWorkoutList.getListOfWorkout().get(0));
        assertEquals(testWorkout1, testWorkoutList.getListOfWorkout().get(4));

        for (int i=0 ; i<5 ; i++) {
            testWorkoutList.addWorkout(testWorkout2);
        }

        assertEquals(10, testWorkoutList.getSize());
        assertEquals(testWorkout2, testWorkoutList.getListOfWorkout().get(5));
        assertEquals(testWorkout2, testWorkoutList.getListOfWorkout().get(9));
    }

    @Test
    public void testRemoveSingleWorkout() {
        testWorkoutList.addWorkout(testWorkout1);
        testWorkoutList.removeWorkout(testWorkout1);
        assertEquals(0,testWorkoutList.getSize());
    }

    @Test
    public void testRemoveMultipleWorkouts() {
        addWorkouts();

        assertTrue(testWorkoutList.removeWorkout(testWorkout2));
        assertFalse(testWorkoutList.contains(testWorkout2));
        assertEquals(2, testWorkoutList.getSize());

        assertTrue(testWorkoutList.removeWorkout(testWorkout1));
        assertFalse(testWorkoutList.contains(testWorkout1));
        assertEquals(1, testWorkoutList.getSize());

        assertTrue(testWorkoutList.removeWorkout(testWorkout3));
        assertFalse(testWorkoutList.contains(testWorkout3));
        assertEquals(0, testWorkoutList.getSize());
    }


    @Test
    public void testGetWorkout() {
        addWorkouts();

        assertEquals(testWorkout2, testWorkoutList.getWorkout(testWorkout2.getName()));

    }

    // HELPERS

    // EFFECTS: adds workouts 1 through 3

    private void addWorkouts() {
        testWorkoutList.addWorkout(testWorkout1);
        testWorkoutList.addWorkout(testWorkout2);
        testWorkoutList.addWorkout(testWorkout3);
    }

}
