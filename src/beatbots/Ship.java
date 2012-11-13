package beatbots;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Ship implements Entity, Collider, KeyListener {
	
	private static final float THRUST = 0.02f;
	private static final float DRAG_FACTOR = 1f - 0.1f;
	private static final float MAX_SPEED = 0.1f;
	private static final float MAX_SPEED_SQUARED = MAX_SPEED * MAX_SPEED;
	
	private static final int TIME_BETWEEN_SHOTS = 100;
	
	private SequenceManager sequenceManager;
	
	private Vector2f position;
	private Vector2f velocity;
	
	private float radius;
	
	private boolean steeringUp;
	private boolean steeringDown;
	private boolean steeringLeft;
	private boolean steeringRight;
	
	private boolean isFiring;
	
	private int timeTillCanShoot;
	
	private Animation animation;
	
	@Override
	public boolean isActive() {
		
		return true;
	}
	
	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	@Override
	public Vector2f getPosition() {
		
		return this.position;
	}
	
	@Override
	public float getRadius() {
		
		return this.radius;
	}
	
	public Ship(SequenceManager sequenceManager) {
		
		super();
		
		this.sequenceManager = sequenceManager;
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
		
		this.steeringUp = false;
		this.steeringDown = false;
		this.steeringLeft = false;
		this.steeringRight = false;
		
		this.isFiring = false;
	}
	
	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.position.set(gameContainer.getWidth() / 2f, gameContainer.getHeight() - 128f);
		this.velocity.set(0f, 0f);
		
		this.timeTillCanShoot = 0;
		
		gameContainer.getInput().addKeyListener(this);
		
		this.animation = new Animation(new SpriteSheet("assets/Ship.png", 16, 16), 1);
		
		this.animation.setAutoUpdate(false);
		
		this.radius = this.animation.getWidth() / 2;
	}

	@Override
	public void update(GameContainer gameContainer, int delta) {
		
		if (this.steeringUp) {
			
			if (!this.steeringDown) {
				
				this.velocity.y -= THRUST * delta;
			}
		}
		else if (this.steeringDown) {
			
			this.velocity.y += THRUST * delta;
		}
		
		if (this.steeringLeft) {
			
			if (!this.steeringRight) {
				
				this.velocity.x -= THRUST * delta;
			}
		}
		else if (this.steeringRight) {
			
			this.velocity.x += THRUST * delta;
		}
		
		this.velocity.scale(DRAG_FACTOR);
		
		if (this.velocity.lengthSquared() > MAX_SPEED_SQUARED) {
			
			this.velocity.normalise();
			this.velocity.scale(MAX_SPEED);
		}
		
		this.position.add(this.velocity.copy().scale(delta));
		
		if (this.timeTillCanShoot > 0) {
			
			this.timeTillCanShoot -= delta;
			
			if (this.timeTillCanShoot < 0) {
				
				this.timeTillCanShoot = 0;
			}
		}
		
		if (this.isFiring) {
			
			if (this.timeTillCanShoot == 0) {
			
				BulletManager.fire(this.position.copy(), new Vector2f(0, -1));
				
				this.timeTillCanShoot = TIME_BETWEEN_SHOTS;
			}
		}
		
		this.animation.setCurrentFrame((int) (((this.animation.getFrameCount() - 1) * Math.abs(this.velocity.getX()) / MAX_SPEED)));
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.drawAnimation(
				this.animation, 
				this.position.getX() - this.radius, 
				this.position.getY() - this.radius);
	}
	
	@Override
	public void onDestroy() {
		
	}
	
	@Override
	public void handle(Collider other) {
		
		if (other instanceof BeatBot) {
			
			// TODO: Die, lose points, etc.
		}
		else if (other instanceof BeatToken) {
			
			this.sequenceManager.record(((BeatToken) other).getBeat());
		}
	}

	@Override
	public void inputEnded() {
		
	}

	@Override
	public void inputStarted() {
		
	}

	@Override
	public boolean isAcceptingInput() {
		
		return this.isActive();
	}

	@Override
	public void setInput(Input input) {
		
	}

	@Override
	public void keyPressed(int key, char c) {
		
		switch (key) {
		
		case Input.KEY_UP:
			
			this.steeringUp = true;
			
			break;
			
		case Input.KEY_DOWN:
			
			this.steeringDown = true;
			
			break;
			
		case Input.KEY_LEFT:
			
			this.steeringLeft = true;
			
			break;
			
		case Input.KEY_RIGHT:
			
			this.steeringRight = true;
			
			break;
			
		case Input.KEY_SPACE:
			
			this.isFiring = true;
			
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		
		switch (key) {
		
		case Input.KEY_UP:
			
			this.steeringUp = false;
			
			break;
			
		case Input.KEY_DOWN:
			
			this.steeringDown = false;
			
			break;
			
		case Input.KEY_LEFT:
			
			this.steeringLeft = false;
			
			break;
			
		case Input.KEY_RIGHT:
			
			this.steeringRight = false;
			
			break;
			
		case Input.KEY_SPACE:
			
			this.isFiring = false;
			
			break;
		}
	}
}
