// Import all the required classes
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

// Create the class that makes the window for the level and putting everything together
public class LevelFrame extends JFrame implements KeyListener {

	// Create a global array JLabel that creates the grid for level frame
	public static JLabel[][] boardArray = new JLabel[20][25];
	// Create a global score count label that can be updated in other methods and 
	// displayed on the level frame
	public static JLabel scoreCount = new JLabel ("Current score: 0");

	// Create a JPanel to store everything from the level frame in
	private JPanel lvl1Panel = new JPanel();
	
	// Create a MARIO character using the Character class by passing in the MARIO icon array and
	// String array that stores the movement controls
	public static Character mario = new Character(Icons.MARIO,new String[] { "a", "d", " "});
	
	// Create the constructor method
	public LevelFrame(int level) {
		// playMusic method is run
		playMusic();
		// countDownTimer method is run
		countdownTimer();
		// scoreLabel method is run
		scoreLabel();
		// The size of the level frame is set using the board array and calculating the pixels
		setSize(25 * boardArray[0].length + 15, 25 * boardArray.length + 35);
		// The layout of the frame is set
		setLayout(null);
		// If the frame is closed with the "X" button in the corner, the entire program terminates
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// The level frame is made to be unresizable
		setResizable(false);
		// loadLevel method is run
		loadLevel(level);
		// createlLvlPanel method is run
		createlLvlPanel();
		// setupKeyBindings method is run
		setupKeyBindings();
		// addBackground method is run
		addBackground();
		// The frame's visibility is set to true
		setVisible(true);
	}
	
