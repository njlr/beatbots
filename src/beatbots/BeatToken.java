package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class BeatToken implements Entity, Collider {
	
	private static final float FALL_SPEED = 0.1f;
	
	private Beat beat;
	private Vector2f startingPosition;
	
	private boolean isActive;
	
	private Vector2f position;

	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	@Override
	public float getRadius() {
		
		return 12f;
	}

	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	public Beat getBeat() {
		
		return this.beat;
	}
	
	public BeatToken(Beat beat, Vector2f startingPosition) {
		
		super();
		
		this.beat = beat;
		this.startingPosition = startingPosition;
		
		this.position = new Vector2f();
	}
	
	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.isActive = true;
		
		this.position.set(this.startingPosition);
	}

	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
		this.position.y += FALL_SPEED * delta;
		
		if (this.position.getY() > gameContainer.getHeight()) {
			
			this.destroy();
		}
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.setColor(Utils.beatColor(this.beat));
		
		graphics.fillOval(
				this.position.getX() - 2f, 
				this.position.getY() - 2f, 
				4f, 
				4f);
		
		graphics.drawOval(
				this.position.getX() - 8f, 
				this.position.getY() - 8f, 
				16f, 
				16f);
	}
	
	public void destroy() {
		
		this.isActive = false;
	}
	
	@Override
	public void onDestroy() {
		
	}
	
	@Override
	public void handle(Collider other) {
		
		if (other instanceof Ship) {
			
			this.destroy();
		}
	}
}
