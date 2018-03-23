import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Form {
    private JPanel panel1;
    private JTextField widthField;
    private JTextField heightField;
    private JButton generateButton;
    private JButton saveButton;
    private Maze maze;

    public Form() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    maze = new Maze(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));
                    JOptionPane.showMessageDialog(panel1,"Maze sucessfully generated", "Success", 1);
                }
                catch(ArrayIndexOutOfBoundsException | NegativeArraySizeException ee) {
                    JOptionPane.showMessageDialog(panel1,"Number must be greater than 0!","Error",0);
                }
                catch(NumberFormatException ee) {
                    JOptionPane.showMessageDialog(panel1,"Must be a number!","Error",0);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showSaveDialog(panel1);
                if(result==JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    maze.writeToFile(file);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Generator");
        frame.setContentPane(new Form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
