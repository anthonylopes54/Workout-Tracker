package persistance;

import model.Exercise;
import model.Workout;
import model.WorkoutList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Read;
import persistence.Write;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriteTest {
    WorkoutList testWorkoutList;
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;
    private Exercise exercise4;
    private Workout testWorkout1;
    private Workout testWorkout2;
    private Workout testWorkout3;


    @BeforeEach
    public void runBefore() {
        exercise1 = new Exercise("Lat Pulldown", 3, 10);
        exercise2 = new Exercise("Dumbbell Row", 4, 12);
        exercise3 = new Exercise("Face Pulls", 3, 12);
        exercise4 = new Exercise("Pull Ups", 3, 8);
        testWorkout1 = new Workout("Back Blast", "Hypertrophy-based", false);
        testWorkout2 = new Workout("Chest Blast", "Strength-Based", true);
        testWorkout3 = new Workout("HIIT", "Non-Traditional Cardio", false);
        testWorkoutList = new WorkoutList();
    }

    @Test
    public void testConstructor() {
        Write test = new Write();
        assertTrue(test instanceof Write);
    }

    @Test
    public void testSaveWorkoutListNoExceptionThrown() {
        testWorkoutList.addWorkout(testWorkout1);
        testWorkoutList.addWorkout(testWorkout2);
        testWorkoutList.addWorkout(testWorkout3);

        testWorkoutList.getWorkout(testWorkout1).addExercise(exercise1);
        testWorkoutList.getWorkout(testWorkout1).addExercise(exercise2);
        testWorkoutList.getWorkout(testWorkout1).checkExercise(exercise1).addNote("testing");
        testWorkoutList.getWorkout(testWorkout1).checkExercise(exercise1).addNote("testing2");


        testWorkoutList.getWorkout(testWorkout2).addExercise(exercise1);
        testWorkoutList.getWorkout(testWorkout2).addExercise(exercise2);

        testWorkoutList.getWorkout(testWorkout3).addExercise(exercise1);
        testWorkoutList.getWorkout(testWorkout3).addExercise(exercise2);

        try {
            Write.saveWorkoutList("testwrite", testWorkoutList);
        } catch (IOException e) {
            fail();
        }

        testWorkoutList.removeWorkout(testWorkout1);
        testWorkoutList.removeWorkout(testWorkout2);
        testWorkoutList.removeWorkout(testWorkout3);

        assertEquals(0, testWorkoutList.getSize());
        try {
            Read.readWorkoutList("testwrite", testWorkoutList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        assertEquals(3, testWorkoutList.getSize());
        assertTrue(testWorkoutList.getWorkout("HIIT").checkExercise("Lat Pulldown"));
        assertTrue(testWorkoutList.getWorkout("HIIT").checkExercise("Dumbbell Row"));

        assertTrue(testWorkoutList.getWorkout("Chest Blast").checkExercise("Lat Pulldown"));
        assertTrue(testWorkoutList.getWorkout("Chest Blast").checkExercise("Dumbbell Row"));

        assertTrue(testWorkoutList.getWorkout("Back Blast").checkExercise("Lat Pulldown"));
        assertTrue(testWorkoutList.getWorkout("Back Blast").checkExercise("Dumbbell Row"));
    }
    @Test
    public void testSaveWorkoutListExceptionThrown() {
        testWorkoutList.addWorkout(testWorkout1);
        testWorkoutList.addWorkout(testWorkout2);
        testWorkoutList.addWorkout(testWorkout3);
        try {
            Write.saveWorkoutList("./path/does/not/exist/testt", testWorkoutList);
            fail("expected an exception to be thrown");
        } catch (IOException e) {
        }
    }

}

