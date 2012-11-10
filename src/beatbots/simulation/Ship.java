package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Ship {
	
	private Vector2f position;
	
	private Image imageShip;
	
	public Ship() {
		
		super();
	}

	public void init(GameContainer gameContainer) {
		
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.drawImage(
				this.imageShip, 
				this.position.getX() - this.imageShip.getWidth() / 2, 
				this.position.getY() - this.imageShip.getHeight() / 2);
	}
}
