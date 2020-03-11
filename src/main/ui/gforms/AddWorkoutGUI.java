package ui.gforms;

import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkoutGUI extends GUI {
    private JPanel panelMain;
    private JTextField header;
    private JTextField textArea1;
    private JTextField textArea2;
    private JButton goBackButton;
    private JButton addExerciseButton;

    public AddWorkoutGUI(JFrame recentFrame, WorkoutList workoutList) {
        header.setEditable(false);
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