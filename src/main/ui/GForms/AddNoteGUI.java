package ui.GForms;

import model.Exercise;
import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNoteGUI extends GUI{
    private JPanel panelMain;
    private JTextField LISTOFEXERCISETextField;
    private JButton backButton;
    private JButton submitButton;
    private JTextArea textArea1;

    public AddNoteGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout, Exercise thisExercise) {

        submitButton.addActionListener(new ActionListener() { //TODO: backend
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
    }
    public Container getPanel() {
        return this.panelMain;
    }
}
