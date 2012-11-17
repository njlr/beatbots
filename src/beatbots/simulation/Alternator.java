package beatbots.simulation;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Alternator implements Entity {
	
	private static final float RADIUS = 16f;
	private static final float RADIUS_SQUARED = RADIUS * RADIUS;
	
	private EntityManager entityManager;
	
	private Vector2f position;
	
	private boolean isActive;
	
	private Set<Note> notesSeen;
	
	private boolean shouldDestroyNext;
	
	private Animation animation;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public Alternator(EntityManager entityManager, Vector2f position) {
		
		super();
		
		this.entityManager = entityManager;
		
		this.position = position;
		
		this.notesSeen = new HashSet<Note>();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
		
		this.notesSeen.clear();
		
		this.shouldDestroyNext = true;
		
		this.animation = new Animation(new SpriteSheet("assets/Alternator.png", 32, 32), 1);
		
		this.animation.setAutoUpdate(false);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		for (Entity i : this.entityManager.getEntities()) {
			
			if (i instanceof Note) {
				
				Note note = (Note) i;
				
				if (this.position.distanceSquared(note.getPosition()) < RADIUS_SQUARED) {
				
					if (!this.notesSeen.contains(note)) {
						
						if (this.shouldDestroyNext) {
							
							note.deactivate();
						}
						
						this.shouldDestroyNext = !this.shouldDestroyNext;
						
						this.notesSeen.add(note);
					}
				}
				else {
					
					if (this.notesSeen.contains(note)) {
						
						this.notesSeen.remove(note);
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		if (this.shouldDestroyNext) {
			
			this.animation.setCurrentFrame(0);
		}
		else {
			
			this.animation.setCurrentFrame(1);
		}
		
		graphics.drawAnimation(
				this.animation, 
				this.position.getX() - this.animation.getWidth() / 2, 
				this.position.getY() - this.animation.getHeight() / 2);
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
}
