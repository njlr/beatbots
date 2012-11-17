package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Generator implements Entity, MetronomeListener {
	
	private EntityManager entityManager;
	private Metronome metronome;
	private Node node;
	
	private boolean isActive;
	
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
	public void init(GameContainer container) {
		
		this.isActive = true;
		
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
		
		graphics.setColor(Color.green);
		
		graphics.drawOval(
				this.node.getPosition().getX() - 4f, 
				this.node.getPosition().getY() - 4f, 
				8f, 
				8f);
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
