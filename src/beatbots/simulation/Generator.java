package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Generator extends Node implements MetronomeListener {
	
	private EntityManager entityManager;
	private Metronome metronome;

	public Generator(Vector2f position, Node successor, EntityManager entityManager, Metronome metronome) {
		
		super(position, successor);
		
		this.entityManager = entityManager;
		this.metronome = metronome;
	}

	public Generator(Vector2f position, EntityManager entityManager, Metronome metronome) {
		
		super(position);
		
		this.entityManager = entityManager;
		this.metronome = metronome;
	}
	
	@Override
	public void init(GameContainer container) {
		
		super.init(container);
		
		this.metronome.addListener(this);
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		super.render(container, graphics);
		
		graphics.setColor(Color.green);
		
		graphics.drawRect(
				this.getPosition().getX() - 4f,  
				this.getPosition().getY() - 4f, 
				8f, 
				8f);
	}
	
	@Override
	public void beat(int beatCount) {
		
		this.entityManager.addEntity(new Note(this));
	}

	@Override
	public void bar() {
		
	}
}
