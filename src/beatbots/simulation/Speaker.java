package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Speaker implements Entity {
	
	private Sequence sequence;
	private Node node;
	
	private boolean isActive;
	
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
		
		graphics.setColor(Color.magenta);
		
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
		
	}
}
