package persistance;

import model.Exercise;
import model.Workout;
import model.WorkoutList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Read;
import persistence.Write;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

// Tests the functionality of the methods in the Read class

public class ReadTest {
    WorkoutList testWorkoutList;
    private Workout testWorkout1;
    private Workout testWorkout2;
    private Workout testWorkout3;
    private Exercise exercise1;
    private Exercise exercise2;


    @BeforeEach
    public void runBefore() {
        testWorkout1 = new Workout("Back Blast", "Hypertrophy-based", false);
        testWorkout2 = new Workout("Chest Blast", "Strength-Based", true);
        testWorkout3 = new Workout("HIIT", "Non-Traditional Cardio", false);
        testWorkoutList = new WorkoutList();
        exercise1 = new Exercise("Lat Pulldown", 3, 10);
        exercise2 = new Exercise("Dumbbell Row", 4, 12);
    }

    @Test
    public void testConstructor() {
        Read test = new Read();
        assertTrue(test instanceof Read);
    }

    @Test
    public void testLoadWorkoutListExceptionThrown() {
        createWorkoutsAndExercises();

        try {
            Write.saveWorkoutList("testread", testWorkoutList);
        } catch (IOException e) {
            fail();
        }
        testWorkoutList.removeWorkout(testWorkout1);
        testWorkoutList.removeWorkout(testWorkout2);
        testWorkoutList.removeWorkout(testWorkout3);

        assertEquals(0, testWorkoutList.getSize());
        try {
            Read.readWorkoutList("tes", testWorkoutList);
            fail();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadWorkoutListGUI() {
        createWorkoutsAndExercises();

        try {
            Write.saveWorkoutList("testreadGUI", testWorkoutList);
        } catch (IOException e) {
            fail();
        }
        try {
            Read.readWorkoutListGUI("testreadGUI", testWorkoutList);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(3, testWorkoutList.getSize());
        assertEquals(2, testWorkout1.getListOfExercise().size());
    }

    // HELPERS

    // EFFECTS: Creates and adds 3 workouts to testWorkoutList as well as 2 exercises to testWorkout1

    private void createWorkoutsAndExercises() {
        testWorkoutList.addWorkout(testWorkout1);
        testWorkoutList.addWorkout(testWorkout2);
        testWorkoutList.addWorkout(testWorkout3);

        testWorkoutList.getWorkout(testWorkout1).addExercise(exercise1);
        testWorkoutList.getWorkout(testWorkout1).addExercise(exercise2);
    }


}
