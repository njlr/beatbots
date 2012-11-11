package beatbots.simulation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp class BeatBotNone extends BeatBot {
	
	private static final float SPEED = 0.02f;

	private Vector2f startPosition;
	
	private Vector2f position;
	
	private Animation animation;
	
	private boolean isGoingRight;
	
	private int hops;
	
	private float desY;
	
	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}

	@Override
	public float getRadius() {
		
		return this.animation.getWidth() / 2;
	}
	
	public BeatBotNone(BeatMachine beatMachine, BulletManager bulletManager, Vector2f startPosition) {
		
		super(beatMachine, bulletManager);
		
		this.startPosition = startPosition.copy();
		
		this.position = new Vector2f();
	}
	
	@Override
	public strictfp void init(GameContainer gameContainer) throws SlickException {
		
		super.init(gameContainer);
		
		this.position.set(this.startPosition);
		
		this.desY = this.position.y;
		
		this.isGoingRight = true;
		
		this.hops = 0;
		
		this.animation = new Animation(new Image[] { new Image("assets/BeatBot1Frame1.png"), new Image("assets/BeatBot1Frame2.png") }, 200, true);
	}
	
	@Override
	public strictfp void update(GameContainer gameContainer, int delta) {
		
		super.update(gameContainer, delta);
		
		if (this.position.y < this.desY) {
			
			this.position.y += SPEED * delta;
		}
		else {
			
			if (this.isGoingRight) {
				
				this.position.x += SPEED * delta;
			}
			else {
				
				this.position.x -= SPEED * delta;
			}
		}

	}
	
	@Override
	public strictfp void render(GameContainer gameContainer, Graphics graphics) {
		
		super.render(gameContainer, graphics);
		
		graphics.drawAnimation(
				this.animation, 
				this.position.getX() - this.animation.getWidth() / 2, 
				this.position.getY() - this.animation.getHeight() / 2, 
				this.getBeatColor());
	}
	
	@Override
	public strictfp void beat() {
		
		super.beat();
		
		this.hops++;
		
		if (this.hops == 5) {
			
			this.hops = 0;
			
			this.desY = this.position.y + 4f;
			
			this.isGoingRight = !this.isGoingRight;
		}
	}
}
