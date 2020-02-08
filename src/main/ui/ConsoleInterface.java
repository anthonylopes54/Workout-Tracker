package ui;

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

    public boolean runProgram;
    private WorkoutList workoutList;
    private Scanner input;

    public ConsoleInterface(WorkoutList workoutList) {
        this.workoutList = workoutList;
        input = new Scanner(System.in);
        runProgram = true;


    }

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
                        if (s.equals(next.getName())) {
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

    private void openWorkout(Workout workout) {
        System.out.println("Your current list of exercises are as follows:");
        workout.printWorkout();
        System.out.println("Type '" + ADD_EXERCISE_COMMAND + "' '" + REMOVE_WORKOUT_COMMAND
                + "' '" + QUIT_COMMAND + "' or the name of the exercise you would like to change!");

    }

    private void removeWorkout() throws InterruptedException {
        System.out.println("What is the name of the workout you would like to remove?");
        String name = input.nextLine();
        workoutList.removeWorkout(name);
        System.out.println(name + "was removed!");
        dealUserInput(workoutList);
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
                s = makeLowerCase(s);
                parseStringForWorkoutList(s);
            }
        }
    }
}
