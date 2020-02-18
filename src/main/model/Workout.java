package model;


import java.util.ArrayList;
// This class represents each workout a user can make and/or access. It is comprised of a list of exercises that
// comprise the workout as well as the name, description and status of the workout (i.e., is it a favourite workout or
// not).

public class Workout {
    private String name;
    private String description;
    private ArrayList<Exercise> listOfExercise;
    private boolean favourite;

    public Workout(String name, String description, boolean fav) {
        this.name = name;
        this.description = description;
        listOfExercise = new ArrayList<>();
        favourite = fav;
    }

    public Workout(String name, String description, ArrayList<Exercise> listOfExercise, boolean fav) {
        this.name = name;
        this.description = description;
        this.listOfExercise = listOfExercise;
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
                workout += "Exercise: " + next.getName()
                        + " Sets: " + next.getSets()
                        + " Reps: " + next.getReps() + "\n"
                        + next.getNotes()
                        + "\n";
            }
            System.out.println(workout);
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

    // EFFECTS: return value of this.favourite

    public boolean getFavourite() {
        return favourite;
    }

    // EFFECTS: return listOfExercise

    public ArrayList<Exercise> getListOfExercise() {
        return listOfExercise;
    }

    // EFFECTS: produces true if given string is in lisOfExercise

    public boolean containsName(String name) {
        for (Exercise next : listOfExercise) {
            if (next.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

//    // REQUIRES: exercise must already be in listOfExercise
//    // EFFECTS: returns the given exercise
//
//    public Exercise getExercise(Exercise exercise) {
//        return null;
//    }
}
