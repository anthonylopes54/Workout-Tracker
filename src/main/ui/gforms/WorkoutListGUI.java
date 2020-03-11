package ui.gforms;

import model.Workout;
import model.WorkoutList;
import org.json.simple.parser.ParseException;
import persistence.Read;
import persistence.Write;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// A class that shows the current list of workouts with options to modify the workouts in the list,
// save the workout list and load the last saved workout list via the associated GUI.

public class WorkoutListGUI {
    private JPanel panelMain;
    private JTextField header;
    private JList list1;
    private JTextArea descriptionTextArea;
    private JButton saveButton;
    private JButton addWorkoutButton;
    private JTextField descriptionTextField;
    private JButton removeWorkoutButton;
    private JButton loadLastWorkoutButton;
    private JButton toggleFavButton;
    private JLabel dumbbell;
    private DefaultListModel listModel;
    private Map<Integer, Workout> populatedList;


    public WorkoutListGUI(JFrame recentFrame, WorkoutList workoutList) {
        setup(workoutList);
        createSaveButtonFunctionality(workoutList);
        createAddWorkoutButtonFunctionality(recentFrame, workoutList);
        createListSelectFunctionality(recentFrame, workoutList);
        createRemoveWorkoutButtonFunctionality(workoutList);
        createLoadButtonFunctionality(workoutList);
        createFavouriteButtonFunctionality(workoutList);
    }

    // MODIFIES: this
    // EFFECTS: Adds functionality to the favorite button so users can toggle favourite status for workouts.
    //          If no workout is selected, show message dialog asking user to select a workout

    private void createFavouriteButtonFunctionality(WorkoutList workoutList) {
        toggleFavButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastIndex = list1.getSelectedIndex();
                if (lastIndex >= 0) {                               // if nothing is selected, return value will be -1
                    Workout workout = populatedList.get(lastIndex); // find workout
                    workout.toggleFavourite();
                    populateList(workoutList);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select the workout you would like to toggle!");
                }
            }
        });
    }

    // EFFECTS: Adds functionality to the load button so that users can load their last workout.

    private void createLoadButtonFunctionality(WorkoutList workoutList) {
        loadLastWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Read.readWorkoutListGUI("default", workoutList);
                    populateList(workoutList);
                    JOptionPane.showMessageDialog(null, "Loading the workout list was successful!");
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Loading the workout list was unsuccessful!");
                }
            }
        });
    }

    // MODIFIES: this, workoutList
    // EFFECTS: creates functionality for the remove workout button so that users can remove and delete a workout they
    // selected last. If no workout is chosen, show message dialog asking to select workout.

    private void createRemoveWorkoutButtonFunctionality(WorkoutList workoutList) {
        removeWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastIndex = list1.getSelectedIndex();
                if (lastIndex >= 0) {
                    Workout removeThisWorkout = populatedList.get(lastIndex);
                    workoutList.removeWorkout(removeThisWorkout);
                    descriptionTextArea.setText("");
                    populateList(workoutList);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select the workout you would like to remove!");
                }
            }
        });
    }

    // EFFECTS: allows user to select items in the list object. If an item is clicked twice, it take the user into
    //          the workout. if the item is clicked once, the description of the item is shown

    private void createListSelectFunctionality(JFrame recentFrame, WorkoutList workoutList) {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int lastIndex = list1.getSelectedIndex();
                if (e.getClickCount() == 1) {
                    if (lastIndex >= 0) {
                        super.mouseClicked(e);
                        Workout nextWorkout = populatedList.get(lastIndex);
                        String nextWorkoutDescription = nextWorkout.getDescription();
                        descriptionTextArea.setText(nextWorkoutDescription);
                    }
                }
                if (e.getClickCount() >= 2) {
                    if (lastIndex >= 0) {
                        Workout nextWorkout = populatedList.get(lastIndex);
                        goToWorkout(recentFrame, workoutList, nextWorkout);
                    }
                }
            }
        });
    }

    // EFFECTS: Adds functionality to the add workout button so that users can add custom workouts

    private void createAddWorkoutButtonFunctionality(JFrame recentFrame, WorkoutList workoutList) {
        addWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame thisFrame = new JFrame("Add a Workout");
                AddWorkoutGUI thisGUI = new AddWorkoutGUI(thisFrame, workoutList);
                thisFrame.setContentPane(thisGUI.getPanel());
                thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.pack();
                thisFrame.setVisible(true);
                panelMain.setVisible(false);
                recentFrame.dispose();
            }
        });
    }

    // EFFECTS: adds functionality to the save button so users can save the current state of their workout list

    private void createSaveButtonFunctionality(WorkoutList workoutList) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Write.saveWorkoutList("default", workoutList);
                    JOptionPane.showMessageDialog(null, "The workout list was saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "The save was unsuccessful");
                }
            }
        });
    }

    // EFFECTS: disposes of the current Fram and opens the ExerciseListGUI form

    private void goToWorkout(JFrame recentFrame, WorkoutList workoutList, Workout nextWorkout) {
        JFrame thisFrame = new JFrame("Exercise");
        ExerciseListGUI thisGUI = new ExerciseListGUI(thisFrame, workoutList, nextWorkout);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }

    // MODIFIES: this
    // EFFECTS: instantiates fields and sets the characteristics of the panel objects

    private void setup(WorkoutList workoutList) {
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        header.setEditable(false);
        descriptionTextField.setEditable(false);
        populatedList = new HashMap<>();
        listModel = new DefaultListModel();
        populateList(workoutList);
        list1.setModel(listModel);
        dumbbell.setIcon(new ImageIcon("data/dumbbell.png"));
    }

    // MODIFIES: this
    // EFFECTS: Deletes all the elements in the Jlist and repopulates the list with workouts from list of workouts

    private void populateList(WorkoutList workoutList) {
        listModel.clear();
        populatedList.clear();
        int index = 0;
        for (Workout next : workoutList.getListOfWorkout()) {
            if (next.getFavourite()) {
                listModel.addElement((next.getName() + "*"));
            } else {
                listModel.addElement(next.getName());
            }
            populatedList.put(index, next);
            index++;
        }
    }

    // GETTERS

    public JPanel getPanel() {
        return this.panelMain;
    }
}
