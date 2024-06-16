// Import all required classes
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.text.TextAction;

// Create the class that will store all the functions for the buttons pressed
public class KeyAction extends TextAction {
	private String key;

	// Constructor method for variable key
	public KeyAction(String key) {
		super(key);
		this.key = key;
	}

	// When a button is pressed...
	public void actionPerformed(ActionEvent e) {
		// A new Character class is created named MARIO
		Character mario = LevelFrame.mario;
		// If the button pressed is "a" which is index [0] of the String array we 
		// passed in and MARIO is not touching a wall...
		if (e.getActionCommand().equals(mario.getKey()[0])
				&& LevelFrame.boardArray[mario.getRow()][mario.getCol()].getIcon() != Icons.WALL)
			{
			// MARIO moves left
			mario.moveLeft();
			// MARIO's icon is set to face right
			mario.setIcon(Icons.MARIO[0]);
			}
		
		// If the button pressed is "d" which is index [1] of the String array we 
		// passed in and MARIO is not touching a wall...
		else if (e.getActionCommand().equals(mario.getKey()[1])
				&& LevelFrame.boardArray[mario.getRow()][mario.getCol()].getIcon() != Icons.WALL)
			{
			// MARIO moves right
			mario.moveRight();
			// MARIO's icon is set to face right
			mario.setIcon(Icons.MARIO[1]);
			}
		// If the button pressed is " " which is index [2] of the String array we 
		// passed in and MARIO is not touching a wall...
		else if (e.getActionCommand().equals(mario.getKey()[2])
				&& LevelFrame.boardArray[mario.getRow() - 1][mario.getCol()].getIcon() != Icons.WALL)
			// If MARIO isn't already jumping...
			if (!mario.isJumping()) {
				// MARIO is set to jumping
				mario.setJumping(true);
				// MARIO jumps
				mario.jump();
			}
	}
}
