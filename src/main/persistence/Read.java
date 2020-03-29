package persistence;

import model.Workout;
import model.WorkoutList;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

// This class contains methods that reads and parses JSON files. It then turns the parsed data into objects to reinstate
// the context of the program when the file was saved.

public class Read {

    private static final String FILE_LOCATION = "./data/";

    public Read(){}

    // MODIFIES: WorkoutList, Workout, Exercise
    // EFFECTS: instantiates a list of workouts parsed from a JSON file

    public static void readWorkoutList(String fileName, WorkoutList workoutList) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Reader reader = new FileReader(FILE_LOCATION + fileName + ".json");
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
        Workout.read(jsonArray, workoutList);
    }


    // MODIFIES: WorkoutList, Workout, Exercise
    // EFFECTS: removes workouts from workoutList and instantiates a list of workouts parsed from a JSON file

    public static void readWorkoutListGUI(String fileName, WorkoutList workoutList) throws IOException, ParseException {
        workoutList.removeAll();
        readWorkoutList(fileName, workoutList);
    }

}
