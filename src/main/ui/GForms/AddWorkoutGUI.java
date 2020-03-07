package ui.GForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddWorkoutGUI {
    private JPanel panelMain;
    private JTextField LISTOFEXERCISETextField;
    private JTextField textArea1;
    private JTextField textArea2;
    private JTextField textArea3;
    private JButton goBackButton;
    private JButton addExerciseButton;

    public AddWorkoutGUI(JFrame recentFrame) {
        addExerciseButton.addActionListener(new ActionListener() {
            // TODO: Back end
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToWorkoutListForm(recentFrame);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackToWorkoutListForm(recentFrame);
            }
        });
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onlyNumber(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                removeAllText(e);
            }
        });
        textArea2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onlyNumber(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                removeAllText(e);
            }
        }
        );
        textArea3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onlyNumber(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                removeAllText(e);
            }
        });
    }

    private void moveBackToWorkoutListForm(JFrame recentFrame) {
        JFrame thisFrame = new JFrame("Add a Workout");
        WorkoutListGUI thisGUI = new WorkoutListGUI(thisFrame);
        thisFrame.setContentPane(thisGUI.getPanel());
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.pack();
        thisFrame.setVisible(true);
        panelMain.setVisible(false);
        recentFrame.dispose();
    }

    // HELPERS

    private void onlyNumber(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            textArea1.setEditable(true);
        } else {
            textArea1.setEditable(false);
        }
    }

    private void removeAllText(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            textArea1.setText("");
        } else {
            textArea1.setEditable(false);
        }
    }

    // GETTERS

    public Container getPanel() {
        return this.panelMain;
    }
}
