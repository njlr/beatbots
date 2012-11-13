package beatbots;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public strictfp final class BeatBotsGame extends StateBasedGame {

	public BeatBotsGame() {
		
		super("Beat Bots");
		
		this.addState(new GameStatePlaying());
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		
		this.enterState(GameStatePlaying.ID);
	}
	
	public static void main(String[] args) throws SlickException {
		
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		BeatBotsGame game = new BeatBotsGame();
		
		AppGameContainer appGameContainer = new AppGameContainer(game);
		
		appGameContainer.setDisplayMode(640, 480, false);
		
		appGameContainer.start();
	}
}
