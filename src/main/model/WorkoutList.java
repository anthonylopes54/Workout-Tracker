package model;


import java.util.ArrayList;

public class WorkoutList {
    private ArrayList<Workout> listOfWorkout;

    public WorkoutList() {
        listOfWorkout = new ArrayList<>();
    }

    // REQUIRES: listOfWorkouts must have >0 elements
    // EFFECTS: returns and prints a list of workouts currently in listOfWorkouts
    // TODO: fix checkstyle
    public String printListOfWorkouts() {
        String workout = "";
        for (Workout next : listOfWorkout) {
            if (next.getFavourite()) {
                workout += "Workout: " + next.getName() + "\u066D" + "\n         Description: "
                        + next.getDescription() + "\n";
            }
        }
        for (Workout next : listOfWorkout) {
            if (!next.getFavourite()) {
                workout += "Workout: " + next.getName() + "\n         Description: " + next.getDescription() + "\n";
            }
        }
        System.out.println(workout);
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

    public boolean removeWorkout(String workout) {
        for (Workout next : listOfWorkout) {
            if (next.getName().equals(workout)) {
                listOfWorkout.remove(next);
                return true;
            }
        }
        return false;
    }


    // REQUIRES: given name of workout must be in listOfWorkout
    // EFFECTS: returns given workout
    // TODO: fix code coverage
    public Workout getWorkout(String workout) {
        Workout returnValue = null;
        for (Workout next : listOfWorkout) {
            if (next.getName() == workout) {
                returnValue = next;
                break;
            }
        }
        return returnValue;
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

    public boolean containsName(String name) {
        for (Workout next : listOfWorkout) {
            if (next.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
