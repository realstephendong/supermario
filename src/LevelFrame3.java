import java.awt.Color;
import java.awt.Font;
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
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.KeyStroke;

// This class has the same comments as "LevelFrame.java", however it is optimized for the MOON MARIO
// character and level. New icon is used for the walls and platforms for this theme.
// No comments will be added. 
public class LevelFrame3 extends JFrame implements KeyListener {
	// Create a new board array
	public static JLabel[][] boardArray2 = new JLabel[20][25];
	
	private JPanel lvl1Panel = new JPanel();
	// Create a new MOON MARIO character using the Character3 class
	public static Character3 moonmario = new Character3(Icons.MOONMARIO,new String[] { "a", "d", " "});
	// Create a new global score count label
	public static JLabel scoreCount2 = new JLabel ("Current score: 0");
	
	public LevelFrame3(int level) {
		playMusic();
		countdownTimer();
		scoreLabel();
		setSize(25 * boardArray2[0].length + 15, 25 * boardArray2.length + 35);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		loadLevel(level);
		createlLvlPanel();
		setupKeyBindings();
		addBackground();
		setVisible(true);
	}
	
	public void addBackground() {
		ImageIcon backgroundImage = new ImageIcon("images/spacebackground.png");
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0,0, lvl1Panel.getWidth(), lvl1Panel.getHeight());
		lvl1Panel.add(backgroundLabel);
	}
	
	public void scoreLabel() {
		scoreCount2.setBounds(40, -100, 300, 300);
		scoreCount2.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scoreCount2.setText("CURRENT SCORE: 0");
		scoreCount2.setForeground(Color.WHITE);
		lvl1Panel.add(scoreCount2);
	}

	public static void playMusic() {
		try {
			File musicPath = new File("images/spacesong.wav");

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

	static int i;
	static Timer t;
	static int i1;
	static Timer t1;
	static String secondTimer;
	
	public void countdownTimer() {
		JLabel timer = new JLabel("TIME REMAINING: 120.0");
		timer.setFont(new Font("Monospaced", Font.PLAIN, 15));
		timer.setBounds(390,-100, 300, 300);
		timer.setForeground(Color.WHITE);
		lvl1Panel.add(timer);
		int del = 1000;
		int per = 1000;
		int del1 = 1000;
		int per1 = 100;
		t = new Timer();
		i = 120;
		t1 = new Timer();
		i1 = 10;
		
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				secondTimer = Integer.toString(seti());
			}
		}, del, per);
		t1.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (secondTimer == null) {
					secondTimer = "120";
				}
				timer.setText("TIME REMAINING: " + secondTimer + "." + seti1());
			}
		}, del1, per1);
	}

	private static final int seti() {
		if (i == 0) {
			t.cancel();
			System.exit(0);
		}
		return --i;
	}
	
	private static final int seti1() {
		if (i1==0) {
			i1 = i1 + 10;
		}
		return --i1;
	}
	
	private void loadLevel(int levelNumber) {
		try {

			Scanner inputFile = new Scanner(new File("images/level" + levelNumber + ".txt"));
			for (int row = 0; row < boardArray2.length; row++) {
				char[] lineArray = inputFile.next().toCharArray();
				for (int col = 0; col < lineArray.length; col++) {
					if (lineArray[col] == 'B')
						boardArray2[row][col] = new JLabel(Icons.ROCK);
					else if (lineArray[col] == 'C')
						boardArray2[row][col] = new JLabel(Icons.COIN);
					else
						boardArray2[row][col] = new JLabel();
				}
			}
			inputFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void createlLvlPanel() {
		lvl1Panel.setLayout(null);
		lvl1Panel.setBackground(Color.BLACK);

		for (int row = 0; row < boardArray2.length; row++) {
			for (int col = 0; col < boardArray2[0].length; col++) {
				if (boardArray2[row][col].getIcon() == Icons.ROCK) {
					boardArray2[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvl1Panel.add(boardArray2[row][col]);
				} else if (boardArray2[row][col].getIcon() == Icons.COIN) {
					boardArray2[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvl1Panel.add(boardArray2[row][col]);
				}
			}
		}

		moonmario.setBounds(25, 425, 25, 25);
		lvl1Panel.add(moonmario);
		lvl1Panel.setBounds(0, 0, 25 * boardArray2[0].length, 25 * boardArray2.length);
		add(lvl1Panel);
	}

	private void setupKeyBindings() {
		ActionMap actionMap;
		InputMap inputMap;
		inputMap = lvl1Panel.getInputMap();
		actionMap = lvl1Panel.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(moonmario.getKey()[0].toCharArray()[0]), "left");
		actionMap.put("left", new KeyAction2("left"));
		inputMap.put(KeyStroke.getKeyStroke(moonmario.getKey()[1].toCharArray()[0]), "right");
		actionMap.put("right", new KeyAction2("right"));
		inputMap.put(KeyStroke.getKeyStroke(moonmario.getKey()[2].toCharArray()[0]), "jump");
		actionMap.put("jump", new KeyAction2("jump"));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			moonmario.setdX(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			moonmario.setdX(0);
		}
	}
}
