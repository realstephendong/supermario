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
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MarioTitle {
	// Create the class
	MarioTitle() {
		// Surround everything in a try-catch method so that the title frame music can be stopped
		// after clicking buttons
		try {
			File musicPath = new File("images/titleSong.wav");
			// Title screen music starts to loop and play
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
				// The main frame for the title screen is created
				JFrame mainFrame = new JFrame("SuperMario");
				// The main frames size is set
			    mainFrame.setSize(1280, 720);
			    // Null layout so we can freely place buttons in the frame
				mainFrame.setLayout(null);
				// Not resizable
				mainFrame.setResizable(false);
				// When the title screen is closed with the "X" in the corner, the entire program quits
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
				// An image icon is created for the background
		        ImageIcon background = new ImageIcon("images/gameLogo.png");
		        // the JLabel for the background is created
		        JLabel backgroundLabel = new JLabel(background);
		        // The label is set to the size of the frame to cover the screen
		        backgroundLabel.setSize(1280,720);
		        
		        
		        // Create an image icon for the start button
		        ImageIcon start = new ImageIcon("images/startButton.png");
		        // Create the start button
		        JButton startButton = new JButton(start);
		        // Button is made transparent
		        startButton.setBorder(null);
		        startButton.setBorderPainted(false);
		        startButton.setContentAreaFilled(false);
		        startButton.setOpaque(false);
		        // Cursor is set to the hand cursor when the button is hovered over
		        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        // The button size and position is set
		        startButton.setBounds(630, 540, 206, 88);
		        startButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// When the button is pressed...
						// If the theme chosen from the theme frame is MARIODAY...
						if (ThemeFrame.MARIODAY) {
							// A new level is created with the LevelFrame class as it creates a level with the
							// MARIODAY theme
							new LevelFrame(LevelSelectFrame.levelNum);
							// The main frame is closed
							mainFrame.dispose();
							// The music stops playing
							clip.stop();
						}
						else {
							// The only other cases when the LUIGINIGHT theme is selected
							// If so, a new level is created with the LevelFrame2 class as it creates a level with 
							// the LUIGINIGHT theme
							new LevelFrame2(LevelSelectFrame.levelNum);
							// The main frame is closed
							mainFrame.dispose();
							// The music stops playing
							clip.stop();
						}
					}
		        });
		        
		        // An image icon for the theme button is created
		        ImageIcon theme = new ImageIcon("images/themeButton.png");
		        // The theme button is created
		        JButton changeTheme = new JButton(theme);
		        // Button is made transparent
		        changeTheme.setBorder(null);
		        changeTheme.setBorderPainted(false);
		        changeTheme.setContentAreaFilled(false);
		        changeTheme.setOpaque(false);
		        // Hand cursor displays when hovering over button
		        changeTheme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        // Buttons size and position is set
		        changeTheme.setBounds(710, 90, 202, 95);
		        changeTheme.addActionListener(new ActionListener() {
		        	// When the button is pressed...
					public void actionPerformed(ActionEvent e) {
						// A new theme frame is created and opened
						new ThemeFrame();
					}
		        });
		        
		        // An image icon for the close button is created
		        ImageIcon close = new ImageIcon("images/closeButton.png");
		        // The close button is created
		        JButton closeButton = new JButton(close);
		        // The button is made transparent
		        closeButton.setBorder(null);
		        closeButton.setBorderPainted(false);
		        closeButton.setContentAreaFilled(false);
		        closeButton.setOpaque(false);
		        // Hand cursor displays when hovering over button
		        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        // The buttons size and position is set
		        closeButton.setBounds(1180, 20, 50, 50);
		        closeButton.addActionListener(new ActionListener() {
		        	// When the button is pressed...
					public void actionPerformed(ActionEvent e) {
						// The entire program terminates
						System.exit(0);
					}
		        });
		        
		        // An image icon for the level select button is created
		        ImageIcon levelSelect = new ImageIcon("images/levelselect.png");
		        // The level select button is created
		        JButton levelButton = new JButton(levelSelect);
		        // The level button is made transparent
		        levelButton.setBorder(null);
		        levelButton.setBorderPainted(false);
		        levelButton.setContentAreaFilled(false);
		        levelButton.setOpaque(false);
		        // Hand cursor displays when hovering over button
		        levelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        // The buttons size and position is set
		        levelButton.setBounds(220, 440, 385, 75);
		        levelButton.addActionListener(new ActionListener() {
		        	// When the button is pressed...
					public void actionPerformed(ActionEvent e) {
						// A new level select frame is created and opened
						new LevelSelectFrame();
					}
		        });
		        
		        // An image icon is created for the advanced feature button
		        ImageIcon moon = new ImageIcon("images/moon.gif");
		        // The special button for the advanced feature is created
		        JButton moonButton = new JButton(moon);
		        // The button is made transparent
		        moonButton.setBorder(null);
		        moonButton.setBorderPainted(false);
		        moonButton.setContentAreaFilled(false);
		        moonButton.setOpaque(false);
		        // Hand cursor displays when hovering over button
		        moonButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        // The buttons size and position is set
		        moonButton.setBounds(70, -20, 73, 302);
		        moonButton.addActionListener(new ActionListener() {
		        	// When the button is pressed...
					public void actionPerformed(ActionEvent e) {
						// A new level is created using the LevelFrame3 class which creates a level
						// with the special character movement changes and new moon theme
						// The text file used is level4.txt, which is a new level built for the new
						// movement that the level select screen does not provide
						new LevelFrame3(4);
						// The main frame closes
						mainFrame.dispose();
						// The music stops playing
						clip.stop();
					}
		        });
		        
		        // All the buttons are added to the main frame
		        mainFrame.add(moonButton);
		        mainFrame.add(levelButton);
		        mainFrame.add(startButton);
		        mainFrame.add(closeButton);
		        mainFrame.add(startButton);
		        mainFrame.add(changeTheme);
		        mainFrame.add(backgroundLabel); 
		        // Make main frame visible
		        mainFrame.setVisible(true);
				
			}
		} catch (Exception e) {
			System.out.println("Can't find file.");
		}
	}
}