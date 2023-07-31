package main;

import javax.swing.SwingUtilities;
import gui.*;

public class ProgramMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            VerseDisplay verseDisplayGUI = new VerseDisplay();
            verseDisplayGUI.setVisible(true);

            ReferenceSelection.referenceSelector(verseDisplayGUI);
        });
	}
	
}
