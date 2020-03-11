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
        createAddExerciseButtonFunctionality(recentFrame, workoutList);
        createGoBackButtonFunctionality(recentFrame, workoutList);
    }

    // EFFECTS: adds functionality to the go back button; takes users to WorkoutListGUI if pressed.

    private void createGoBackButtonFunctionality(JFrame recentFrame, WorkoutList workoutList) {
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
    }

    // EFFECTS: creates new exercise with user input, adds the new exercise to listOfExercises;
    //          navigates user to WorkoutListGUI.

    private void createAddExerciseButtonFunctionality(JFrame recentFrame, WorkoutList workoutList) {
        addExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Workout newWorkout = new Workout(textArea1.getText(), textArea2.getText(), false);
                workoutList.addWorkout(newWorkout);
                moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
    }


    // GETTERS

    public Container getPanel() {
        return this.panelMain;
    }
}
