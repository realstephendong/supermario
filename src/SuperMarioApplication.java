/* Stephen Dong
 * Solo Software Development Project 
 * SuperMario
 * 
 * DUE DATE: May 28, 2023, 11:59 PM
 * 
 * ICS3U1-04/05 Mrs. Biswas
 * 
 * DESCRIPTION:
 * This is a video game where a character, represented by the
 * iconic mario, luigi, and a SPECIAL character 
 * (this is a knock-off, this is a dupe, catfish) can
 * run through a selection of levels and collect coins.
 * 
 * A time limit is set every level where the player must collect all
 * coins before the timer ends. As the level number goes up, the harder 
 * the level and the more tight the time becomes to complete the level.
 * 
 * The player can also select between themes; using MARIO during the 
 * day time or using LUIGI during the night time.
 * 
 * A secret bonus to the game can be found by clicking the rocket button 
 * in the corner of the screen where the player can play as ASTRONAUT MARIO
 * with a whole new range of movement features, physics, and challenges to 
 * overcome.
 *
 * This class serves as the entry point for the game. It creates an
 * instance of the title frame which has buttons that lead the user
 * to the actual gameplay.
 * Essentially this opens the title screen which opens the game window.
 * 
 * Skills used: Arrays, methods and classes, Java timer, GUI, Swing GUI components, 
 * photo editing/resizing, reading keyboard input, Scanner class, and 
 * ImageIcon implementation.
 * 
 * BASIC FEATURES ADDED: 
 * 	Get player image to Face the Proper Direction as they move
 * 	Add Timing
 * 	Add more accurate timing (tenth of a second)
 * 	Add Music
 * 	Add Sound Effects
 * 	Add Different Board Themes - user can select from different themes
 * 	Add title frame
 * 	Add a new level
 * 	Add more characters
 * 	9 basic features = 95%
 * 
 * ADVANCED FEATURES ADDED:
 * 	Add a level where the physics of movement are different
 * 	9 basic features + 1 advanced feature = 100%
 * 
 * AREAS OF CONVERN:
 * Player movement has not been changed drastically. There might still be many
 * errors within the movement of the character such as phasing into platforms
 * or getting stuck in walls.
 * 
 * CONTRIBUTION TO ASSIGNMENT:
 * 100% completed by me with help from sources.
 * 
 * SOURCES:
 * https://www.youtube.com/watch?v=wJO_cq5XeSA
 * https://www.educba.com/java-countdown-timer/
 * 
*/
public class SuperMarioApplication {
	// A new title screen is created which starts the entire program
	public static void main(String[] args) {
		MarioTitle frame = new MarioTitle();
	}
}
