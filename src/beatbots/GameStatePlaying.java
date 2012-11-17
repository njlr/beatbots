package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import beatbots.simulation.BeatBotsMap;
import beatbots.simulation.EntityManager;


public strictfp final class GameStatePlaying extends BasicGameState {
	
	public static final int ID = 0;
	
	private EntityManager entityManager;
	
	public GameStatePlaying() {
		
		super();
		
		this.entityManager = new EntityManager();
		
		this.entityManager.addEntity(new BeatBotsMap(this.entityManager, "assets/maps/Test.tmx"));
	}

	@Override
	public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
		
		this.entityManager.init(container);
	}

	@Override
	public void update(GameContainer container, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		this.entityManager.update(container, delta);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		this.entityManager.render(container, graphics);
	}

	@Override
	public int getID() {
		
		return ID;
	}
}
