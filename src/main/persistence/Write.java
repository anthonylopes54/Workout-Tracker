package persistence;

import model.Workout;
import model.WorkoutList;
import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

// This class contains methods that encodes the state of the given WorkoutList in JSON
// and then writes the data to a file.

public class Write {

    private static final String FILE_LOCATION = "./data/";

    public Write() {}

    // EFFECTS: save state of workoutList and associated objects to FILE_LOCATION + nameOfFile

    public static void saveWorkoutList(String nameOfFile, WorkoutList listOfWorkout) throws IOException {
        JSONArray objectToSave = new JSONArray();
        Workout.save(listOfWorkout, objectToSave);
        FileWriter myFile = new FileWriter(FILE_LOCATION + nameOfFile + ".json");
        String encodedJson = objectToSave.toJSONString();
        myFile.write(encodedJson);
        myFile.close();
    }
}

