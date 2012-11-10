package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import beatbots.simulation.BeatMachine;

public class GameStatePlaying extends BasicGameState {
	
	private BeatMachine beatMachine;

	public GameStatePlaying() {
		
		super();
		
		this.beatMachine = new BeatMachine();
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		this.beatMachine.init(gameContainer);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		this.beatMachine.update(gameContainer, delta);
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		
	}

	@Override
	public int getID() {
		
		return ID;
	}
	
	public static final int ID = 0;
}
