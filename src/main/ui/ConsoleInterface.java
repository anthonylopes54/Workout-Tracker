package ui;

import model.Exercise;
import model.Workout;
import model.WorkoutList;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleInterface {

    private static final String QUIT_COMMAND = "quit";
    public static final String ADD_WORKOUT_COMMAND = "add workout";
    private static final String REMOVE_WORKOUT_COMMAND = "remove workout";
    private static final String ADD_EXERCISE_COMMAND = "add exercise";
    private static final String REMOVE_EXERCISE_COMMAND = "remove exercise";
    private static final String MODIFY_EXERCISE_COMMAND = "modify exercise";

    public boolean runProgram;
    private WorkoutList workoutList;
    private Scanner input;

    public ConsoleInterface(WorkoutList workoutList) {
        this.workoutList = workoutList;
        input = new Scanner(System.in);
        runProgram = true;
    }

    // EFFECTS: parses user input until user quits

    public void dealUserInput(WorkoutList workoutList) throws InterruptedException {
        displayInstructionsForWorkoutList(workoutList);
        checkInputForWorkoutList();
    }


    private void parseStringForWorkoutList(String s) throws InterruptedException {
        if (s.length() > 0) {
            switch (s) {
                case QUIT_COMMAND:
                    System.out.println("Have a great day! Goodbye!");
                    TimeUnit.SECONDS.sleep(3);
                    runProgram = false;
                    break;
                case ADD_WORKOUT_COMMAND:
                    addWorkout();
                    break;
                case REMOVE_WORKOUT_COMMAND:
                    removeWorkout();
                    break;
                default:
                    for (Workout next : workoutList.getListOfWorkout()) {
                        if (s.equalsIgnoreCase(next.getName())) {
                            openWorkout(next);
                            return;
                        }
                    }
                    System.out.println("Sorry, can you please try typing that in again?");
                    checkInputForWorkoutList();
                    break;
            }
        }
    }

    private void openWorkout(Workout workout) throws InterruptedException {
        displayInstructionsForWorkout(workout);
        checkInputForWorkout(workout);
    }

    private void checkInputForWorkout(Workout workout) throws InterruptedException {
        String s;

        while (runProgram) {
            if (input.hasNext()) {
                s = input.nextLine();
                parseStringForWorkout(s, workout);
            }
        }
    }

    private void parseStringForWorkout(String s, Workout workout) throws InterruptedException {
        if (s.length() > 0) {
            switch (s) {
                case ADD_EXERCISE_COMMAND:
                    addExercise(workout);
                    break;
                case REMOVE_EXERCISE_COMMAND:
                    removeExercise(workout);
                    break;
                case MODIFY_EXERCISE_COMMAND:
                    modifyExercise(workout);
                    break;
                case QUIT_COMMAND:
                    System.out.println("Have a great day! Goodbye!");
                    TimeUnit.SECONDS.sleep(3);
                    runProgram = false;
                    break;
                default:
                    System.out.println("Sorry, can you please try typing that in again?");
                    checkInputForWorkout(workout);
                    break;
            }

        }
    }


    private void modifyExercise(Workout workout) throws InterruptedException {
        areThereAnyExercisesInWorkout(workout);
        System.out.println("What is the name of the exercise you would like to modify?");
        String name = input.nextLine();
        if (workout.containsName(name)) {
            Exercise newExercise = buildExerciseWithUser();
            for (Exercise next : workout.getListOfExercise()) {
                if (next.getName().equals(name)) {
                    int indexToPlace = workout.getListOfExercise().indexOf(next);
                    workout.getListOfExercise().remove(next);
                    workout.getListOfExercise().add(indexToPlace, newExercise);
                }
            }
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            modifyExercise(workout);
            return;
        }

    }

    private void areThereAnyExercisesInWorkout(Workout workout) throws InterruptedException {
        if (workout.getListOfExercise().size() <= 0) {
            System.out.println("This workout does not have any exercises in it");
            openWorkout(workout);
            return;
        }
    }

    private void removeExercise(Workout workout) throws InterruptedException {
        areThereAnyExercisesInWorkout(workout);
        System.out.println("What  is the name of the exercise you would like to remove?");
        String name = input.nextLine();
        if (workout.containsName(name)) {
            for (Exercise next : workout.getListOfExercise()) {
                if (next.getName().equals(name)) {
                    workout.removeExercise(next);
                    System.out.println("Successfully removed");
                    openWorkout(workout);
                    return;
                }
            }

        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            removeExercise(workout);
            return;
        }
    }

    private void addExercise(Workout workout) throws InterruptedException {
        Exercise newExercise = buildExerciseWithUser();
        workout.addExercise(newExercise);
        System.out.println("Successfully added exercise");
        openWorkout(workout);
    }

    private Exercise buildExerciseWithUser() {
        System.out.println("What is the name of the exercise you would like to add?");
        String name = input.nextLine();
        System.out.println("How many sets?");
        String sets = input.nextLine();
        System.out.println("How many reps?");
        String reps = input.nextLine();
        return new Exercise(name, Integer.parseInt(sets), Integer.parseInt(reps));
    }

    private void displayInstructionsForWorkout(Workout workout) {
        System.out.println("Your current list of exercises are as follows:");
        workout.printWorkout();
        System.out.println("Type '" + ADD_EXERCISE_COMMAND + "' '" + REMOVE_EXERCISE_COMMAND
                + "' '" + QUIT_COMMAND + "' or the name of the exercise you would like to modify!");
    }

    private void removeWorkout() throws InterruptedException {
        if (workoutList.getSize() <= 0) {
            System.out.println("There are no workouts to remove");
            dealUserInput(workoutList);
        }
        System.out.println("What is the name of the workout you would like to remove?");
        String name = input.nextLine();
        if (workoutList.containsName(name)) {
            workoutList.removeWorkout(name);
            System.out.println(name + " was removed!");
            dealUserInput(workoutList);
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            dealUserInput(workoutList);
        }
    }

    private void addWorkout() throws InterruptedException {
        System.out.println("What would you like to name your new workout?");
        String name = input.nextLine();
        System.out.println("How would you describe this workout?");
        String description = input.nextLine();
        Workout workout = new Workout(name, description, false);
        workoutList.addWorkout(workout);
        System.out.println("The new workout was created!");
        dealUserInput(workoutList);
    }

    private static String makeLowerCase(String str) {
        str = str.toLowerCase();
        str = str.trim();
        return str;
    }

    // EFFECTS: Prints instructions for using the interface

    private static void displayInstructionsForWorkoutList(WorkoutList workoutList) {
        System.out.println("Your current list of workouts are as follows:");
        workoutList.printListOfWorkouts();
        System.out.println("Type '" + ADD_WORKOUT_COMMAND + "' '" + REMOVE_WORKOUT_COMMAND
                + "' '" + QUIT_COMMAND + "' or the name of the workout you would like to view!");
    }

    private void checkInputForWorkoutList() throws InterruptedException {
        String s;

        while (runProgram) {
            if (input.hasNext()) {
                s = input.nextLine();
                parseStringForWorkoutList(s);
            }
        }
    }
}
