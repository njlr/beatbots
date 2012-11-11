package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Bullet implements Collider {
	
	private static final float BUFFER = 0f;

	private boolean isActive;
	
	private Image image;
	
	private Vector2f position;
	private Vector2f velocity;
	
	private Beat beat;
	
	private Color color;
	
	public boolean isActive() {
		
		return this.isActive;
	}
	
	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	@Override
	public float getRadius() {
		
		return 4f;
	}
	
	public Beat getBeat() {
		
		return beat;
	}
	
	public Bullet() {
		
		super();
		
		this.isActive = false;
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		this.position.add(this.velocity.copy().scale(delta));
		
		boolean isOutsideField = (
				(this.position.getX() < -BUFFER) || 
				(this.position.getY() < -BUFFER) || 
				(this.position.getX() > 640 + BUFFER) || 
				(this.position.getY() > 480 + BUFFER));
		
		if (isOutsideField) {
			
			this.destroy();
		}
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.drawImage(
				this.image, 
				this.position.getX() - this.image.getWidth() / 2, 
				this.position.getY() - this.image.getHeight() / 2, 
				this.color);
	}
	
	public void fire(Image image, Vector2f position, Vector2f velocity, Beat beat) {
		
		this.isActive = true;
		
		this.image = image;
		
		this.position.set(position);
		this.velocity.set(velocity);
		
		this.beat = beat;
		
		this.color = Utils.getBeatColor(this.beat);
	}
	
	public void destroy() {
		
		this.isActive = false;
	}
}
