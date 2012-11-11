package beatbots;

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
}
