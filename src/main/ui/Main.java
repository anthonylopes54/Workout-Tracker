package ui;

// Begins  program with the instantiation of a new ConsoleInterface object

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.Workout;
import model.WorkoutList;
import ui.GForms.WorkoutListGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame thisFrame = new JFrame("Your Workouts");
        WorkoutList workoutList = new WorkoutList();
        WorkoutListGUI thisGUI = new WorkoutListGUI(thisFrame, workoutList);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);


        //ConsoleInterface beginProgram = new ConsoleInterface();
    }


}
