package beatbots.simulation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Speaker implements Entity {
	
	private Sequence sequence;
	private Node node;
	
	private boolean isActive;
	
	private Animation animation;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}

	public Speaker(Sequence sequence, Node node) {
		
		super();
		
		this.sequence = sequence;
		this.node = node;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
		
		this.animation = new Animation(new SpriteSheet("assets/Speaker.png", 32, 32), 33);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		if (this.node.isActive()) {
			
			while (!this.node.getNotes().isEmpty()) {
				
				this.sequence.record(this.node.getNotes().remove().getNoteColor());
			}
		}
		else {
			
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
		
	}
}
