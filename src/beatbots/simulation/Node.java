package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Node implements Entity {
	
	private Vector2f position;
	private Node successor;
	
	private boolean isActive;

	@Override
	public boolean isActive() {
		
		return isActive;
	}
	
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	public Node getSuccessor() {
		
		return this.successor;
	}
	
	public Node(Vector2f position, Node successor) {
		
		super();
		
		this.position = position;
		this.successor = successor;
	}
	
	public Node(Vector2f position) {
		
		this(position, null);
	}
	
	@Override
	public void init(GameContainer container) {
		
		this.isActive = true;
	}

	@Override
	public void update(GameContainer container, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		graphics.setColor(Color.white);
		
		graphics.drawRect(
				this.position.x - 14f,  
				this.position.y - 14f, 
				28f, 
				28f);
		
		if (this.successor != null) {
			
			graphics.setColor(Color.yellow);
			
			graphics.drawLine(
					this.position.x, 
					this.position.y, 
					this.successor.getPosition().getX(), 
					this.successor.getPosition().getY());
		}
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
}
