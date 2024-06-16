// This class is almost the same as "Character.java", only
// extra comments regarding changes to movement/physics will be added
import java.awt.Color;
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

public class Character3 extends JLabel implements ActionListener {
	private ImageIcon[] iconArray; 
	private ImageIcon icon;
	private String[] key;
	private int dX, dY;
	// Jump timer variable is increased from 25 to 70 to slow down 
	// the character's movements stimulating moon gravity
	private Timer jumpTimer = new Timer(70, this);
	private int jumpCounter;
	private boolean jumping = false;
	// Fall timer variable is increased from 25 to 70 to slow down 
	// the character's movements stimulating moon gravity
	private Timer fallTimer = new Timer(70, this);
	private int fallCounter;
	private boolean falling = false;
	static int coinCounter1 = 0;
	// 2 new global variables are made to check if the character is moving
	// left or right
	private boolean movingLeft = false;
	private boolean movingRight = false;
	
	public Character3(ImageIcon[] iconArray, String[] key) {
		super();
		// Icon is set to MOON MARIO
		icon = Icons.MOONMARIO[1];
		setIcon(icon);
		setIconArray(iconArray);
		this.key = key;
	}

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

	public void jump() {
		jumping = true;
		jumpCounter = 0;
		jumpTimer.start();
		try {
			File jumpSound = new File("images/jump.wav");
			AudioInputStream jumpClip = AudioSystem.getAudioInputStream(jumpSound);
			Clip clip1 = AudioSystem.getClip();
			clip1.open(jumpClip);
			clip1.start();
		} catch (Exception e) {
			System.out.println("Can't find file.");
		}
	}
	
	public void fall() {
		falling = true;
		fallCounter = 0;
		fallTimer.start();
	}
	
	public void moveRight() {
		// Movement is decreased from 4 to 2 so character walks slower
		// simulating a heavy moon suit that makes walking more difficult
		dX = 2;
		// The character is moving right
		movingRight = true;
		// The character is not moving left
		movingLeft = false;
		if (LevelFrame3.boardArray2[getRow()-1][getCol()].getIcon() != Icons.ROCK && jumping) {
			fall();
		}
		setBounds(getX() + dX, getY(), 25, 25);
		collectCoin();
	}

	public void moveLeft() {
		// Movement is decreased from 4 to 2 so character walks slower
		// simulating a heavy moon suit that makes walking more difficult
		dX = -2;
		// The character is not moving right
		movingRight = false;
		// The character is moving left
		movingLeft = true;
		if (LevelFrame3.boardArray2[getRow()-1][getCol()].getIcon() != Icons.ROCK && jumping) {
			fall();
		}
		setBounds(getX() + dX, getY(), 25, 25);
		collectCoin();
	}

	public int getRow() {
		return (int) getY() / 25;
	}

	public int getCol() {
		return (int) getX() / 25;

	}

	public void collectCoin() {
		int row = getRow();
		int col = getCol();
		if (LevelFrame3.boardArray2[row][col].getIcon() == Icons.COIN) {
			LevelFrame3.boardArray2[row][col].setIcon(null);
			try {
				File coinSound = new File("images/coinsound.wav");
				AudioInputStream coin = AudioSystem.getAudioInputStream(coinSound);
				Clip clip2 = AudioSystem.getClip();
				clip2.open(coin);
				clip2.start();
				
			} catch (Exception e) {
				System.out.println("Can't find file.");
			}
			coinCounter1+=100;
			LevelFrame3.scoreCount2.setForeground(Color.WHITE);
			LevelFrame3.scoreCount2.setFont(new Font("Monospaced", Font.PLAIN, 15));
			LevelFrame3.scoreCount2.setText("CURRENT SCORE: " + coinCounter1);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (jumping && dY < 0 && LevelFrame3.boardArray2[getRow() - 1][getCol()].getIcon() == Icons.ROCK) {
			jumping = false;
			dY = 0;
			jumpTimer.stop();
			fall();
			return;
		}
		if (falling && dY > 0) {
			int nextRow = getRow() + 1;
			if (nextRow >= LevelFrame3.boardArray2.length
					|| LevelFrame3.boardArray2[nextRow][getCol()].getIcon() == Icons.ROCK) {
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
		}

		if (jumping) {
			// If the character is jumping the jumping parabola should open up more
			// simulating what jumping is like on the moon. 
			// to do this, the character needs to move horizontally faster
			if (movingRight)
				// If the character is moving right, then move 8 units to the right
				// at a time
				dX = 8;
			else if (movingLeft) {
				// If the character is moving left, then move 8 units to the right
				// at a time
				dX = -8;
			}
			jumpCounter++;
			// Because the character needs to jump higher to stimulate moon gravity,
			// we must increase the number of units the character goes up and down
			// at a time
			if (jumpCounter <= 10)
				// Increased from 5 to 12 units at a time
				dY = -12;
			else if (jumpCounter <= 18)
				// Increased from 5 to 12 units at a time
				dY = 12;
			else {
				jumping = false;
				dY = 0;
				jumpTimer.stop();
				fall();
			}
		}

		else if (falling) {
			if (movingRight)
				dX = 8;
			else if (movingLeft) {
				dX = -8;
			}
			fallCounter++;
			// The character should fall at the same speed as it jumped to balance it out
			// The character goes down 12 units at a time
			dY = 12;
			if (LevelFrame3.boardArray2[getRow() + 1][getCol()].getIcon() == Icons.ROCK) {
				falling = false;
				dY = 0;
				fallTimer.stop();
			} else if (fallCounter >= 20) {
				falling = false;
				dY = 0;
				fallTimer.stop();
			}
			setBounds(getX(), getY() + dY, 25, 25); 
			return;
		}

		if (LevelFrame3.boardArray2[getRow()][getCol() + 1].getIcon() == Icons.ROCK && dX > 0) {
			dX = 0;
		} else if (LevelFrame3.boardArray2[getRow()][getCol() + 1].getIcon() == Icons.ROCK && dX < 0) {
			dX = 0;
		} else if (LevelFrame3.boardArray2[getRow()][getCol() + 1].getIcon() == Icons.ROCK && dY < 0) {
			dY = 0;
		} else if (LevelFrame3.boardArray2[getRow()][getCol() + 1].getIcon() == Icons.ROCK && dY > 0) {
			dY = 0;
		}

		collectCoin();

		setBounds(getX() + dX, getY() + dY, 25, 25);

	}
}
