package beatbots;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import beatbots.simulation.BeatBot;
import beatbots.simulation.BeatMachine;
import beatbots.simulation.BeatMap;
import beatbots.simulation.BotIndex;

public class GameStatePlaying extends BasicGameState {
	
	private BeatMap beatMap;
	
	private BeatMachine beatMachine;
	
	private List<BeatBot> beatBots;

	public GameStatePlaying() {
		
		super();
		
		this.beatMap = new BeatMap("assets/maps/Test.tmx");
		
		this.beatMachine = new BeatMachine();
		
		this.beatBots = new ArrayList<BeatBot>();
		
		Vector2f center = new Vector2f(640f / 2f, 480f / 2f);
		
		this.beatBots.add(new BeatBot(this.beatMachine, BotIndex.One, center.copy().add(new Vector2f(-64f, 0f))));
		this.beatBots.add(new BeatBot(this.beatMachine, BotIndex.Two, center.copy().add(new Vector2f(0f, 0f))));
		this.beatBots.add(new BeatBot(this.beatMachine, BotIndex.Three, center.copy().add(new Vector2f(64f, 0f))));
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		this.beatMap.init(gameContainer);
		
		this.beatMachine.init(gameContainer);
		
		for (BeatBot i : this.beatBots) {
			
			i.init(gameContainer);
		}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		this.beatMap.update(gameContainer, delta);
		
		this.beatMachine.update(gameContainer, delta);
		
		for (BeatBot i : this.beatBots) {
			
			i.update(gameContainer, delta);
		}
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		graphics.setBackground(new Color(100, 149, 237));
		
		this.beatMap.render(gameContainer, graphics);
		
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
