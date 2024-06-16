// Import all required classes
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.sound.sampled.*;

// Create the character class for the MARIO theme
public class Character extends JLabel implements ActionListener {
	
	// Create the variables to be used
	// Stores the character icons to be passed in
	private ImageIcon[] iconArray; 
	// Displays the icon when the level is first opened
	private ImageIcon icon;
	private String[] key;
	private int dX, dY;
	private Timer jumpTimer = new Timer(25, this);
	private int jumpCounter;
	private boolean jumping = false;
	private Timer fallTimer = new Timer(25, this);
	private int fallCounter;
	private boolean falling = false;
	public static int coinCounter = 0;

	// Create the constructor method
	public Character(ImageIcon[] iconArray, String[] key) {
		super();
		// Icon is set to MARIO when the level first opens
		icon = Icons.MARIO[1];
		setIcon(icon);
		// After, when the user moves the character around, it will change 
		// according to the direction key press
		setIconArray(iconArray);
		this.key = key;

	}
	// Create getters and setters
	public ImageIcon[] getIconArray() {
		return iconArray;
	}

	public void setIconArray(ImageIcon[] iconArray) {
		this.iconArray = iconArray;
	}

	public String[] getKey() {
		return key;
	}

	public void setKey(String[] key) {
		this.key = key;
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

	public Timer getJumpTimer() {
		return jumpTimer;
	}

	public void setJumpTimer(Timer jumpTimer) {
		this.jumpTimer = jumpTimer;
	}

	public int getJumpCounter() {
		return jumpCounter;
	}

	public void setJumpCounter(int jumpCounter) {
		this.jumpCounter = jumpCounter;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public Timer getFallTimer() {
		return fallTimer;
	}

	public void setFallTimer(Timer fallTimer) {
		this.fallTimer = fallTimer;
	}

	public int getFallCounter() {
		return fallCounter;
	}

	public void setFallCounter(int fallCounter) {
		this.fallCounter = fallCounter;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	// Utility methods begin here --------------------------------
	// Create the method for jumping
	public void jump() {
		// When jump is pressed...
		jumping = true;
		jumpCounter = 0;
		jumpTimer.start();
		// A jump sound is played using a try-catch statement
		try {
			File jumpSound = new File("images/jump.wav");
			AudioInputStream jumpClip = AudioSystem.getAudioInputStream(jumpSound);
			Clip clip1 = AudioSystem.getClip();
			clip1.open(jumpClip);
			clip1.start();
		}
		// If the file is not found, the computer prints "Can't find file."
		catch (Exception e) {
			System.out.println("Can't find file.");
		}
	}
	
	// Method for when the character is falling
	public void fall() {
		falling = true;
		fallCounter = 0;
		fallTimer.start();
	}

	// Create the method for moving right
	public void moveRight() {
		// Character moves 4 units to the right
		dX = 4;
		// If the character touches a wall while moving right and he is jumping then he falls
		if (LevelFrame.boardArray[getRow()-1][getCol()].getIcon() != Icons.WALL && jumping) {
			fall();
		}
		// The character's new position is set
		setBounds(getX() + dX, getY(), 25, 25);
		// If the character passes a coin while moving right it runs the collect coin method
		collectCoin();
	}

	// Same as method for moving right
	public void moveLeft() {
		dX = -4;
		if (LevelFrame.boardArray[getRow()-1][getCol()].getIcon() != Icons.WALL && jumping) {
			fall();
		}
		setBounds(getX() + dX, getY(), 25, 25);
		collectCoin();
	}
	
	// Method for getting the row that the character is currently in
	public int getRow() {
		return (int) getY() / 25;
	}

	// Method for getting the column that the character is currently in
	public int getCol() {
		return (int) getX() / 25;
	}

	// Method for collecting a coin
	public void collectCoin() {
		// The row and column that the character is in is stored as variables
		int row = getRow();
		int col = getCol();
		// If the character is touching a coin...
		if (LevelFrame.boardArray[row][col].getIcon() == Icons.COIN) {
			// Coin is removed from the board
			LevelFrame.boardArray[row][col].setIcon(null);
			// Coin collected sound is played using try-catch statement
			try {
				File coinSound = new File("images/coinsound.wav");
				AudioInputStream coin = AudioSystem.getAudioInputStream(coinSound);
				Clip clip2 = AudioSystem.getClip();
				clip2.open(coin);
				clip2.start();
				
			} catch (Exception e) {
				System.out.println("Can't find file.");
			}
			// The global variable coinCounter goes up by 100
			coinCounter+=100;
			// Font is set for the global label
			LevelFrame.scoreCount.setFont(new Font("Monospaced", Font.PLAIN, 15));
			// The label is updated inside the level to display the current score
			LevelFrame.scoreCount.setText("CURRENT SCORE: " + coinCounter);
		}
	}

	// This method checks for collisions and runs each required method when 
	// a button is pressed
	public void actionPerformed(ActionEvent e) {
		// Accounts for when character jumps up and hits something
		if (jumping && dY < 0 && LevelFrame.boardArray[getRow() - 1][getCol()].getIcon() == Icons.WALL) {
			// Character is not jumping anymore and starts falling
			jumping = false;
			dY = 0;
			jumpTimer.stop();
			fall();
			// Method is exited
			return;
		}
		// Accounts for when the character jumps off a platform and lands on the ground
		// or when character is falling
		if (falling && dY > 0) {
			int nextRow = getRow() + 1;
			if (nextRow >= LevelFrame.boardArray.length
					|| LevelFrame.boardArray[nextRow][getCol()].getIcon() == Icons.WALL) {
				// When the character touches the ground, it is not falling anymore
				falling = false;
				dY = 0;
				fallTimer.stop();
				// Method is exited
				return;
			}
		}
		// Accounts for when the character jumps on the spot
		if (jumping) {
			// Jump counter variable increments by one for each tick of the timer
			jumpCounter++;
			if (jumpCounter <= 10) // When <= 10, the character is going up
				// Character moves up 5 units up at a time
				dY = -5;
			else if (jumpCounter <= 18) // When <= 18, the character is going down
				// Character moves down 5 units at a time
				dY = 5;
			// Accounts for other cases and tells character to fall
			else {
				jumping = false;
				dY = 0;
				jumpTimer.stop();
				fall();
			}
		}
		// Accounts for when the character is falling
		else if (falling) {
			// Fall counter variable increments by one for each tick of the timer
			fallCounter++;
			dY = 5; // Character moves down 5 units at a time
			// Accounts for any collision with the ground
			if (LevelFrame.boardArray[getRow() + 1][getCol()].getIcon() == Icons.WALL) {
				falling = false;
				dY = 0;
				fallTimer.stop();
			} else if (fallCounter >= 20) { // The character stops falling after reaching the ground
				falling = false;
				dY = 0;
				fallTimer.stop();
			}
			// The character's position is updated after finishing falling
			setBounds(getX(), getY() + dY, 25, 25);
			// Method is exited
			return;
		}

		// If the character touches a wall while moving right
		if (LevelFrame.boardArray[getRow()][getCol() + 1].getIcon() == Icons.WALL && dX > 0) {
			dX = 0;
		}
		// If the character touches a wall while moving left
		else if (LevelFrame.boardArray[getRow()][getCol() + 1].getIcon() == Icons.WALL && dX < 0) {
			dX = 0;
		}
		// If the character touches a wall while jumping up
		else if (LevelFrame.boardArray[getRow()][getCol() + 1].getIcon() == Icons.WALL && dY < 0) {
			dY = 0;
		}
		// If the character touches a wall while falling down
		else if (LevelFrame.boardArray[getRow()][getCol() + 1].getIcon() == Icons.WALL && dY > 0) {
			dY = 0;
		}

		// Method checks for if any coins have been collected in the jump
		collectCoin();

		// The character's position is updated
		setBounds(getX() + dX, getY() + dY, 25, 25);

	}
}
