// Import only ImageIcon class
import javax.swing.ImageIcon;

// This class creates all the icons for the game
public class Icons {
	
	// Creates the bricks for the map
	public static final ImageIcon WALL = new ImageIcon("images/wall.png");

	// Creates a coin gif for the MARIO theme maps
	public static final ImageIcon COIN = new ImageIcon("images/coin.gif");
	// Creates a lucky block icon for LUIGI theme maps
	public static final ImageIcon BLOCK = new ImageIcon("images/luckyBlock.png");
	// Creates a rock icon for the MOON MARIO level
	public static final ImageIcon ROCK = new ImageIcon("images/rock.png");

	// An array that stores the MARIO icons for the map facing both right and left
	public static final ImageIcon[] MARIO = {new ImageIcon("images/marioleft.png"), new ImageIcon("images/marioright.png")};
	
	// An array that stores the LUIGI icons for the map facing both right and left
	public static final ImageIcon[] LUIGI = {new ImageIcon("images/luigileft.png"), new ImageIcon("images/luigiright.png")};
	
	// An array that stores the MOON MARIO icons for the map facing both right and left
	public static final ImageIcon[] MOONMARIO = {new ImageIcon("images/moonmarioleft.png"), new ImageIcon("images/moonmarioright.png")};
}