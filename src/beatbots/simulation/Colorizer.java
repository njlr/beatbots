package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Colorizer implements Entity {
	
	private static final float RADIUS = 16f;
	private static final float RADIUS_SQUARED = RADIUS * RADIUS;
	
	private EntityManager entityManager;
	
	private NoteColor noteColor;
	private Vector2f position;
	
	private boolean isActive;

	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public Colorizer(EntityManager entityManager, NoteColor noteColor, Vector2f position) {
		
		super();
		
		this.entityManager = entityManager;
		
		this.noteColor = noteColor;
		this.position = position;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		for (Entity i : this.entityManager.getEntities()) {
			
			if (i instanceof Note) {
				
				Note note = (Note) i;
				
				if (this.position.distanceSquared(note.getPosition()) <= RADIUS_SQUARED) {
					
					NoteColor mixedColor = Utils.mix(note.getNoteColor(), this.noteColor);
					
					if (mixedColor != note.getNoteColor()) {
						
						note.setNoteColor(mixedColor);
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		graphics.setColor(Utils.getColor(this.noteColor));
		
		graphics.drawOval(
				this.position.getX() - RADIUS, 
				this.position.getY() - RADIUS, 
				RADIUS * 2, 
				RADIUS * 2);
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
}
