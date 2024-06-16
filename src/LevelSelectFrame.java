// Import all required classes
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

public class LevelSelectFrame {
	// Create a global int variable that stores the level number which can be used 
	// in the MarioTitle method
	public static int levelNum = 1;

	// Create the class
	LevelSelectFrame() {
		
		// Create a frame
		JFrame mainFrame = new JFrame("SuperMario");
		
		// Frame size is set
	    mainFrame.setSize(720, 720);
	    // Frame layout is set to a grid layout
		mainFrame.setLayout(new GridLayout());
		// The frame is set to unresizable
		mainFrame.setResizable(false);

		// An icon is created for the level 1 button
        ImageIcon levl1 = new ImageIcon("images/level1.png");
        // The level 1 button is created
        JButton level1Button = new JButton(levl1);
        // Button is made transparent
        level1Button.setBorder(null);
        level1Button.setBorderPainted(false);
        level1Button.setContentAreaFilled(false);
        level1Button.setOpaque(false);
        // The hand cursor is shown when hovering over the button
        level1Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        level1Button.addActionListener(new ActionListener() {
        	// When the button is pressed...
			public void actionPerformed(ActionEvent e) {
				// The level number is set to 1
				levelNum = 1;
				// The frame is closed
				mainFrame.dispose();
			}
        });
        
        // Same as the level 1 button but the level number is set to 2
        ImageIcon level2 = new ImageIcon("images/level2.png");
        JButton level2Button = new JButton(level2);
        level2Button.setBorder(null);
        level2Button.setBorderPainted(false);
        level2Button.setContentAreaFilled(false);
        level2Button.setOpaque(false);
        level2Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        level2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelNum = 2;
				mainFrame.dispose();
			}
        });
        
        // Same as the level 1 button but the level number is set to 3
        ImageIcon level3 = new ImageIcon("images/level3.png");
        JButton level3Button = new JButton(level3);
        level3Button.setBorder(null);
        level3Button.setBorderPainted(false);
        level3Button.setContentAreaFilled(false);
        level3Button.setOpaque(false);
        level3Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        level3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelNum = 3;
				mainFrame.dispose();
			}
        });
        
        // Add all the created buttons to the frame in order from left to right
        mainFrame.add(level1Button);
        mainFrame.add(level2Button);
        mainFrame.add(level3Button);
        
        // Make frame visible
        mainFrame.setVisible(true);
	}
}