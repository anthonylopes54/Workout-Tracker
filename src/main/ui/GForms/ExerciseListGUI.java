package ui.GForms;

import model.Workout;
import model.WorkoutList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public ExerciseListGUI(JFrame recentFrame, WorkoutList workoutList, Workout workout) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            moveBackToWorkoutListForm(recentFrame, panelMain, workoutList);
            }
        });
        addExerciseButton.addActionListener(new ActionListener() {
            @Override //TODO: create conditional statement for if nothing in list was clicked and determine exercise to be passed
            public void actionPerformed(ActionEvent e) {
                JFrame thisFrame = new JFrame("Add an exercise");
                AddExerciseGUI thisGUI = new AddExerciseGUI(thisFrame, workoutList, workout);
                thisFrame.setContentPane(thisGUI.getPanel());
                thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.pack();
                thisFrame.setVisible(true);
                panelMain.setVisible(false);
                recentFrame.dispose();
            }
        });
        removeExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addNoteButton.addActionListener(new ActionListener() {
            @Override //TODO: create conditional statement for if nothing in list was clicked and determine exercise to be passed
            public void actionPerformed(ActionEvent e) {
                JFrame thisFrame = new JFrame("Add a note");
                AddNoteGUI thisGUI = new AddNoteGUI(thisFrame, workoutList, workout, null);
                thisFrame.setContentPane(thisGUI.getPanel());
                thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisFrame.pack();
                thisFrame.setVisible(true);
                panelMain.setVisible(false);
                recentFrame.dispose();
            }
        });
    }

    public JPanel getPanel() {
        return this.panelMain;
    }
}
