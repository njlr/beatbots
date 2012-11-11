package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import beatbots.simulation.BeatBotManager;
import beatbots.simulation.BeatMachine;
import beatbots.simulation.BeatQueue;
import beatbots.simulation.BeatTokenManager;
import beatbots.simulation.BulletManager;
import beatbots.simulation.Ship;

public class GameStatePlaying extends BasicGameState {
	
	private BeatMachine beatMachine;
	
	private BeatQueue beatQueue;
	
	private BeatTokenManager beatTokenManager;
	
	private BulletManager bulletManager;
	
	private BeatBotManager beatBotManager;
	
	private Ship ship;

	public GameStatePlaying() {
		
		super();
		
		this.beatMachine = new BeatMachine();
		this.beatQueue = new BeatQueue();
		
		this.bulletManager = new BulletManager();
		
		this.beatBotManager = new BeatBotManager(this.beatMachine, this.bulletManager, "assets/maps/Test.tmx");
		
		this.ship = new Ship(this.beatQueue, this.bulletManager);
		
		this.beatTokenManager = new BeatTokenManager(this.ship);
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		this.beatMachine.init(gameContainer);
		
		this.beatQueue.init(gameContainer);
		
		this.beatTokenManager.init(gameContainer);
		
		this.bulletManager.init(gameContainer);
		
		this.beatBotManager.init(gameContainer);
		
		this.ship.init(gameContainer);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		this.beatMachine.update(gameContainer, delta);
		
		this.beatQueue.update(gameContainer, delta);
		
		this.beatTokenManager.update(gameContainer, delta);
		
		this.bulletManager.update(gameContainer, delta);
		
		this.beatBotManager.update(gameContainer, delta);
		
		this.ship.update(gameContainer, delta);
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		this.bulletManager.render(gameContainer, graphics);
		
		this.beatTokenManager.render(gameContainer, graphics);
		
		this.beatQueue.render(gameContainer, graphics);
		
		this.beatBotManager.render(gameContainer, graphics);
		
		this.ship.render(gameContainer, graphics);
	}

	@Override
	public int getID() {
		
		return ID;
	}
	
	public static final int ID = 0;
}
