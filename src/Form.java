import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form {
    private JPanel panel1;
    private JTextField widthField;
    private JTextField heightField;
    private JButton generateButton;
    private JTextArea mazeField;

    public Form() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Maze maze = new Maze(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));
                    mazeField.setText(maze.getMaze());
                }
                catch(ArrayIndexOutOfBoundsException | NegativeArraySizeException ee) {
                    JOptionPane.showMessageDialog(panel1,"Number must be greater than 0!","Error",0);
                }
                catch(NumberFormatException ee) {
                    JOptionPane.showMessageDialog(panel1,"Must be a number!","Error",0);
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
    }
}
