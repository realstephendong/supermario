// THIS CLASS HAS THE SAME COMMENTS AS "KeyAction.java" but this one 
// is made for the LUIGI character and theme
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.text.TextAction;

public class KeyAction1 extends TextAction {
	private String key1;

	public KeyAction1(String key1) {
		super(key1);
		this.key1 = key1;
	}

	public void actionPerformed(ActionEvent e) {
		
		Character2 luigi = LevelFrame2.luigi;
		if (e.getActionCommand().equals(luigi.getKey()[0])
				&& LevelFrame2.boardArray1[luigi.getRow()][luigi.getCol()].getIcon() != Icons.BLOCK)
			{ luigi.moveLeft();
			luigi.setIcon(Icons.LUIGI[0]);
			}

		else if (e.getActionCommand().equals(luigi.getKey()[1])
				&& LevelFrame2.boardArray1[luigi.getRow()][luigi.getCol()].getIcon() != Icons.BLOCK)
			{ luigi.moveRight();
			luigi.setIcon(Icons.LUIGI[1]);
			}

		else if (e.getActionCommand().equals(luigi.getKey()[2])
				&& LevelFrame2.boardArray1[luigi.getRow() - 1][luigi.getCol()].getIcon() != Icons.BLOCK)
			if (!luigi.isJumping()) {
				luigi.setJumping(true);
				luigi.jump();
			}
	}
}
