package ui.GForms;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutListGUI {
    private JPanel panelMain;
    private JTextField LISTOFEXERCISETextField;
    private JList list1;
    private JTextArea textArea1;
    private JTextField NOTESTextField;
    private JButton saveButton;
    private JButton addWorkoutButton;


    public WorkoutListGUI(JFrame recentFrame) {

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        addWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame thisFrame = new JFrame("Add a Workout");
                AddWorkoutGUI thisGUI = new AddWorkoutGUI(thisFrame);
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
