package ui;

import model.WorkoutList;

import java.util.Scanner;

public class ConsoleInterface {

    public boolean runProgram;
    private WorkoutList workoutList;
    private Scanner input;

    public ConsoleInterface(WorkoutList workoutList) {
        this.workoutList = workoutList;
        input = new Scanner(System.in);
        runProgram = true;


    }

    public static void dealUserInput(WorkoutList workoutList) {
        System.out.println("Welcome to the Lifestyle Tracker!");
        System.out.println("Your current list of workouts are as follows:");
        workoutList.printListOfWorkouts();
        System.out.println("Type 'Add workout,' 'remove workout' or the name of the workout you would like to view!");

    }
}
