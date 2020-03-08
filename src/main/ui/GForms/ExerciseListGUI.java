package ui.GForms;

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

public class ExerciseListGUI extends GUI{
    private JPanel panelMain;
    private JTextField LISTOFEXERCISETextField;
    private JList list1;
    private JTextArea textArea1;
    private JTextField NOTESTextField;
    private JButton backButton;
    private JButton addNoteButton;
    private JButton addExerciseButton;
    private JButton removeExerciseButton;
    private Map<Integer, Exercise> populatedList;
    private DefaultListModel listModel;

    public ExerciseListGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        populatedList = new HashMap<>();
        listModel = new DefaultListModel();
        populateList(workout);
        list1.setModel(listModel);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
        addExerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveToAddExerciseForm(workoutList, workout, recentFrame);
            }
        });
        removeExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastIndex = list1.getSelectedIndex();
                if (lastIndex >= 0) {
                    Exercise removeThisExercise = populatedList.get(lastIndex);
                    workout.removeExercise(removeThisExercise);
                    textArea1.setText("");
                    populateList(workout);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an Exercise");
                }
            }
        });
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToAddNoteForm(workoutList, workout, recentFrame);
            }
        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int lastIndex = list1.getSelectedIndex();
                if (e.getClickCount() == 1) {
                    if (lastIndex >= 0) {
                        super.mouseClicked(e);
                        Exercise nextExercise = populatedList.get(lastIndex);
                        String nextExerciseNote = nextExercise.getNotesGUI();
                        textArea1.setText(nextExerciseNote);
                    }
                }
                if (e.getClickCount() >= 2) {
                    if (lastIndex >= 0) {
                        Exercise nextExercise = populatedList.get(lastIndex);
                        moveToModifyExerciseForm(recentFrame, workoutList, workout, nextExercise);
                    }
                }
            }
        });
    }

    private void moveToAddNoteForm(WorkoutList workoutList, Workout workout, JFrame recentFrame) {
        int exerciseIndex = list1.getSelectedIndex();
        if (exerciseIndex >= 0) {
            Exercise exercise = populatedList.get(exerciseIndex);
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


    // HELPERS

    private void moveToModifyExerciseForm(JFrame recentFrame, WorkoutList workoutList, Workout workout, Exercise nextExercise) {
        JFrame thisFrame = new JFrame("ModifyExercise");
        ModifyExerciseGUI thisGUI = new ModifyExerciseGUI(thisFrame, workoutList, workout, nextExercise);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }

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

    private void populateList(Workout workout) {
        listModel.clear();
        populatedList.clear();
        int index = 0;
        for (Exercise next : workout.getListOfExercise()) {
            listModel.addElement((next.getName() + " S: " + next.getSets() + " R: " + next.getReps()));
            populatedList.put(index, next);
            index++;
        }
    }

    // GETTERS

    public JPanel getPanel() {
        return this.panelMain;
    }
}
