package model;


import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

// This class holds the list of workouts a user can make. It can also hold preset workouts

public class WorkoutList {
    private static final String ACCOUNT_LOCATION = "./data/";
    private ArrayList<Workout> listOfWorkout;

    public WorkoutList() {
        listOfWorkout = new ArrayList<>();
    }

    // REQUIRES: listOfWorkouts must have >0 elements
    // EFFECTS: returns and prints a list of workouts currently in listOfWorkouts

    public String printListOfWorkouts() {
        String workout = "";
        for (Workout next : listOfWorkout) {
            if (next.getFavourite()) {
                workout += "Workout: " + next.getName() + "*" + "\n         Description: "
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

    // MODIFIES: this
    // EFFECTS: removes given workout from listOfWorkout if it is present; otherwise, do nothing

    public void removeWorkout(Workout workout) {
        Workout workoutToRemove = null;
        for (Workout next : listOfWorkout) {
            if (next == workout) {
                workoutToRemove = next;
            }
        }
        listOfWorkout.remove(workoutToRemove);
    }


    // REQUIRES: given name of workout must be in listOfWorkout
    // EFFECTS: returns given workout
    public Workout getWorkout(String workout) {
        Workout returnValue = null;
        for (Workout next : listOfWorkout) {
            if (next.getName().equals(workout)) {
                returnValue = next;
                break;
            }
        }
        return returnValue;
    }

    // EFFECTS: returns given workout if it is in listOfWorkout; else return null

    public Workout getWorkout(Workout workout) {
        Workout returnValue = null;
        for (Workout next : listOfWorkout) {
            if (next == workout) {
                return workout;
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

    // EFFECTS: returns true if given string is a name of a workout within listOfWorkout; otherwise, return false

    public boolean containsName(String name) {
        for (Workout next : listOfWorkout) {
            if (next.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


}

