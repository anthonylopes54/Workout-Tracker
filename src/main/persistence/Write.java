package persistence;

import model.Exercise;
import model.Workout;
import model.WorkoutList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// This class contains methods that encodes the state of the given WorkoutList in JSON
// and then writes the data to a file.

public class Write {

    private static final String ACCOUNT_LOCATION = "./data/";

    // EFFECTS: save state of workoutList and associated objects to ACCOUNTS_FILE

    public static void saveWorkoutList(String nameOfFile, WorkoutList listOfWorkout) {
        JSONArray objectToSave = new JSONArray();
        for (Workout next : listOfWorkout.getListOfWorkout()) {
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

    // EFFECTS: encodes the given list of exercises into JSON

    public static JSONArray encodeListOfExercises(ArrayList<Exercise> listOfExercise) {
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

