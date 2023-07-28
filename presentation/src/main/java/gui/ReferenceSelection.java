package gui;

import javax.swing.*;
import api.BibleAPI;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The referenceSelection class is to create the GUI for the referenceSelector
 * @author Matthew McCaughey
 *
 */
public class ReferenceSelection {

	/**
	 * The referenceSelector method opens the Reference Selector GUI to run the program.
	 * @param verseDisplayGUI The Verse Display GUI to correspond and send verses to.
	 */
    public static void referenceSelector(VerseDisplay verseDisplayGUI) {
        // Create the first drop-down menu
        JComboBox<String> comboBox1 = new JComboBox<>(BibleAPI.getBookNames());

        // Create a JTextField for the "Chapter" field
        JTextField chapterField = new JTextField(5);
        chapterField.setText("1"); // Set initial value to "1"

        // Create a JTextField for the "Verse" field
        JTextField verseField = new JTextField(5);
        verseField.setText("1"); // Set initial value to "1"

        // Create a panel to hold the components
        JPanel panel = createJPanel(comboBox1, chapterField, verseField);

        // Create a custom JButton for "Send"
        JButton sendButton = new JButton("Send");
        panel.add(sendButton);

        // Add an ActionListener to comboBox1
        comboBox1.addActionListener(e -> {
            chapterField.setText("1");
            verseField.setText("1");
        });

        // Add the send button listener
        addSendButtonListener(sendButton, comboBox1, chapterField, verseField, verseDisplayGUI);

        // Create a custom JDialog to hold the panel
        JDialog dialog = createJDialog(panel);
        dialog.setVisible(true);
    }
    
    /**
     * Creates a custom JDialog to hold the panel.
     * @param panel The JPanel that holds the components.
     * @return The created JDialog.
     */
    private static JDialog createJDialog(JPanel panel) {
    	JDialog dialog = new JDialog();
        dialog.setTitle("Verse Selector");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Change this to JDialog.DO_NOTHING_ON_CLOSE
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center the dialog on the screen
        
        return dialog;
    }
    
    /**
     * Creates a JPanel to hold the components.
     * @param comboBox1 The JComboBox representing the book drop-down menu.
     * @param chapterField The JTextField for chapter input.
     * @param verseField The JTextField for verse input.
     * @return The created JPanel.
     */
    private static JPanel createJPanel(JComboBox<String> comboBox1, JTextField chapterField, JTextField verseField) {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment, 10px vertical and horizontal gaps
        panel.add(new JLabel("Book"));
        panel.add(comboBox1);
        panel.add(new JLabel("Chapter"));
        panel.add(chapterField);
        panel.add(new JLabel("Verse"));
        panel.add(verseField);
        
        return panel;
    }
    
    /**
     * Adds an ActionListener to the "Send" button to handle verse retrieval and display.
     * 
     * @param sendButton The JButton representing the "Send" button.
     * @param comboBox1 The JComboBox representing the book drop-down menu.
     * @param chapterField The JTextField for chapter input.
     * @param verseField The JTextField for verse input.
     * @param verseDisplayGUI The Verse Display GUI to display the selected verse.
     */
    private static void addSendButtonListener(JButton sendButton, JComboBox<String> comboBox1, JTextField chapterField,
            JTextField verseField, VerseDisplay verseDisplayGUI) {
    	sendButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// Get the selected options from the components
                String selectedOption1 = (String) comboBox1.getSelectedItem();
                String chapterText = chapterField.getText().trim();
                String verseText = verseField.getText().trim();

                if (!chapterText.isEmpty() && !verseText.isEmpty()) {
                    try {
                        int selectedOption2 = Integer.parseInt(chapterText);
                        int selectedOption3 = Integer.parseInt(verseText);

                        // Check if the selected chapter and verse are within the valid range
                        int maxChapter = BibleAPI.getChapters(BibleAPI.getBookID(selectedOption1)).length;
             
                        
                        if(selectedOption2 > 0 && selectedOption2 <=maxChapter) {
                        int maxVerse = BibleAPI.getVerses(BibleAPI.getBookID(selectedOption1), selectedOption2).length;
                        
	                        if (selectedOption3 > 0 && selectedOption3 <= maxVerse) {
	
	                            // Update the text in the VerseDisplay GUI with the selected options
	                        	System.out.println(selectedOption2 + "<=" + maxChapter);
	                            String verseText1 = BibleAPI.getVerse(BibleAPI.getBookID(selectedOption1), selectedOption2, selectedOption3);
	                            verseDisplayGUI.setText(verseText1);
	                        } else {
	                            // Show an error message for out of range input
	                            JOptionPane.showMessageDialog(
	                                    null,
	                                    "Invalid chapter or verse number!",
	                                    "Error",
	                                    JOptionPane.ERROR_MESSAGE
	                            		);
	                        		}
                        } else {
                            // Show an error message for out of range input
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Invalid chapter or verse number!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            		);
                        		}
                        
                    	} catch (NumberFormatException ex) {
                        // Show an error message for invalid integer input
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter valid chapter and verse numbers!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    // Show an error message for empty fields
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter chapter and verse numbers!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
    		});
    	}
    
}
