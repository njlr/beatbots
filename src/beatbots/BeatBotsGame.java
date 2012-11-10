package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public strictfp final class BeatBotsGame extends StateBasedGame {

	public BeatBotsGame() {
		
		super("Beat Bots");
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		
		this.addState(new GameStatePlaying());
		
		this.enterState(GameStatePlaying.ID);
	}
}
