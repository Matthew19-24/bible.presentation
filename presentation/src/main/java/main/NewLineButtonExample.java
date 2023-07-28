package main;
import javax.swing.*;
import java.awt.*;

public class NewLineButtonExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("New Line Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        // Add the buttons to the panel
        panel.add(button1);
        panel.add(button2);

        // Create a new line using an empty JLabel or Component
        panel.add(new JLabel()); // You can also use: panel.add(new JPanel());

        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        // Add the buttons to the new line
        panel.add(button3);
        panel.add(button4);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}