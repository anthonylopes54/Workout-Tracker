package ui.gforms;

import model.Exercise;
import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
//TODO: class comment
public class ExerciseListGUI extends GUI {
    private JPanel panelMain;
    private JTextField header;
    private JList list1;
    private JTextArea notesTextArea;
    private JTextField notesHeader;
    private JButton backButton;
    private JButton addNoteButton;
    private JButton addExerciseButton;
    private JButton removeExerciseButton;
    private Map<Integer, Exercise> mapForList;
    private DefaultListModel listModel;

    public ExerciseListGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        setup(workout);
        createBackButtonFunctionality(recentFrame, workoutList);
        addExerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveToAddExerciseForm(workoutList, workout, recentFrame);
            }
        });
        removeExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExerciseButtonFunctionality(workout);
            }
        });
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToAddNoteForm(workoutList, workout, recentFrame);
            }
        });
        createListFunctionaility(recentFrame, workoutList, workout);
    }

    // EFFECTS: adds back button functionality; navigates users back to ListOfWorkoutGUI form if pressed.

    private void createBackButtonFunctionality(JFrame recentFrame, WorkoutList workoutList) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
    }

    // EFFECTS: allows user to select items in the jlist object. If an item is clicked twice, it take the user into
    //          ModifyExerciseGUI form. If the item is clicked once, the notes for the exercises are shown.

    private void createListFunctionaility(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int lastIndex = list1.getSelectedIndex();
                if (e.getClickCount() == 1) {
                    if (lastIndex >= 0) {
                        super.mouseClicked(e);
                        Exercise nextExercise = mapForList.get(lastIndex);
                        String nextExerciseNote = nextExercise.getNotesGUI();
                        notesTextArea.setText(nextExerciseNote);
                    }
                }
                if (e.getClickCount() >= 2) {
                    if (lastIndex >= 0) {
                        Exercise nextExercise = mapForList.get(lastIndex);
                        moveToModifyExerciseForm(recentFrame, workoutList, workout, nextExercise);
                    }
                }
            }
        });
    }

    // MODIFIES: this, Workout
    // EFFECTS: adds functionality to the remove exercise button;
    //          removes exercise from listOfExercise and repopulates Jlist

    private void removeExerciseButtonFunctionality(Workout workout) {
        int lastIndex = list1.getSelectedIndex();
        if (lastIndex >= 0) {
            Exercise removeThisExercise = mapForList.get(lastIndex);
            workout.removeExercise(removeThisExercise);
            notesTextArea.setText("");
            populateList(workout);
        } else {
            JOptionPane.showMessageDialog(null, "Please select an Exercise");
        }
    }

    // MODIFIES: this
    // EFFECTS: instantiates fields and sets the characteristics of the panel objects

    private void setup(Workout workout) {
        header.setEditable(false);
        notesHeader.setEditable(false);
        mapForList = new HashMap<>();
        listModel = new DefaultListModel();
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);
        populateList(workout);
        list1.setModel(listModel);
    }

    // EFFECTS: navigates user to AddNoteGUI form if an exercise is selected; otherwise, show a message dialog asking
    //          user to select an exercise

    private void moveToAddNoteForm(WorkoutList workoutList, Workout workout, JFrame recentFrame) {
        int exerciseIndex = list1.getSelectedIndex();
        if (exerciseIndex >= 0) {
            Exercise exercise = mapForList.get(exerciseIndex);
            JFrame thisFrame = new JFrame("Add a note");
            AddNoteGUI thisGUI = new AddNoteGUI(thisFrame, workoutList, workout, exercise);
            thisFrame.setContentPane(thisGUI.getPanel());
            thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            thisFrame.pack();
            thisFrame.setVisible(true);
            panelMain.setVisible(false);
            recentFrame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Please select an Exercise!");
        }
    }


    // EFFECTS: navigates user to ModifyExerciseGUI form

    private void moveToModifyExerciseForm(JFrame recentFrame,
                                          WorkoutList workoutList, Workout workout, Exercise nextExercise) {
        JFrame thisFrame = new JFrame("ModifyExercise");
        ModifyExerciseGUI thisGUI = new ModifyExerciseGUI(thisFrame, workoutList, workout, nextExercise);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }

    // EFFECTS: navigates user to AddExerciseGUI form

    private void moveToAddExerciseForm(WorkoutList workoutList, Workout workout, JFrame recentFrame) {
        JFrame thisFrame = new JFrame("Add an exercise");
        AddExerciseGUI thisGUI = new AddExerciseGUI(thisFrame, workoutList, workout);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Deletes all the elements in the Jlist and repopulates the list with exercises from list of exercises

    private void populateList(Workout workout) {
        listModel.clear();
        mapForList.clear();
        int index = 0;
        for (Exercise next : workout.getListOfExercise()) {
            listModel.addElement((next.getName() + " S: " + next.getSets() + " R: " + next.getReps()));
            mapForList.put(index, next);
            index++;
        }
    }

    // GETTERS

    public JPanel getPanel() {
        return this.panelMain;
    }
}
