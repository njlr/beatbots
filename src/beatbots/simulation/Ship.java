package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class Ship implements KeyListener {
	
	private static final float DRAG_FACTOR = 0.975f;
	
	private static final float MAX_SPEED = 0.3f;
	private static final float MAX_SPEED_SQUARED = MAX_SPEED * MAX_SPEED;
	
	private static final float THRUST = 0.03f;
	
	private Vector2f position;
	private Vector2f velocity;
	
	private boolean isSteeringLeft;
	private boolean isSteeringRight;
	
	private Image imageShip;
	
	public Ship() {
		
		super();
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}

	public void init(GameContainer gameContainer) throws SlickException {
		
		this.imageShip = new Image("assets/ShipForward.png");
		
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
		
		this.velocity.scale(DRAG_FACTOR);
		
		if (this.velocity.lengthSquared() > MAX_SPEED_SQUARED) {
			
			this.velocity.normalise();
			this.velocity.scale(MAX_SPEED);
		}
		
		this.position.add(this.velocity.copy().scale(delta));
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.drawImage(
				this.imageShip, 
				this.position.getX() - this.imageShip.getWidth() / 2, 
				this.position.getY() - this.imageShip.getHeight() / 2);
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
}
