package model;

import java.util.ArrayList;

public class WorkoutList {
    private ArrayList<Workout> listOfWorkout;

    public WorkoutList() {
        listOfWorkout = new ArrayList<>();
    }

    // REQUIRES: listOfWorkouts must have >0 elements
    // EFFECTS: returns a list of workouts currently in listOfWorkouts

    public String printListOfWorkouts() {
        String workout = "";
        for (Workout next : listOfWorkout) {
            if (next.getFavourite()) {
                workout += "Workout: " + next.getName() + "\n      Description: " + next.getDescription() + "\u066D\n";
            } else {
                workout += "Workout: " + next.getName() + "\n      Description: " + next.getDescription() + "\n";
            }
        }
        return workout;
    }

    // EFFECTS: returns the number of elements in WorkoutList

    public int getSize() {
        return listOfWorkout.size();
    }

    // MODIFIES: this
    // EFFECTS: adds workout to listOfWorkouts

    public void addWorkout(Workout workout) {
        listOfWorkout.add(workout);
    }

    // MODIFIES: this
    // EFFECTS: removes given workout from listOfWorkout if it's present and returns true,
    //          otherwise return false

    public boolean removeWorkout(Workout workout) {
        if (!listOfWorkout.contains(workout)) {
            return false;
        } else {
            listOfWorkout.remove(workout);
            return true;
        }
    }


    // REQUIRES: given name of workout must be in listOfWorkout
    // EFFECTS: returns given workout

    public Workout getWorkout(String workout) {
        for (Workout next : listOfWorkout) {
            if (next.getName() == workout) {
                return next;
            }
        }
        return null;
    }

    // EFFECTS: returns true if given workout is in listOfWorkout;
    //          false otherwise

    public boolean contains(Workout workout) {
        return listOfWorkout.contains(workout);
    }

    // EFFECTS: returns listOfWorkout

    public ArrayList<Workout> getListOfWorkout() {
        return listOfWorkout;
    }
}