	// The addBackground method is created
	public void addBackground() {
		// The background JLabel is created
		ImageIcon backgroundImage = new ImageIcon("images/mariobackground.png");
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0,0, lvl1Panel.getWidth(), lvl1Panel.getHeight());
		// The background is added to the level panel
		lvl1Panel.add(backgroundLabel);
	}

	// The scoreLabel method is created
	public void scoreLabel() {
		// The scoreCount label's bounds and size are set
		scoreCount.setBounds(40, -100, 300, 300);
		// Font is changed
		scoreCount.setFont(new Font("Monospaced", Font.PLAIN, 15));
		// Sets the original text in the label
		scoreCount.setText("CURRENT SCORE: 0");
		// Sets the color of the text to black
		scoreCount.setForeground(Color.BLACK);
		// Adds the scoreCount label to the level panel
		lvl1Panel.add(scoreCount);
	}
	
	// The playMusic method is created
	public void playMusic() {
		// A music file is created and played using a try-catch statement
		try {
			File musicPath = new File("images/song.wav");

			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (Exception e) {
			System.out.println("Can't find file.");
		}
	}
	
	// Variables are created for the decrementing variable and timer that runs
	// i and t are for seconds
	static int i;
	static Timer t;
	// i1 and t1 are for tenth of seconds
	static int i1;
	static Timer t1;
	static String secondTimer;
	// Creates the countDownTimer method
	public void countdownTimer() {
		// A JLabel for the timer is created
		// It's original text is set
		JLabel timer = new JLabel("TIME REMAINING: 120.0");
		// Font is set
		timer.setFont(new Font("Monospaced", Font.PLAIN, 15));
		// Position and size is set
		timer.setBounds(390, -100, 300, 300);
		// Text color is set to black
		timer.setForeground(Color.BLACK);
		// Timer label is added to the level frame
		lvl1Panel.add(timer);
		// Variables are created the set the speed of the timer
		// These variables will act as ratios
		// Ratio del/per = 1 which is one second
		int del = 1000;
		int per = 1000;
		// Ratio del/per = 10 which is one tenth of a second
		int del1 = 1000;
		int per1 = 100;
		// Timer for second is created
		t = new Timer();
		// Starting time value is 120
		i = 120;
		// Timer for tenth second is created
		t1 = new Timer();
		// Start time value is 10 and will repeat
		i1 = 10;
		
		// Starts the seconds timer and task at each tick
		t.scheduleAtFixedRate(new TimerTask() {
			// Task to be performed
			public void run() {
				// The method seti is run and set as an integer variable to print
				// on the JLabel
				secondTimer = Integer.toString(seti());
			}
		}, del, per); // del and per are passed in to set the timer speed
		// Starts the tenth seconds timer and task at each tick
		t1.scheduleAtFixedRate(new TimerTask() {
			// Task to be performed
			public void run() {
				// Because of a unknown program error where the JLabel displays
				// "null" for a split second at 120 seconds, when the seconds is 
				// 120, the JLabel prints 120
				// This will fix the error
				if (secondTimer == null) {
					secondTimer = "120";
				}
				// The method seti1 is run and set as an integer variable to print
				// on the JLabel
				timer.setText("TIME REMAINING: " + secondTimer + "." + seti1());
			}
		}, del1, per1); // del and per are passed in to set the timer speed
	}

	// Creates the method that decreases the seconds by 1 every second
	private static final int seti() {
		// Once the seconds reaches 0, the program is exited and the timer is stopped
		if (i == 0) {
			t.cancel();
			System.exit(0);
		}
		// The seconds value is returned before i subtracts 1
		return --i;
	}
	// Creates the method that decreases the tenth seconds by 10 every second
	private static final int seti1() {
		// Once the tenth seconds reaches 0, another 10 is added to repeat the time
		if (i1==0) {
			i1 = i1 + 10;
		}
		// The tenth seconds value is returned before i subtracts 1
		return --i1;
	}
	
	// Create the loadLevel method by passing in the levelNumber
	private void loadLevel(int levelNumber) {
		// Scanner is used in a try-catch statement to read the text file and build 
		// the level with icons
		try {
			// Create new Scanner variable
			Scanner inputFile = new Scanner(new File("images/level" + levelNumber + ".txt"));
			// Goes through every row
			for (int row = 0; row < boardArray.length; row++) {
				// Scanner will read every row of characters in the text file
				char[] lineArray = inputFile.next().toCharArray();
				// Goes through every column
				for (int col = 0; col < lineArray.length; col++) {
					// If a "B" is found, then a JLabel with the wall icon is created in it's place
					if (lineArray[col] == 'B')
						boardArray[row][col] = new JLabel(Icons.WALL);
					// If a "C" is found, then a JLabel with the coin icon is created in it's place
					else if (lineArray[col] == 'C')
						boardArray[row][col] = new JLabel(Icons.COIN);
					// In all other cases, empty space is added
					else
						boardArray[row][col] = new JLabel();
				}
			}
			// Scanner is closed
			inputFile.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Create the createLvlPanel method
	private void createlLvlPanel() {
		// Set the panel's layout to null so we can overlap images and place buttons in places we want
		lvl1Panel.setLayout(null);
		
		// All the newly created icons are added to the level panel
		for (int row = 0; row < boardArray.length; row++) {
			for (int col = 0; col < boardArray[0].length; col++) {
				if (boardArray[row][col].getIcon() == Icons.WALL) {
					boardArray[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvl1Panel.add(boardArray[row][col]);
				} 
				else if (boardArray[row][col].getIcon() == Icons.COIN) {
					boardArray[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvl1Panel.add(boardArray[row][col]);
				}
			}
		}
		
		// MARIO's character size and position is set on the level panel
		mario.setBounds(25, 425, 25, 25);
		// MARIO is added to the level panel
		lvl1Panel.add(mario);
		// The level panel's size is set
		lvl1Panel.setBounds(0, 0, 25 * boardArray[0].length, 25 * boardArray.length);
		// The level panel is added to the JFrame
		add(lvl1Panel);

	}

	// Create the setUpKeyBindings method
	private void setupKeyBindings() {
		// ActionMap variable is created
		ActionMap actionMap;
		// InputMap variable is created
		InputMap inputMap;
		// Newly created variable is set to the level panel's input map
		inputMap = lvl1Panel.getInputMap();
		// Newly created variable is set to the level panel's action map
		actionMap = lvl1Panel.getActionMap();
		
		// When index [0] in the String Array is pressed, it converts the action into the string "left"
		// "left" is inserted into the action map and passed into the KeyAction class where the action 
		// is performed on the character
		inputMap.put(KeyStroke.getKeyStroke(mario.getKey()[0].toCharArray()[0]), "left");
		actionMap.put("left", new KeyAction("left"));
		// When index [1] in the String Array is pressed, it converts the action into the string "right"
		// "right" is inserted into the action map and passed into the KeyAction class where the action 
		// is performed on the character
		inputMap.put(KeyStroke.getKeyStroke(mario.getKey()[1].toCharArray()[0]), "right");
		actionMap.put("right", new KeyAction("right"));
		// When index [1] in the String Array is pressed, it converts the action into the string "jump"
		// "jump" is inserted into the action map and passed into the KeyAction class where the action 
		// is performed on the character
		inputMap.put(KeyStroke.getKeyStroke(mario.getKey()[2].toCharArray()[0]), "jump");
		actionMap.put("jump", new KeyAction("jump"));
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		// Whenever a key is released, MARIO's position is updated
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			mario.setdX(0);
		}
	}
}
