package ui.gforms;

import model.Exercise;
import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddExerciseGUI extends GUI {
    private JPanel panelMain;
    private JTextField header;
    private JTextField textArea1;
    private JTextField textArea2;
    private JTextField textArea3;
    private JButton goBackButton;
    private JButton addExerciseButton;

    public AddExerciseGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        header.setEditable(false);
        createAddExerciseButtonFunctionality(recentFrame, workoutList, workout);
        createBackButtonFunctionality(recentFrame, workoutList, workout);
        textArea2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onlyNumber(e, textArea2);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                removeAllText(e, textArea2);
            }
        });
        textArea3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onlyNumber(e, textArea3);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                removeAllText(e, textArea3);
            }
        });
    }

    private void createBackButtonFunctionality(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
    }

    private void createAddExerciseButtonFunctionality(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        addExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sets = Integer.valueOf(textArea2.getText());
                int reps = Integer.valueOf(textArea3.getText());
                Exercise newExercise = new Exercise(textArea1.getText(), sets, reps);
                workout.addExercise(newExercise);
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
    }

    public Container getPanel() {
        return this.panelMain;
    }


}
