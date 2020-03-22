package ui.gforms;

import model.Exercise;
import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// A class for adding a note to an exercise via the associated GUI

public class AddNoteGUI extends GUI {
    private JPanel panelMain;
    private JTextField header;
    private JButton backButton;
    private JButton addNoteButton;
    private JList listOfNotes;
    private JTextArea userTextField;
    private JButton removeNoteButton;
    private JTextField askUserToTypeNoteLabel;
    private Map<Integer, String> mapForList;
    private DefaultListModel listModel;

    public AddNoteGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout, Exercise thisExercise) {
        setup(thisExercise);
        createAddNoteButtonFunctionality(recentFrame, workoutList, workout, thisExercise);
        createBackButtonFunctionality(recentFrame, workoutList, workout);
        createRemoveNoteButtonFunctionality(thisExercise);
    }

    // EFFECTS: instantiates fields and sets characteristics of some panel objects

    private void setup(Exercise thisExercise) {
        mapForList = new HashMap<>();
        listModel = new DefaultListModel();
        populateList(thisExercise);
        listOfNotes.setModel(listModel);
        header.setEditable(false);
    }

    // MODFIES: this, Exercise
    // EFFECTS: adds functionality to the remove note button functionality; removes note from list;
    //          if the user has not selected a note, a message dialog pops up asking user to select a note

    private void createRemoveNoteButtonFunctionality(Exercise thisExercise) {
        removeNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastIndex = listOfNotes.getSelectedIndex();
                if (lastIndex >= 0) {
                    String removeThisNote = mapForList.get(lastIndex);
                    thisExercise.removeNote(removeThisNote);
                    populateList(thisExercise);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a note!");
                }
            }
        });
    }

    // EFFECTS: adds functionality to the back button; takes users to the ExerciseListGUI form

    private void createBackButtonFunctionality(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
            }
        });
    }

    // MODIFIES: this, Exercise
    // EFFECTS: add functionality to the add note button; adds user's not to list of notes

    private void createAddNoteButtonFunctionality(JFrame recentFrame,
                                                  WorkoutList workoutList, Workout workout, Exercise thisExercise) {
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userTextField.getText().trim().length() > 0) {
                    thisExercise.addNote(userTextField.getText());
                    moveBackToExerciseListForm(recentFrame, panelMain, workoutList, workout);
                } else {
                    JOptionPane.showMessageDialog(null, "Please type out a note you would like to add!");
                }
            }
        });
    }

    // EFFECTS: Deletes all the elements in the Jlist and repopulates the list with notes from exercise's list of notes

    private void populateList(Exercise exercise) {
        listModel.clear();
        mapForList.clear();
        int index = 0;
        for (String next : exercise.getListOfNote()) {
            listModel.addElement(next);
            mapForList.put(index, next);
            index++;
        }
    }

    // Getters

    // EFFECTS: returns this.panelMain

    public Container getPanel() {
        return this.panelMain;
    }
}
