package ui;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.WorkoutList;

import java.util.Scanner;

import static ui.ConsoleInterface.dealUserInput;

public class Main {
    public static void main(String[] args) {
        WorkoutList workoutList = new WorkoutList();
        ConsoleInterface consoleInterface = new ConsoleInterface(workoutList);
        dealUserInput(workoutList);


    }


}
