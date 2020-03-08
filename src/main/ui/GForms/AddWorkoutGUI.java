package ui.GForms;

import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddWorkoutGUI extends GUI{
    private JPanel panelMain;
    private JTextField LISTOFEXERCISETextField;
    private JTextField textArea1;
    private JTextField textArea2;
    private JButton goBackButton;
    private JButton addExerciseButton;

    public AddWorkoutGUI(JFrame recentFrame, WorkoutList workoutList) {
        addExerciseButton.addActionListener(new ActionListener() {
            // TODO: Back end
            @Override
            public void actionPerformed(ActionEvent e) {
                Workout newWorkout = new Workout(textArea1.getText(), textArea2.getText(), false);
                workoutList.addWorkout(newWorkout);
                moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
    }


    // GETTERS

    public Container getPanel() {
        return this.panelMain;
    }
}
