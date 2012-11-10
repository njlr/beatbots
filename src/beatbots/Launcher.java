package beatbots;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

public strictfp final class Launcher {
	
	public static void main(String[] args) throws SlickException {
		
		// Linking
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		// Start the game!
		Game game = new BeatBotsGame();
		
		AppGameContainer appGameContainer = new AppGameContainer(game);
		
		appGameContainer.setDisplayMode(640, 480, false);
		
		appGameContainer.start();
	}
}
