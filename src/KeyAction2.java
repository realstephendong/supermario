// THIS CLASS HAS THE SAME COMMENTS AS "KeyAction.java" but this one 
// is made for the MOON MARIO character and level
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.text.TextAction;

public class KeyAction2 extends TextAction {
	private String key2;

	public KeyAction2(String key2) {
		super(key2);
		this.key2 = key2;
	}
	
	public void actionPerformed(ActionEvent e) {
		Character3 moonmario = LevelFrame3.moonmario;
		if (e.getActionCommand().equals(moonmario.getKey()[0])
				&& LevelFrame3.boardArray2[moonmario.getRow()][moonmario.getCol()].getIcon() != Icons.BLOCK)
			{ moonmario.moveLeft();
			moonmario.setIcon(Icons.MOONMARIO[0]);
			}

		else if (e.getActionCommand().equals(moonmario.getKey()[1])
				&& LevelFrame3.boardArray2[moonmario.getRow()][moonmario.getCol()].getIcon() != Icons.BLOCK)
			{ moonmario.moveRight();
			moonmario.setIcon(Icons.MOONMARIO[1]);
			}
		
		else if (e.getActionCommand().equals(moonmario.getKey()[2])
				&& LevelFrame3.boardArray2[moonmario.getRow() - 1][moonmario.getCol()].getIcon() != Icons.BLOCK)
			if (!moonmario.isJumping()) {
				moonmario.setJumping(true);
				moonmario.jump();
			}
	}
}
