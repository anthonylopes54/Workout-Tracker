package ui;

import model.WorkoutList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WorkoutList workoutList = new WorkoutList();
        ConsoleInterface consoleInterface = new ConsoleInterface(workoutList);
        System.out.println("Welcome to the Lifestyle Tracker!");

        consoleInterface.dealUserInput(workoutList, ConsoleInterface.ADD_WORKOUT_COMMAND);
    }


}
