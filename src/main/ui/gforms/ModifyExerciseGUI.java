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

// A class for modifying an exercise via the associated GUI

public class ModifyExerciseGUI extends GUI {
    private JPanel panelMain;
    private JTextField header;
    private JTextField textArea1;
    private JTextField textArea2;
    private JTextField textArea3;
    private JButton goBackButton;
    private JButton modifyExerciseButton;

    public ModifyExerciseGUI(JFrame recentFrame,
                             WorkoutList workoutList, Workout workout, Exercise exercise) {
        setup(exercise);
        createModifyExerciseButtonFunctionality(recentFrame, workoutList, workout, exercise);
        createBackButtonFunctionality(recentFrame, workoutList, workout);
        constrainUserInput();
    }

    // EFFECTS: allows user to type only numbers into text area 2 and 3;
    //          allows delete to delete the whole content of the text area

    private void constrainUserInput() {
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

    // EFFECTS: add back button functionality; navigates user back to ExerciseListGUI if clicked

    private void createBackButtonFunctionality(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
    }

    // MODIFIES: Exercise
    // EFFECTS: add functionality to modify exercise button; if clicked, modifies exercise with user input
    //          and navigates user back to ExerciseListGUI form.

    private void createModifyExerciseButtonFunctionality(JFrame recentFrame,
                                                         WorkoutList workoutList, Workout workout, Exercise exercise) {
        modifyExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exercise.changeName(textArea1.getText());
                exercise.changeSets(Integer.parseInt(textArea2.getText()));
                exercise.changeReps(Integer.parseInt(textArea3.getText()));
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets some properties of panel objects in form

    private void setup(Exercise exercise) {
        header.setEditable(false);
        textArea1.setText(exercise.getName());
        textArea2.setText(Integer.toString(exercise.getSets()));
        textArea3.setText(Integer.toString(exercise.getReps()));
    }

    // Getters

    // EFFECTS: returns this.panelMain

    public Container getPanel() {
        return this.panelMain;
    }


}

