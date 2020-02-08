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
    private static final String BACK_COMMAND = "back";
    private static final String ADD_TO_FAVOURITE = "fav";
    private static final String REMOVE_FROM_FAVOURITE = "remove fav";

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

    // EFFECTS: helps parse user input and direct user to correct methods required for their intentions

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
                case BACK_COMMAND:
                    dealUserInput(workoutList);
                    break;
                case ADD_TO_FAVOURITE:
                    addWorkoutToFavourite();
                    break;
                case REMOVE_FROM_FAVOURITE:
                    removeWorkoutFromFavourite();
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

    private void removeWorkoutFromFavourite() throws InterruptedException {
        System.out.println("What is the name of the workout you would like to remove from your favourites");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
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

    private void addWorkoutToFavourite() throws InterruptedException {
        System.out.println("What is the name of the workout you would like to make a favourite?");
        String name = input.nextLine();
        if (name.equalsIgnoreCase(BACK_COMMAND)) {
            dealUserInput(workoutList);
        }
        if (workoutList.containsName(name)) {
            for (Workout next : workoutList.getListOfWorkout()) {
                if (next.getName().equalsIgnoreCase(name)) {
                    next.addToFavourites();
                    System.out.println(name + "was added to your favourites!");
                    dealUserInput(workoutList);
                    return;
                }
            }
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            addWorkoutToFavourite();
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
                s = s.toLowerCase();
                s = s.trim();
                parseStringForWorkout(s, workout);
            }
        }
    }

    // EFFECTS: helps parse user input and direct user to correct methods required for their intentions

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
                case BACK_COMMAND:
                    dealUserInput(workoutList);
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

    private void areThereAnyExercisesInWorkout(Workout workout) throws InterruptedException {
        if (workout.getListOfExercise().size() <= 0) {
            System.out.println("This workout does not have any exercises in it");
            openWorkout(workout);
        }
    }

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

    private void addExercise(Workout workout) throws InterruptedException {
        Exercise newExercise = buildExerciseWithUser(workout);
        workout.addExercise(newExercise);
        System.out.println("Successfully added exercise");
        openWorkout(workout);
    }

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
        System.out.println("How many reps?");
        String reps = input.nextLine();
        if (reps.equalsIgnoreCase(BACK_COMMAND)) {
            openWorkout(workout);
            return null;
        }
        return new Exercise(name, Integer.parseInt(sets), Integer.parseInt(reps));
    }

    private void displayInstructionsForWorkout(Workout workout) {
        System.out.println("Your current list of exercises are as follows:");
        workout.printWorkout();
        System.out.println("Type '" + ADD_EXERCISE_COMMAND + "' '" + REMOVE_EXERCISE_COMMAND
                + "' '" + QUIT_COMMAND + "' or the name of the exercise you would like to modify!");
        System.out.println("If you would ever like to go back, type '" + BACK_COMMAND + "'");
    }

    private void removeWorkout() throws InterruptedException {
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
            dealUserInput(workoutList);
        } else {
            System.out.println("Sorry, I did not get that. Can you please try again...");
            dealUserInput(workoutList);
        }
    }


    private void addWorkout() throws InterruptedException {
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


    // EFFECTS: Prints instructions for using the interface

    private static void displayInstructionsForWorkoutList(WorkoutList workoutList) {
        System.out.println("Your current list of workouts are as follows:");
        workoutList.printListOfWorkouts();
        System.out.println("Type '" + ADD_WORKOUT_COMMAND + "' '" + REMOVE_WORKOUT_COMMAND
                + "' '" + QUIT_COMMAND + "' or the name of the workout you would like to view!");
        System.out.println("You can also type '" + ADD_TO_FAVOURITE + "' or '" + REMOVE_FROM_FAVOURITE + "' to add or"
                + " remove workouts to or from your favourites.");
    }

    private void checkInputForWorkoutList() throws InterruptedException {
        String s;

        while (runProgram) {
            if (input.hasNext()) {
                s = input.nextLine();
                s = s.toLowerCase();
                s = s.trim();
                parseStringForWorkoutList(s);
            }
        }
    }
}
