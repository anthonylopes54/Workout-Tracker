package ui;

import model.Exercise;
import model.Workout;
import model.WorkoutList;
import org.json.simple.parser.ParseException;
import persistence.Read;
import persistence.Write;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// This class contains the methods required to interact with the user. It calls on the model package to carry out
// the functions of the program to satisfy the user's needs.

public class ConsoleInterface {

    private static final String QUIT_COMMAND = "quit";
    public static final String ADD_WORKOUT_COMMAND = "add workout";
    private static final String REMOVE_WORKOUT_COMMAND = "remove workout";
    private static final String ADD_EXERCISE_COMMAND = "add exercise";
    private static final String REMOVE_EXERCISE_COMMAND = "remove exercise";
    private static final String MODIFY_EXERCISE_COMMAND = "modify exercise";
    private static final String BACK_COMMAND = "back";
    private static final String ADD_TO_FAVOURITE = "fav";
    private static final String REMOVE_FROM_FAVOURITE = "remove fav";
    private static final String ADD_NOTE = "note";

    public boolean runProgram;
    private WorkoutList workoutList;
    private Scanner input;

    public ConsoleInterface() {
        System.out.println("Welcome to the Lifestyle Tracker!");
        this.workoutList = new WorkoutList();
        input = new Scanner(System.in);
        runProgram = true;
        askUserToLoad();
        dealUserInput(workoutList);
    }

    private void askUserToLoad() {
        System.out.println("Would you like to load your last list of workouts?\nPlease type 'yes' or 'no'.");
        String command = input.nextLine();
        if (command.equalsIgnoreCase("yes")) {
            System.out.println("What is the name of the file you would like to load?");
            String fileName = input.nextLine();
            try {
                Read.readWorkoutList(fileName, this.workoutList);
            } catch (IOException | ParseException e) {
                System.out.println("Sorry, I couldn't load the file. Can you try typing in the file name again?");
                askUserToLoad();
                return;
            }
        } else if (!command.equalsIgnoreCase("no")) {
            System.out.println("Sorry, I didn't get that");
            askUserToLoad();
            return;
        }
    }

    // EFFECTS: parses user input until user quits

    public void dealUserInput(WorkoutList workoutList) {
        displayInstructionsForWorkoutList(workoutList);
        checkInputForWorkoutList();
    }

    // EFFECTS: helps parse user input and direct user to correct methods required for their intentions

    private void parseStringForWorkoutList(String s) throws InterruptedException {
        if (s.length() > 0) {
            switch (s) {
                case QUIT_COMMAND: quitProgram();
                break;
                case ADD_WORKOUT_COMMAND: addWorkout();
                    break;
                case REMOVE_WORKOUT_COMMAND: removeWorkout();
                    break;
                case BACK_COMMAND: dealUserInput(workoutList);
                    break;
                case ADD_TO_FAVOURITE: addWorkoutToFavourite();
                    break;
                case REMOVE_FROM_FAVOURITE: removeWorkoutFromFavourite();
                    break;
                default: if (checkStringMatchesAWorkout(s)) {
                        return;
                    }
                    System.out.println("Sorry, can you please try typing that in again?");
                    checkInputForWorkoutList();
                    break;
            }
        }
    }

    // EFFECTS: if string matches a workout name, open up the contents of the workout

