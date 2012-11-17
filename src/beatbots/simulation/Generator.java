package beatbots.simulation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Generator implements Entity, MetronomeListener {
	
	private EntityManager entityManager;
	private Metronome metronome;
	private Node node;
	
	private boolean isActive;
	
	private Animation animation;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}

	public Generator(EntityManager entityManager, Metronome metronome, Node node) {
		
		super();
		
		this.entityManager = entityManager;
		this.metronome = metronome;
		this.node = node;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
		
		this.animation = new Animation(new SpriteSheet("assets/Generator.png", 32, 32), 33);
		
		this.metronome.addListener(this);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		if (!this.node.isActive()) { 
			
			this.deactivate();
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		graphics.drawAnimation(
				this.animation, 
				this.node.getPosition().getX() - this.animation.getWidth() / 2, 
				this.node.getPosition().getY() - this.animation.getHeight() / 2);
	}
	
	@Override
	public void deactivate() {
		
		this.isActive = false;
	}
	
	@Override
	public void destroy(GameContainer gameContainer) {
		
		this.metronome.removeListener(this);
	}
	
	@Override
	public void beat(int beatCount) {
		
		this.entityManager.addEntity(new Note(this.node));
	}
}
