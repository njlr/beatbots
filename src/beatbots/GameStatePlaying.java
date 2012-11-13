package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public strictfp final class GameStatePlaying extends BasicGameState {
	
	public static final int ID = 0;
	
	private EntityManager entityManager;
	
	public GameStatePlaying() {
		
		super();
		
		this.entityManager = new EntityManager();
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		
		super.enter(container, game);
		
		
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		
		super.leave(container, game);
		
		
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		BulletManager.init(this.entityManager);
		
		Metronome metronome = new Metronome();
		
		this.entityManager.addEntity(metronome);
		
		SequenceManager sequenceManager = new SequenceManager(metronome);
		
		this.entityManager.addEntity(sequenceManager);
		this.entityManager.addEntity(new Ship(sequenceManager));
		this.entityManager.addEntity(new BeatBot(new Vector2f(128f, 128f)));
		this.entityManager.addEntity(new BeatBotColored(new Vector2f(160f, 128f), this.entityManager, Beat.Red));
		
		this.entityManager.init(gameContainer);
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		
		this.entityManager.update(gameContainer, delta);
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		
		this.entityManager.render(gameContainer, graphics);
	}

	@Override
	public int getID() {
		
		return ID;
	}
}
