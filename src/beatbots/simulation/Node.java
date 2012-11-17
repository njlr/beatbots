package beatbots.simulation;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Node implements Entity {
	
	private Vector2f position;
	private Node successor;
	
	private boolean isActive;
	
	private Queue<Note> notes;

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
	
	public Queue<Note> getNotes() {
		
		return this.notes;
	}
	
	public Node(Vector2f position, Node successor) {
		
		super();
		
		this.position = position;
		this.successor = successor;
		
		this.notes = new ConcurrentLinkedQueue<Note>();
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
		
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		if (this.getSuccessor() != null) {
			
			graphics.setColor(Color.yellow);
			
			graphics.drawLine(
					this.position.x, 
					this.position.y, 
					this.getSuccessor().getPosition().getX(), 
					this.getSuccessor().getPosition().getY());
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
