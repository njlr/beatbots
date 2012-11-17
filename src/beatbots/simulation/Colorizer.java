package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Colorizer implements Entity {
	
	private static final float RADIUS = 8f;
	private static final float RADIUS_SQUARED = RADIUS * RADIUS;
	
	private EntityManager entityManager;
	
	private NoteColor noteColor;
	private Vector2f position;
	
	private boolean isActive;
	
	private Image image;

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
		
		this.image = new Image("assets/Colorizer.png");
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
		
		graphics.drawImage(
				this.image, 
				this.position.x - this.image.getWidth() / 2, 
				this.position.y - this.image.getHeight() / 2, 
				Utils.getColor(this.noteColor));
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
}
