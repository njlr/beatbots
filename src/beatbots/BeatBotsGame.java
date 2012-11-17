package beatbots;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public strictfp final class BeatBotsGame extends StateBasedGame {

	public BeatBotsGame() {
		
		super("Beat Bots");
		
		
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		
		
	}
	
	public static void main(String[] args) throws SlickException {
		
		BeatBotsGame beatBotsGame = new BeatBotsGame();
		
		ScalableGame scalableGame = new ScalableGame(beatBotsGame, 640, 480, true);
		
		AppGameContainer appGameContainer = new AppGameContainer(scalableGame, 640, 480, false);
		
		appGameContainer.start();
	}
}