    private boolean checkStringMatchesAWorkout(String s) throws InterruptedException {
        for (Workout next : workoutList.getListOfWorkout()) {
            if (s.equalsIgnoreCase(next.getName())) {
                openWorkout(next);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: Workout in this
    // EFFECTS: removes workout from favourites if given workout exists as a favourite
    //          else, notify user if there are no workouts in WorkoutList and return

    private void removeWorkoutFromFavourite() {
        areThereAnyExercisesInWorkoutList();
        System.out.println("What is the name of the workout you would like to remove from your favourites");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
        }
        if (workoutList.containsName(name)) {
            for (Workout next : workoutList.getListOfWorkout()) {
                if (next.getName().equalsIgnoreCase(name)) {
                    next.removeFromFavourites();
                    System.out.println(name + "was removed from your favourites!");
                    dealUserInput(workoutList);
                    return;
                }
            }
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            removeWorkoutFromFavourite();
        }
    }

    // MODIFIES: Workout in this
    // EFFECTS: adds a currently existing workout to favourites;
    //          if workout, is already a favourite do nothing;
    //          else, notify user if there are no Workouts in WorkoutList and return

    private void addWorkoutToFavourite() {
        areThereAnyExercisesInWorkoutList();
        System.out.println("What is the name of the workout you would like to make a favourite?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
        }
        if (workoutList.containsName(name)) {
            for (Workout next : workoutList.getListOfWorkout()) {
                if (next.getName().equalsIgnoreCase(name)) {
                    next.addToFavourites();
                    System.out.println(name + " was added to your favourites!");
                    dealUserInput(workoutList);
                    return;
                }
            }
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            addWorkoutToFavourite();
        }
    }

    // EFFECTS: displays exercises in given workout and parses user input to help direct user to
    //          functions they seek

    private void openWorkout(Workout workout) {
        displayInstructionsForWorkout(workout);
        checkInputForWorkout(workout);
    }

    // EFFECTS: takes user input, trims it and sets it to lowercase before parsing input to determine
    //          which function user requires

    private void checkInputForWorkout(Workout workout) {
        String s;

        try {
            while (runProgram) {
                if (input.hasNext()) {
                    s = input.nextLine();
                    s = s.toLowerCase();
                    s = s.trim();
                    parseStringForWorkout(s, workout);
                }
            }
        } catch (Exception e) {
            System.out.println("Please try again!");
            openWorkout(workout);
            return;
        }
    }


    // EFFECTS: helps parse user input and direct user to correct methods required for their intentions

    private void parseStringForWorkout(String s, Workout workout) throws InterruptedException {
        if (s.length() > 0) {
            switch (s) {
                case ADD_EXERCISE_COMMAND: addExercise(workout);
                    break;
                case REMOVE_EXERCISE_COMMAND: removeExercise(workout);
                    break;
                case MODIFY_EXERCISE_COMMAND: modifyExercise(workout);
                    break;
                case QUIT_COMMAND: quitProgram();
                    break;
                case BACK_COMMAND:
                    dealUserInput(workoutList);
                    break;
                case ADD_NOTE:
                    addNote(workout);
                    break;
                default:
                    invalidInputForWorkout(workout);
                    break;
            }
        }
    }

    // EFFECTS: prompts use to try inputting another string to chose what to do with workout

    private void invalidInputForWorkout(Workout workout) throws InterruptedException {
        System.out.println("Sorry, can you please try typing that in again?");
        checkInputForWorkout(workout);
    }

    // EFFECTS: prints a goodbye message before ending program

    private void quitProgram() throws InterruptedException {
        askUserToSave();
        System.out.println("Have a great day! Goodbye!");
        TimeUnit.SECONDS.sleep(3);
        runProgram = false;
    }

    private void askUserToSave() {
        System.out.println("Would you like to save the current state of your workout list?");
        String command = input.nextLine();
        if (command.equalsIgnoreCase("yes")) {
            System.out.println("What would you like to name your file?");
            String nameOfFile = input.nextLine();
            try {
                Write.saveWorkoutList(nameOfFile, this.workoutList);
            } catch (IOException e) {
                System.out.println("Sorry, I could not save the file. "
                        + "Maybe try using a different file name next time!");
                askUserToSave();
            }
        } else if (!command.equalsIgnoreCase("no")) {
            System.out.println("Sorry, I don't understand...");
            askUserToSave();
        }
    }

    // MODIFIES: Exercise within Workout within this
    // EFFECTS: If workout does not have any exercises, notify user and return to exercise instructions;
    //          else, ask user which exercise they would like to add a note to and add the input user proides
    //          for note to listOfNote

    private void addNote(Workout workout) throws InterruptedException {
        areThereAnyExercisesInWorkout(workout);
        System.out.println("What is the name of the exercise you would like to add a note to?");
        String name = input.nextLine();
        if (ifBackThenOpenWorkout(workout, name)) {
            return;
        }
        if (workout.containsName(name)) {
            System.out.println("Please type the note you would like to add");
            String note = input.nextLine();
            if (ifBackThenOpenWorkout(workout, name)) {
                return;
            }
            for (Exercise next : workout.getListOfExercise()) {
                if (next.getName().equalsIgnoreCase(name)) {
                    next.addNote(note);
                    openWorkout(workout);
                }
            }
        } else {
            System.out.println("Sorry, I could not find that exercise. Can you please try again...");
            addNote(workout);
        }
    }

    // EFFECTS: if string.equals BACK_COMMAND, open workout and return true

    private boolean ifBackThenOpenWorkout(Workout workout, String name) throws InterruptedException {
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return true;
        }
        return false;
    }

    // MODIFIES: Given Workout in this
    // EFFECTS: If workout does not have any exercises, notify user and return to exercise instructions;
    //          Else, modify fields of exercise

    private void modifyExercise(Workout workout) throws InterruptedException {
        areThereAnyExercisesInWorkout(workout);
        System.out.println("What is the name of the exercise you would like to modify?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return;
        }
        if (workout.containsName(name)) {
            Exercise newExercise = buildExerciseWithUser(workout);
            for (Exercise next : workout.getListOfExercise()) {
                if (next.getName().equalsIgnoreCase(name)) {
                    int indexToPlace = workout.getListOfExercise().indexOf(next);
                    workout.getListOfExercise().remove(next);
                    workout.getListOfExercise().add(indexToPlace, newExercise);
                }
            }
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            modifyExercise(workout);
        }
    }

    // EFFECTS: if workout does not have any exercises in it, it will take user back to overview of given
    //          workout

    private void areThereAnyExercisesInWorkout(Workout workout) throws InterruptedException {
        if (workout.getListOfExercise().size() <= 0) {
            System.out.println("This workout does not have any exercises in it!\n");
            openWorkout(workout);
        }
    }

    private void areThereAnyExercisesInWorkoutList() {
        if (workoutList.getListOfWorkout().size() <= 0) {
            System.out.println("There are no workouts made!");
            dealUserInput(workoutList);
        }
    }

    // MODIFIES: Workout within this
    // EFFECTS: if there are exercises within given workout, remove exercise given by user
    //          if no exercise is within given workout, take user back to overview of given workout

    private void removeExercise(Workout workout) throws InterruptedException {
        areThereAnyExercisesInWorkout(workout);
        System.out.println("What  is the name of the exercise you would like to remove?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return;
        }
        if (workout.containsName(name)) {
            for (Exercise next : workout.getListOfExercise()) {
                if (next.getName().equalsIgnoreCase(name)) {
                    workout.removeExercise(next);
                    System.out.println("Successfully removed");
                    openWorkout(workout);
                    return;
                }
            }

        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            removeExercise(workout);
        }
    }

    // MODIFIES: Workout within this
    // EFFECTS: Create and add exercise to listOfExercise within given workout

    private void addExercise(Workout workout) throws InterruptedException {
        Exercise newExercise = buildExerciseWithUser(workout);
        workout.addExercise(newExercise);
        System.out.println("Successfully added exercise");
        openWorkout(workout);
    }
    // MODIFIES: Exercise within workout
    // EFFECTS: prompts user to provide name, sets and reps of a new exercise and then instantiates new exercise

    private Exercise buildExerciseWithUser(Workout workout) throws InterruptedException {
        System.out.println("What is the name of the exercise you would like to add?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return null;
        }
        System.out.println("How many sets?");
        String sets = input.nextLine();
        if (sets.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return null;
        }
        sets = checkUserInputNumber(sets);
        sets = checkLessThanZero(sets);
        System.out.println("How many reps?");
        String reps = input.nextLine();
        if (reps.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return null;
        }
        reps = checkUserInputNumber(reps);
        reps = checkLessThanZero(reps);
        return new Exercise(name, Integer.parseInt(sets), Integer.parseInt(reps));
    }

    // EFFECTS: if numeric string is less than zero, set string to "0"

    private String checkLessThanZero(String sets) {
        if (Integer.parseInt(sets) < 0) {
            sets = "0";
        }
        return sets;
    }

    // EFFECTS: prompts user to enter new input repeatedly if string is not numeric

    public String checkUserInputNumber(String str) {
        if (!isNumeric(str)) {
            while (!isNumeric(str)) {
                System.out.println("Please try again");
                str = input.nextLine();
            }
        }
        return str;
    }


    // EFFECTS: returns true if given string is numeric; otherwise false

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // EFFECTS: Displays list of exercises in given workout with their respective reps and sets

    private void displayInstructionsForWorkout(Workout workout) {
        System.out.println("Your current list of exercises are as follows:\n");
        workout.printWorkout();
        System.out.println("Type '" + ADD_EXERCISE_COMMAND + ",' '" + REMOVE_EXERCISE_COMMAND
                + ",' '" + QUIT_COMMAND + ",' '" + ADD_NOTE
                + "' to add a note to an exercise, or the name of the exercise you would like to modify!");
    }

    // MODIFIES: this
    // EFFECTS: asks user for the workout they would like to remove if listOfWorkouts is not empty
    //          and then removes workout; If no workout is present, will only notify the user

    private void removeWorkout() {
        if (workoutList.getSize() <= 0) {
            System.out.println("There are no workouts to remove");
            dealUserInput(workoutList);
        }
        System.out.println("What is the name of the workout you would like to remove?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
            return;
        }
        if (workoutList.containsName(name)) {
            workoutList.removeWorkout(name);
            System.out.println(name + " was removed!");
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
        }
        dealUserInput(workoutList);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to provide workout details, instantiates a new workout with the given details,
    //          and adds it to the listOfWorkout

    private void addWorkout() {
        System.out.println("What would you like to name your new workout?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
            return;
        }
        System.out.println("How would you describe this workout?");
        String description = input.nextLine();
        if (description.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
            return;
        }
        Workout workout = new Workout(name, description, false);
        workoutList.addWorkout(workout);
        System.out.println("The new workout was created!");
        dealUserInput(workoutList);
    }


    // EFFECTS: Prints instructions for using the navigating and using the list of workouts

    private static void displayInstructionsForWorkoutList(WorkoutList workoutList) {
        System.out.println("Your current list of workouts are as follows:");
        workoutList.printListOfWorkouts();
        System.out.println("Type '" + ADD_WORKOUT_COMMAND + "' '" + REMOVE_WORKOUT_COMMAND
                + "' '" + QUIT_COMMAND + "' or the name of the workout you would like to view!");
        System.out.println("You can also type '" + ADD_TO_FAVOURITE + "' or '" + REMOVE_FROM_FAVOURITE + "' to add or"
                + " remove workouts to or from your favourites.");
        System.out.println("If you would ever like to go back, type '" + BACK_COMMAND + "'");
    }

    // EFFECTS: Trims and makes user input all lowercase before parsing input

    private void checkInputForWorkoutList() {
        String s;

        while (runProgram) {
            try {
                if (input.hasNext()) {
                    s = input.nextLine();
                    s = s.toLowerCase();
                    s = s.trim();
                    parseStringForWorkoutList(s);
                }
            } catch (Exception e) {
                System.out.println("error thrown :" + e);
                System.out.println("Please try again!");
                dealUserInput(workoutList);
                return;
            }
        }
    }
}
