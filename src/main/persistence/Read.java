package persistence;

import model.Exercise;
import model.Workout;
import model.WorkoutList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

// This class contains methods that reads and parses JSON files. It then turns the parsed data into objects to reinstate
// the context of the program when the file was saved.

public class Read {

    private static final String ACCOUNT_LOCATION = "./data/";

    // EFFECTS: instantiates a list of workouts parsed from a JSON file

    public static void readWorkoutList(String fileName, WorkoutList workoutList) {
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
                workoutList.getListOfWorkout().add(thisWorkout);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // EFFECTS: parses the given JSONArray int an ArrayList of exercises

    private static ArrayList<Exercise> parseListOfExercise(JSONArray listOfExercise) {
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
}
