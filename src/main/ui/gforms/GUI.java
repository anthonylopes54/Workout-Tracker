package ui.gforms;

import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.event.KeyEvent;

// A class with methods related to numerous GUI forms used within this project

public abstract class GUI {

    // EFFECTS: limits user input so user can only type numbers in given JTextField

    protected void onlyNumber(KeyEvent e, JTextField textArea) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            textArea.setEditable(true);
        } else {
            textArea.setEditable(false);
        }
    }

    // EFFECTS: allows user to delete all of what they have typed thus far using the backspace or delete keys

    protected void removeAllText(KeyEvent e, JTextField textArea) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            textArea.setText("");
        } else {
            textArea.setEditable(false);
        }
    }

    // EFFECTS: navigates user back to WorkoutListGUI form.

    protected void moveBackToWorkoutListForm(JFrame recentFrame, JPanel panelMain, WorkoutList workoutList) {
        JFrame thisFrame = new JFrame("Workout List");
        WorkoutListGUI thisGUI = new WorkoutListGUI(thisFrame, workoutList);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }

    // EFFECTS: navigates user back to the ExerciseListGUI form

    protected void moveBackToExerciseListForm(JFrame recentFrame,
                                              JPanel panelMain,WorkoutList workoutList, Workout workout) {
        JFrame thisFrame = new JFrame("Exercise List");
        ExerciseListGUI thisGUI = new ExerciseListGUI(thisFrame, workoutList, workout);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }
}
