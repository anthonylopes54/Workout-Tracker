package ui.GForms;

import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddExerciseGUI extends GUI{
    private JPanel panelMain;
    private JTextField LISTOFEXERCISETextField;
    private JTextField textArea1;
    private JTextField textArea2;
    private JTextField textArea3;
    private JButton goBackButton;
    private JButton addExerciseButton;

    public AddExerciseGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        addExerciseButton.addActionListener(new ActionListener() {
            //TODO: back end
            @Override
            public void actionPerformed(ActionEvent e) {
            moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
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
    public Container getPanel() {
        return this.panelMain;
    }


}
