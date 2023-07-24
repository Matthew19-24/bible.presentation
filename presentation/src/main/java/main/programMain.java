package main;

import javax.swing.SwingUtilities;
import gui.*;

public class programMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            VerseDisplay verseDisplayGUI = new VerseDisplay();
            verseDisplayGUI.setVisible(true);

            ReferenceSelection.referenceSelector(verseDisplayGUI);
        });
	}
	
}
