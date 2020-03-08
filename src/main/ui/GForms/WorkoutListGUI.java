package ui.GForms;

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
        list1.addMouseListener(new MouseAdapter() {
        });
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

    // HELPERS

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