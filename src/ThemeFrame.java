// Import the required classes
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThemeFrame {
	// Create global boolean variables that tells the title frame which theme the player chose
	public static boolean MARIODAY = true;
	public static boolean LUIGINIGHT = false;

	// Create the main class
	ThemeFrame() {
		
		// Create the main frame
		JFrame mainFrame = new JFrame("SuperMario");
		// Set the size of the main frame
	    mainFrame.setSize(802, 720);
	    // A grid layout is used for easy implementation of buttons
		mainFrame.setLayout(new GridLayout());
		// The theme frame is not resizable
		mainFrame.setResizable(false);
		
        // An image icon for the theme 1 button is created
        ImageIcon marioDay = new ImageIcon("images/Theme1.png");
        // A button to choose theme 1 is created
        JButton marioButton = new JButton(marioDay);
        // Hand cursor is displayed when hovering over button
        marioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        marioButton.addActionListener(new ActionListener() {
        	// When button is pressed...
			public void actionPerformed(ActionEvent e) {
				// MARIODAY theme is true, meaning it's selected
				MARIODAY = true;
				// LUIGINIGHT theme is true, meaning it's not selected
				LUIGINIGHT = false;
				// The main frame is closed
				mainFrame.dispose();
			}
        });
        // The theme 1 button is added to the main frame
        mainFrame.add(marioButton);
        
        // An image icon for the theme 2 button is created
        ImageIcon luigiNight = new ImageIcon("images/Theme2.png");
        // A button to choose theme 2 is created
        JButton luigiButton = new JButton(luigiNight);
     // Hand cursor is displayed when hovering over button
        luigiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        luigiButton.addActionListener(new ActionListener() {
        	// When button is pressed...
			public void actionPerformed(ActionEvent e) {
				// MARIODAY theme is true, meaning it's not selected
				MARIODAY = false;
				// LUIGINIGHT theme is true, meaning it's selected
				LUIGINIGHT = true;
				mainFrame.dispose();
			}
        });
        // The theme 2 button is added to the main frame
        mainFrame.add(luigiButton);
        
 
        // Make the frame visible
        mainFrame.setVisible(true);
	}
}