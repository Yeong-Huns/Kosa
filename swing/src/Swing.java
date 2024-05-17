

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Swing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Text Field Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel dollarLabel = new JLabel("$");
        gbc.gridx = 0;
        gbc.gridy = 0;
        grid.add(dollarLabel, gbc);

        JTextField sumTextField = new JTextField(10) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void processKeyEvent(KeyEvent e) {
                char c = e.getKeyChar();
                if ((Character.isLetter(c) && !e.isAltDown()) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    e.consume();  // Ignore this event
                } else {
                    super.processKeyEvent(e);
                }
            }
        };
        sumTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                // Display the warning message when the user tries to enter letters.
                // You could implement a more sophisticated check here.
                char c = e.getKeyChar();
                if (Character.isLetter(c) && !e.isAltDown()) {
                    JOptionPane.showMessageDialog(grid, "Enter a numeric value");
                }
            }
        });

        gbc.gridx = 1;
        grid.add(sumTextField, gbc);

        JButton submitButton = new JButton("Submit");
        gbc.gridx = 2;
        grid.add(submitButton, gbc);

        JLabel label = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        grid.add(label, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(null);
            }
        });

        frame.getContentPane().add(grid);
        frame.setVisible(true);
    }
}
