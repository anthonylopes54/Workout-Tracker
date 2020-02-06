package model;

import java.util.ArrayList;

public class WorkoutList {
    ArrayList<Workout> listOfWorkouts;

    public WorkoutList(){

    }

    // EFFECTS: returns a list of workouts currently in listOfWorkouts

    public String getListOfWorkouts() {
        return "";
    }

    // EFFECTS: returns the number of elements in WorkoutList

    public int getSize() {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: adds workout to listOfWorkouts

    public void addWorkout(Workout workout) {
    }

    // MODIFIES: this
    // EFFECTS: removes given workout from listOfWorkout if it's present and returns true,
    //          otherwise return false

    public boolean removeWorkout(Workout workout) {
        return false;
    }

    public Workout getWorkout(Workout workout) {
        return null;
    }
}
