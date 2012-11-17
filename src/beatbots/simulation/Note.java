package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Note implements Entity {
	
	private static final float NOTE_SPEED = 0.02f;

	private Node startingNode;
	
	private boolean isActive;
	
	private Vector2f position;
	private Vector2f direction;
	
	private Node target;
	
	private NoteColor noteColor;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	public NoteColor getNoteColor() {
		
		return this.noteColor;
	}
	
	public Note(Node startingNode) {
		
		super();
		
		this.startingNode = startingNode;
		
		this.position = new Vector2f();
		this.direction = new Vector2f();
		
		this.target = null;
		
		this.noteColor = NoteColor.White;
	}
	
	@Override
	public void init(GameContainer container) {
		
		this.isActive = true;
		
		this.position.set(this.startingNode.getPosition());
		
		this.target = this.startingNode.getSuccessor();
		
		if (this.target != null) {
			
			this.setDirection();
		}
		
		this.noteColor = NoteColor.Red;
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		
		if (this.target == null) {
			
			this.deactivate();
		}
		else {
			
			this.move(NOTE_SPEED * delta);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		
		
		switch (this.noteColor) {
		
		case Red:
			
			graphics.setColor(Color.red);
			
			break;
			
		case Blue:
			
			graphics.setColor(Color.cyan);
			
			break;
			
		case Yellow:
			
			graphics.setColor(Color.yellow);
			
			break;
			
		case Magenta:
			
			graphics.setColor(Color.magenta);
			
			break;
			
		case Orange:
			
			graphics.setColor(Color.orange);
			
			break;
			
		case Green:
			
			graphics.setColor(Color.green);
			
			break;

		default:
			
			graphics.setColor(Color.white);
			
			break;
		}
		
		graphics.drawRect(
				this.position.x - 2f,  
				this.position.y - 2f, 
				4f, 
				4f);
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
	
	private void next() {
		
		if (this.target != null) {
			
			if (this.target.getSuccessor() == null) {
				
				this.target.getNotes().add(this);
				
				this.deactivate();
			}
			else {
				
				this.target = this.target.getSuccessor();
				
				this.setDirection();
			}
		}
	}
	
	private void setDirection() {
		
		this.direction.set(this.target.getPosition().copy().sub(this.position).normalise());
	}
	
	private void move(float distanceToMove) {
		
		if (this.position.distanceSquared(this.target.getPosition()) > Math.pow(distanceToMove, 2)) {
			
			this.position.add(this.direction.copy().scale(distanceToMove));
		}
		else {
			
			float overflow = distanceToMove - this.position.distance(this.target.getPosition());
			
			this.position.set(this.target.getPosition());
			
			this.next();
			
			if (this.isActive()) {
				
				this.move(overflow);
			}
		}
	}
}
