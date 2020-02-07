package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.ArrayList;

public class Workout {
    String name;
    String description;
    ArrayList<Exercise> listOfExercise;
    boolean favourite;

    public Workout(String name, String description, boolean fav) {
        this.name = name;
        this.description = description;
        listOfExercise = new ArrayList<>();
        favourite = fav;
    }

    // EFFECTS: returns the name of the workout

    public String getName() {
        return name;
    }

    // EFFECTS: returns the description of the workout

    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: adds given exercise to listOfExercise

    public void addExercise(Exercise exercise) {
        listOfExercise.add(exercise);
    }

    // EFFECTS: returns a string of exercises within the workout with each exercises associated
    //          sets and reps. If list is empty, return "There are no exercises in this workout!"

    public String printWorkout() {
        if (listOfExercise.size() == 0) {
            return "There are no exercises in this workout!";
        } else {
            String workout = "";
            for (Exercise next : listOfExercise) {
                workout += "Exercise: " + next.name + " Sets: " + next.sets + " Reps: " + next.reps + "\n";
            }
            return workout;
        }
    }

    // MODIFIES: this
    // EFFECTS: remove given workout from listOfWorkout and returns true;
    //          otherwise, return false

    public boolean removeExercise(Exercise exercise) {
        if (!listOfExercise.contains(exercise)) {
            return false;
        } else {
            listOfExercise.remove(exercise);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes favourite field to true if it is currently false; otherwise, remains true

    public void addToFavourites() {
        favourite = true;
    }

    // MODIFIES: this
    // EFFECTS: changes favourite field to false if it is currently false; otherwise, remains false

    public void removeFromFavourites() {
        favourite = false;
    }

//    // REQUIRES: exercise must already be in listOfExercise
//    // EFFECTS: returns the given exercise
//
//    public Exercise getExercise(Exercise exercise) {
//        return null;
//    }
}
