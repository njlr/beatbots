package beatbots;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
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
		
		BeatBotsGame beatBotsGame = new BeatBotsGame();
		
		ScalableGame scalableGame = new ScalableGame(beatBotsGame, 640, 480, true);
		
		AppGameContainer appGameContainer = new AppGameContainer(scalableGame, 640, 480, false);
		
		
		appGameContainer.setTargetFrameRate(60);
		
		appGameContainer.start();
	}
}
