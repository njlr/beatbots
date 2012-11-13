package beatbots;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public strictfp class BeatBot implements Entity, Collider {
	
	private Vector2f startingPosition;
	
	private boolean isActive;
	
	private float radius;
	
	private Vector2f position;
	private Vector2f velocity;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	public float getRadius() {
		
		return this.radius;
	}
	
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	public BeatBot(Vector2f startingPosition) {
		
		super();
		
		this.startingPosition = startingPosition;
		
		this.radius = 16f;
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}

	@Override
	public void init(GameContainer gameContainer) {
		
		this.isActive = true;
		
		this.position.set(this.startingPosition);
	}

	@Override
	public void update(GameContainer gameContainer, int delta) {
		
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.setColor(Color.white);
		
		graphics.fillRect(
				this.position.getX() - 4, 
				this.position.getY() - 4, 
				8, 
				8);
	}
	
	public void destroy() {
		
		this.isActive = false;
	}

	@Override
	public void onDestroy() {
		
	}

	@Override
	public void handle(Collider other) {
		
		if (other instanceof Bullet) {
			
			this.destroy();
		}
		else if (other instanceof Ship) {
			
			this.destroy();
		}
	}
}
