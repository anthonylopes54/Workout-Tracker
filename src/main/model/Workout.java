package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.ArrayList;

public class Workout {
    String name;
    String description;
    ArrayList<Exercise> listOfExercise;

    public Workout() {

    }

    // EFFECTS: returns the name of the workout

    public String getName() {
        return "";
    }

    // EFFECTS: returns the description of the workout

    public String getDescription() {
        return "";
    }

    // MODIFIES: this
    // EFFECTS: adds given exercise to listOfExercise and returns true if added successfully;
    //          false otherwise

    public boolean addExercise(Exercise exercise) {
        return false;
    }

    // EFFECTS: returns a string of exercises within the workout with each exercises associated
    //          sets and reps. If list is empty, return "Workout is empty!"

    public String printWorkout() {
        return "";
    }

    // MODIFIES: this
    // EFFECTS: remove given workout from listOfWorkout

    public boolean removeExercise(Exercise exercise) {
        return false;
    }
}
