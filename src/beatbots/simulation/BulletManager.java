package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class BulletManager {
	
	private static final int MAX_BULLET_COUNT = 256;

	private Bullet[] bullets = new Bullet[MAX_BULLET_COUNT];
	
	public BulletManager() {
		
		super();
	}
	
	public void init(GameContainer gameContainer) {
		
		for (int i = 0; i < this.bullets.length; i++) {
			
			this.bullets[i] = new Bullet();
		}
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		for (Bullet i : this.bullets) {
			
			if (i.isActive()) {
				
				i.update(gameContainer, delta);
			}
		}
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		for (Bullet i : this.bullets) {
			
			if (i.isActive()) {
				
				i.render(gameContainer, graphics);
			}
		}
	}
	
	public void fire(Image image, Vector2f position, Vector2f velocity, Beat beat) {
		
		Bullet bullet = getFreeBullet();
		
		if (bullet != null) {
			
			bullet.fire(image, position, velocity, beat);
		}
	}
	
	private Bullet getFreeBullet() {
		
		for (Bullet i : this.bullets) {
			
			if (!i.isActive()) {
				
				return i;
			}
		}
		
		return null;
	}
}
