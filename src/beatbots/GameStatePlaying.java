package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import beatbots.simulation.BeatMachine;
import beatbots.simulation.Ship;

public class GameStatePlaying extends BasicGameState {
	
	private BeatMachine beatMachine;
	private Ship ship;

	public GameStatePlaying() {
		
		super();
		
		this.beatMachine = new BeatMachine();
		this.ship = new Ship();
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		this.beatMachine.init(gameContainer);
		this.ship.init(gameContainer);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		this.beatMachine.update(gameContainer, delta);
		this.ship.update(gameContainer, delta);
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		this.ship.render(gameContainer, graphics);
	}

	@Override
	public int getID() {
		
		return ID;
	}
	
	public static final int ID = 0;
}
