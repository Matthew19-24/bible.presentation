package gui;

import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class VerseDisplay extends JFrame {

	private static String verse = "Please select a verse and click Send.";
	
    private JLabel textLabel;

    public VerseDisplay() {
        // Set up the JFrame
        setTitle("Verse Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create a JLabel to display the text
        textLabel = new JLabel();
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Add the label to the JFrame
        getContentPane().add(textLabel);

        // Set an initial text
        setText(verse);

        // Add a ComponentListener to detect window resizing and update the text size accordingly
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeText();
            }
        });
    }

    public void setText(String text) {
        // Insert line breaks after every 50 characters in the verse text
        StringBuilder formattedText = new StringBuilder("<html><center>");
        int lineLength = 50;
        int currentLineLength = 0;

        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            int wordLength = word.length();

            if (currentLineLength + wordLength > lineLength) {
                formattedText.append("<br>");
                currentLineLength = 0;
            }

            formattedText.append(word).append(" ");
            currentLineLength += wordLength + 1;
        }

        formattedText.append("</center></html>");
        textLabel.setText(formattedText.toString());
        resizeText();
    }


    private void resizeText() {
        // Get the current width and height of the JFrame, considering its insets (borders and title bar)
        Insets insets = getInsets();
        int width = getContentPane().getWidth() - insets.left - insets.right;
        int height = getContentPane().getHeight() - insets.top - insets.bottom;

        // Get the text to be displayed
        String text = textLabel.getText();

        // Calculate the optimal font size based on the width and height constraints
        int fontSize = calculateFontSizeToFit(text, width, height);

        // Set the final font size for the label
        textLabel.setFont(new Font(textLabel.getFont().getName(), textLabel.getFont().getStyle(), fontSize));
    }

    private int calculateFontSizeToFit(String text, int width, int height) {
        int fontSize = 1;
        FontMetrics fontMetrics;

        // Calculate the font size based on the width and height constraints
        do {
            fontSize++;
            Font font = new Font(textLabel.getFont().getName(), textLabel.getFont().getStyle(), fontSize);
            fontMetrics = getFontMetrics(font);

            // Check if the font size allows the text to fit the line width
            String[] lines = text.split("<br>");
            boolean fitsLine = true;
            for (String line : lines) {
                if (fontMetrics.stringWidth(line) > width) {
                    fitsLine = false;
                    break;
                }
            }

            // If the font size is too large for the line width, reduce it
            if (!fitsLine) {
                fontSize--;
                break;
            }
        } while (fontMetrics.getHeight() < height);

        // Reduce font size by 1 to make sure the text fits the frame
        return fontSize - 1;
    }

    public void updateText(String newText) {
        setText(newText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VerseDisplay gui = new VerseDisplay();
            gui.setVisible(true);
        });
    }
}
