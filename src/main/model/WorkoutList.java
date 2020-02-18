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
            if (next.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: save state of workoutList and associated objects to ACCOUNTS_FILE

    public void saveWorkoutList(String nameOfFile) {
        JSONArray objectToSave = new JSONArray();
        for (Workout next : listOfWorkout) {
            JSONObject obj = new JSONObject();
            JSONArray listOfExercise;
            obj.put("name", next.getName());
            obj.put("description", next.getDescription());
            obj.put("favourite", next.getFavourite());
            listOfExercise = encodeListOfExercises(next.getListOfExercise());
            obj.put("listOfExercise", listOfExercise);
            objectToSave.add(obj);
        }
        try {
            FileWriter myFile = new FileWriter(ACCOUNT_LOCATION + nameOfFile + ".json");
            String encodedJson = objectToSave.toJSONString();
            myFile.write(encodedJson);
            myFile.close();
            System.out.println("File now contains json object");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readWorkoutList(String fileName) {
        JSONParser jsonParser = new JSONParser();

        try {
            Reader reader = new FileReader(ACCOUNT_LOCATION + fileName + ".json");
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            for (Object obj : jsonArray) {
                JSONObject workout = (JSONObject) obj;


                String name = (String) workout.get("name");
                String description = (String) workout.get("description");
                JSONArray listOfExercise = (JSONArray) workout.get("listOfExercise");
                ArrayList<Exercise> listOfExerciseParsed = parseListOfExercise(listOfExercise);
                Boolean favourite = (Boolean) workout.get("favourite");
                Workout thisWorkout = new Workout(name, description, listOfExerciseParsed, favourite);
                this.listOfWorkout.add(thisWorkout);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Exercise> parseListOfExercise(JSONArray listOfExercise) {
        ArrayList<Exercise> output = new ArrayList<>();

        for (Object obj: listOfExercise) {
            JSONObject exercise = (JSONObject) obj;

            String name = (String) exercise.get("name");
            int sets = ((Long) exercise.get("sets")).intValue();
            int reps = ((Long) exercise.get("reps")).intValue();
            ArrayList<String> listOfNote = (ArrayList<String>) exercise.get("listOfNote");
            Exercise thisExercise = new Exercise(name, sets, reps, listOfNote);
            output.add(thisExercise);
        }
        return output;
    }

    // EFFECTS: encodes the given list of exercises into JSON

    private JSONArray encodeListOfExercises(ArrayList<Exercise> listOfExercise) {
        JSONArray outputOfExercises = new JSONArray();
        for (Exercise next : listOfExercise) {
            JSONObject obj = new JSONObject();
            obj.put("name", next.getName());
            obj.put("sets", next.getSets());
            obj.put("reps", next.getReps());
            obj.put("listOfNote", next.getListOfNote());
            outputOfExercises.add(obj);
        }
        return outputOfExercises;

    }

}

