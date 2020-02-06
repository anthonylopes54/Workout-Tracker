package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutListTest {

    WorkoutList testWorkoutList;

    @BeforeEach

    public void runBefore() {
        testWorkoutList = new WorkoutList();
    }

    @Test

    public void testConstructor(){
        assertEquals(0, testWorkoutList.getSize());
    }

    @Test

    public void testGetListOfWorkouts() {

    }

    @Test

    public void testAddWorkout() {

    }

    @Test

    public void testRemoveWorkout() {

    }

    @Test

    public void testGetWorkout() {

    }

}
