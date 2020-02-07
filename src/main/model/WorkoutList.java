package model;

import java.util.ArrayList;

public class WorkoutList {
    ArrayList<Workout> listOfWorkouts;

    public WorkoutList() {
        listOfWorkouts = new ArrayList<>();
    }

    // REQUIRES: listOfWorkouts must have >0 elements
    // EFFECTS: returns a list of workouts currently in listOfWorkouts

    public String printListOfWorkouts() {
        String workout = "";
        for (Workout next : listOfWorkouts) {
            if (next.favourite) {
                workout += "Workout: " + next.name + "\n      Description: " + next.description + "\u066D\n";
            } else {
                workout += "Workout: " + next.name + "\n      Description: " + next.description + "\n";
            }
        }
        return workout;
    }

    // EFFECTS: returns the number of elements in WorkoutList

    public int getSize() {
        return listOfWorkouts.size();
    }

    // MODIFIES: this
    // EFFECTS: adds workout to listOfWorkouts

    public void addWorkout(Workout workout) {
        listOfWorkouts.add(workout);
    }

    // MODIFIES: this
    // EFFECTS: removes given workout from listOfWorkout if it's present and returns true,
    //          otherwise return false

    public boolean removeWorkout(Workout workout) {
        if (!listOfWorkouts.contains(workout)) {
            return false;
        } else {
            listOfWorkouts.remove(workout);
            return true;
        }
    }


    // REQUIRES: given name of workout must be in listOfWorkout
    // EFFECTS: returns given workout

    public Workout getWorkout(String workout) {
        for (Workout next : listOfWorkouts) {
            if (next.name == workout) {
                return next;
            }
        }
        return null;
    }

    // EFFECTS: returns true if given workout is in listOfWorkout;
    //          false otherwise

    public boolean contains(Workout workout) {
        return listOfWorkouts.contains(workout);
    }
}
