// THIS CLASS HAS THE SAME COMMENTS AS "Character.java" but this one 
// is made for the LUIGI character and theme
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

public class Character2 extends JLabel implements ActionListener {
	private ImageIcon[] iconArray; 
	private ImageIcon icon;
	private String[] key;
	private int dX, dY;
	private Timer jumpTimer = new Timer(25, this);
	private int jumpCounter;
	private boolean jumping = false;
	private Timer fallTimer = new Timer(25, this);
	private int fallCounter;
	private boolean falling = false;
	static int coinCounter1 = 0;
	
	public Character2(ImageIcon[] iconArray, String[] key) {
		super();
		// Icon is set to LUIGI
		icon = Icons.LUIGI[1];
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
		dX = 4;
		if (LevelFrame2.boardArray1[getRow()-1][getCol()].getIcon() != Icons.BLOCK && jumping) {
			fall();
		}
		setBounds(getX() + dX, getY(), 25, 25);
		collectCoin();
	}

	public void moveLeft() {
		dX = -4;
		if (LevelFrame2.boardArray1[getRow()-1][getCol()].getIcon() != Icons.BLOCK && jumping) {
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
		if (LevelFrame2.boardArray1[row][col].getIcon() == Icons.COIN) {
			LevelFrame2.boardArray1[row][col].setIcon(null);
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
			LevelFrame2.scoreCount1.setForeground(Color.WHITE);
			LevelFrame2.scoreCount1.setFont(new Font("Monospaced", Font.PLAIN, 15));
			LevelFrame2.scoreCount1.setText("CURRENT SCORE: " + coinCounter1);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (jumping && dY < 0 && LevelFrame2.boardArray1[getRow() - 1][getCol()].getIcon() == Icons.BLOCK) {
			jumping = false;
			dY = 0;
			jumpTimer.stop();
			fall();
			return;
		}
		if (falling && dY > 0) {
			int nextRow = getRow() + 1;
			if (nextRow >= LevelFrame2.boardArray1.length
					|| LevelFrame2.boardArray1[nextRow][getCol()].getIcon() == Icons.BLOCK) {
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
		}
		if (jumping) {
			jumpCounter++;
			if (jumpCounter <= 10)
				dY = -5;
			else if (jumpCounter <= 18)
				dY = 5;
			else {
				jumping = false;
				dY = 0;
				jumpTimer.stop();
				fall();
			}
		}
		else if (falling) {
			fallCounter++;
			dY = 5;
			if (LevelFrame2.boardArray1[getRow() + 1][getCol()].getIcon() == Icons.BLOCK) {
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

		if (LevelFrame2.boardArray1[getRow()][getCol() + 1].getIcon() == Icons.BLOCK && dX > 0) {
			dX = 0;
		} else if (LevelFrame2.boardArray1[getRow()][getCol() + 1].getIcon() == Icons.BLOCK && dX < 0) {
			dX = 0;
		} else if (LevelFrame2.boardArray1[getRow()][getCol() + 1].getIcon() == Icons.BLOCK && dY < 0) {
			dY = 0;
		} else if (LevelFrame2.boardArray1[getRow()][getCol() + 1].getIcon() == Icons.BLOCK && dY > 0) {
			dY = 0;
		}

		collectCoin();

		setBounds(getX() + dX, getY() + dY, 25, 25);

	}
}
