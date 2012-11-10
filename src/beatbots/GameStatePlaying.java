package beatbots;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import beatbots.simulation.BeatBot;
import beatbots.simulation.BeatMachine;

public class GameStatePlaying extends BasicGameState {
	
	private BeatMachine beatMachine;
	
	private List<BeatBot> beatBots;

	public GameStatePlaying() {
		
		super();
		
		this.beatMachine = new BeatMachine();
		
		this.beatBots = new ArrayList<BeatBot>();
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		this.beatMachine.init(gameContainer);
		
		for (BeatBot i : this.beatBots) {
			
			i.init(gameContainer);
		}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int delta) throws SlickException {
		
		this.beatMachine.update(gameContainer, delta);
		
		for (BeatBot i : this.beatBots) {
			
			i.update(gameContainer, delta);
		}
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		for (BeatBot i : this.beatBots) {
			
			i.render(gameContainer, graphics);
		}
	}

	@Override
	public int getID() {
		
		return ID;
	}
	
	public static final int ID = 0;
}
