package gui;

import javax.swing.*;
import api.BibleAPI;

public class ReferenceSelection {

	public static void main(String[] args) {
		referenceSelector();
	}
	
    public static void referenceSelector() {
     
        // Create the first drop-down menu
        JComboBox<String> comboBox1 = new JComboBox<>(BibleAPI.getBookNames());

        // Create the second drop-down menu
        JComboBox<Integer> comboBox2 = new JComboBox<>(toIntegerArray(BibleAPI.getChapters(BibleAPI.getBookID("Genesis"))));

        // Create the third drop-down menu (initially empty)
        JComboBox<Integer> comboBox3 = new JComboBox<>(toIntegerArray(BibleAPI.getVerses("GEN", 1)));

        // Create a panel to hold the drop-down menus
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("Book"));
        panel.add(comboBox1);
        panel.add(new JLabel("Chapter"));
        panel.add(comboBox2);
        panel.add(new JLabel("Verse"));
        panel.add(comboBox3);

        // Add an ActionListener to comboBox1
        comboBox1.addActionListener(e -> {
            String selectedOption1 = (String) comboBox1.getSelectedItem();
            updateComboBoxOptions(selectedOption1, comboBox2, comboBox3);
        });
        
        // Add an ActionListener to comboBox2
        comboBox2.addActionListener(e -> {
            String selectedOption1 = (String) comboBox1.getSelectedItem();
            Integer selectedOption2 = (Integer) comboBox2.getSelectedItem();
            updateComboBoxOptions(selectedOption1, selectedOption2, comboBox3);
        });

        // Show the panel in a JOptionPane
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Dependent Drop-Down Menus",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Check if "OK" button was clicked
        if (result == JOptionPane.OK_OPTION) {
            // Get the selected options from the three menus
            String selectedOption1 = (String) comboBox1.getSelectedItem();
            Integer selectedOption2 = (Integer) comboBox2.getSelectedItem();
            Integer selectedOption3 = (Integer) comboBox3.getSelectedItem();

            // Display the selected options in a single dialog box
            JOptionPane.showMessageDialog(
                    null,
                    "Books: " + selectedOption1 + "\n" +
                            "Chapter: " + selectedOption2 + "\n" +
                            "Verse: " + selectedOption3,
                    "Selected Options",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private static void updateComboBoxOptions(String selectedOption, JComboBox<Integer> comboBox2, JComboBox<Integer> comboBox3) {
        // Determine the options for the second drop-down menu based on the selection in the first menu
        Integer[] menu2Options = toIntegerArray(BibleAPI.getChapters(BibleAPI.getBookID(selectedOption)));

        // Determine the options for the third drop-down menu based on the selection in the first menu
        Integer[] menu3Options = toIntegerArray(BibleAPI.getChapters(selectedOption));

        // Update the options of comboBox2 and comboBox3
        comboBox2.setModel(new DefaultComboBoxModel<>(menu2Options));
        comboBox3.setModel(new DefaultComboBoxModel<>(menu3Options));
    }
    
    private static void updateComboBoxOptions(String selectedOption, int selectedOption2, JComboBox<Integer> comboBox3) {
        // Determine the options for the third drop-down menu based on the selection in the first menu
        Integer[] menu3Options = toIntegerArray(BibleAPI.getVerses(BibleAPI.getBookID(selectedOption), selectedOption2));

        // Update the options of comboBox2 and comboBox3
        comboBox3.setModel(new DefaultComboBoxModel<>(menu3Options));
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = intArray[i];
        }
        return result;
    }
}

