package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {
    UserAccount userAccountTest;

    @BeforeEach
    public void runBefore() {
        userAccountTest = new UserAccount("Anthony", "solid");
    }
    // Need to test pre-made workouts once that part of the project is complete
    @Test
    public void testConstructor() {
        WorkoutList testWorkoutList = new WorkoutList();
        assertTrue(userAccountTest.getSize() == testWorkoutList.getSize());
        assertEquals("Anthony", userAccountTest.getUsername());
        assertEquals("solid", userAccountTest.getPassword());
    }

    @Test
    public void testDoesPasswordMatchDoesNotMatch() {
        String testPassword = "SOLid";
        assertFalse(userAccountTest.doesPasswordMatch(testPassword));
    }

    @Test
    public void testDoesPasswordMatchDoesMatch() {
        String testPassword = "solid";
        assertTrue(userAccountTest.doesPasswordMatch(testPassword));
    }

}
