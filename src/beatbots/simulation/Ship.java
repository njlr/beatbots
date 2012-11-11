package beatbots.simulation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Ship implements Collider, KeyListener {
	
	private static final float DRAG_FACTOR = 0.98f;
	
	private static final float MAX_SPEED = 0.4f;
	private static final float MAX_SPEED_SQUARED = MAX_SPEED * MAX_SPEED;
	
	private static final float THRUST = 0.0025f;
	
	private static final float BULLET_SPEED = 0.3f;
	
	private BeatQueue beatQueue;
	private BulletManager bulletManager;
	
	private Vector2f position;
	private Vector2f velocity;
	
	private boolean isSteeringLeft;
	private boolean isSteeringRight;
	
	private Animation animationTilt;
	
	private Image imageBullet;
	
	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}

	@Override
	public float getRadius() {
		
		return this.animationTilt.getWidth() / 2;
	}
	
	public Ship(BeatQueue beatQueue, BulletManager bulletManager) {
		
		super();
		
		this.beatQueue = beatQueue;
		this.bulletManager = bulletManager;
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}

	public void init(GameContainer gameContainer) throws SlickException {
		
		Image frame0 = new Image("assets/ShipForward.png");
		Image frame1 = new Image("assets/ShipTiltingFrame1.png");
		Image frame2 = new Image("assets/ShipTiltingFrame2.png");
		
		this.animationTilt = new Animation(new Image[] { frame0, frame1, frame2 }, 120, false);
		
		this.animationTilt.setLooping(false);
		
		this.imageBullet = new Image("assets/PlayerBullet.png");
		
		this.position.set(640f / 2f, 480f - 128f);
		this.velocity.set(0f, 0f);
		
		this.isSteeringLeft = false;
		this.isSteeringRight = false;
		
		gameContainer.getInput().addKeyListener(this);
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		if (this.isSteeringLeft) {
			
			if (!this.isSteeringRight) {
				
				this.velocity.add(new Vector2f(-THRUST, 0f).scale(delta));
			}
		}
		else if (this.isSteeringRight) {
			
			this.velocity.add(new Vector2f(THRUST, 0f).scale(delta));
		}
		
		if (this.velocity.lengthSquared() > MAX_SPEED_SQUARED) {
			
			this.velocity.normalise();
			this.velocity.scale(MAX_SPEED);
		}
		
		this.position.add(this.velocity.copy().scale(delta));
		
		float l = this.velocity.length();
		
		if (l < MAX_SPEED * 0.25f) {
			
			this.animationTilt.setCurrentFrame(0);
		}
		else if (l < MAX_SPEED * 0.9f) {
			
			this.animationTilt.setCurrentFrame(1);
		}
		else {
			
			this.animationTilt.setCurrentFrame(2);
		}
		
		this.velocity.scale(DRAG_FACTOR);
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.drawAnimation(
				this.animationTilt, 
				this.position.getX() - this.animationTilt.getWidth() / 2, 
				this.position.getY() - this.animationTilt.getHeight() / 2);
	}
	
	public void collect(Beat beat) {
		
		this.beatQueue.storeBeat(beat);
	}

	@Override
	public void inputEnded() {
		
	}

	@Override
	public void inputStarted() {
		
	}

	@Override
	public boolean isAcceptingInput() {
		
		return true;
	}

	@Override
	public void setInput(Input input) {
		
	}

	@Override
	public void keyPressed(int key, char c) {
		
		switch (key) {
		
		case Input.KEY_LEFT:
			
			this.isSteeringLeft = true;
			
			break;
			
		case Input.KEY_RIGHT:
			
			this.isSteeringRight = true;
			
			break;
			
		case Input.KEY_UP:
			
		case Input.KEY_SPACE: 
			
			this.fire();
			
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		
		switch (key) {
		
		case Input.KEY_LEFT:
			
			this.isSteeringLeft = false;
			
			break;
			
		case Input.KEY_RIGHT:
			
			this.isSteeringRight = false;
			
			break;
		}
	}
	
	private void fire() {
		
		this.bulletManager.fire(
				this.imageBullet, 
				this.position.copy(), 
				new Vector2f(0f, -BULLET_SPEED), 
				this.beatQueue.getNextBeat());
	}
}
