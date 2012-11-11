package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp class BeatBotOne extends BeatBot {

	private Vector2f startPosition;
	
	private Vector2f position;
	
	private Image image;
	
	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}

	@Override
	public float getRadius() {
		
		return this.image.getWidth() / 2;
	}
	
	public BeatBotOne(BeatMachine beatMachine, BulletManager bulletManager, Vector2f startPosition) {
		
		super(beatMachine, bulletManager);
		
		this.startPosition = startPosition.copy();
		
		this.position = new Vector2f();
	}
	
	@Override
	public strictfp void init(GameContainer gameContainer) throws SlickException {
		
		super.init(gameContainer);
		
		this.position.set(this.startPosition);
		
		this.image = new Image("assets/BeatBot1Frame1.png");
	}
	
	@Override
	public strictfp void update(GameContainer gameContainer, int delta) {
		
		super.update(gameContainer, delta);
		
		this.position.y += 0.001f * delta;
	}
	
	@Override
	public strictfp void render(GameContainer gameContainer, Graphics graphics) {
		
		super.render(gameContainer, graphics);
		
		graphics.drawImage(
				this.image, 
				this.position.getX() - this.image.getWidth() / 2, 
				this.position.getY() - this.image.getHeight() / 2, 
				this.getBeatColor());
	}
}
