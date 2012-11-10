package beatbots;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public strictfp final class Launcher {
	
	public static void main(String[] args) throws SlickException {
		
		// Linking
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		// Start the game!
		BeatBotsGame beatBotsGame = new BeatBotsGame();
		ScalableGame scalableGame = new ScalableGame(beatBotsGame, 640, 480, true);
		
		AppGameContainer appGameContainer = new AppGameContainer(scalableGame);
		
		appGameContainer.setDisplayMode(640, 480, false);
		
		appGameContainer.start();
	}
}
