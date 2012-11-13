package beatbots;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Bullet implements Entity, Collider {
	
	private static final float SPEED = 0.3f;

	private boolean isActive;
	
	private Vector2f position;
	private Vector2f velocity;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	@Override
	public boolean isReusable() {
		
		return true;
	}
	
	@Override
	public float getRadius() {
		
		return 8f;
	}

	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	public Bullet() {
		
		super();
		
		this.isActive = false;
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}
	
	@Override
	public void init(GameContainer gameContainer) {
		
		this.isActive = false;
	}

	@Override
	public void update(GameContainer gameContainer, int delta) {
		
		this.position.add(this.velocity.copy().scale(delta));
		
		if (this.position.y < 0) {
			
			this.destroy();
		}
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.setColor(Color.white);
		
		graphics.fillRect(
				this.position.getX() - 1, 
				this.position.getY() - 4, 
				2, 
				8);
	}
	
	public void fire(Vector2f position, Vector2f direction) {
		
		this.isActive = true;
		
		if (direction.lengthSquared() != 1f) {
			
			direction.normalise();
		}
		
		this.position.set(position);
		this.velocity.set(direction.scale(SPEED));
	}
	
	public void destroy() {
		
		this.isActive = false;
	}
	
	@Override
	public void onDestroy() {
		
	}
	
	@Override
	public void handle(Collider other) {
		
		if (other instanceof BeatBot) {
			
			this.destroy();
		}
	}
}
