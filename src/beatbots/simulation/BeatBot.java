package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class BeatBot implements KeyListener, BeatListener, BarListener {
	
	private static final int SPEED = 2;
	
	private enum CompassDirection { Up, Down, Left, Right };
	
	private BeatMachine beatMachine;
	private BotIndex botIndex;
	
	private Vector2f startPosition;
	
	private Input input;
	
	private CompassDirection direction;
	private CompassDirection nextDirection;
	
	private Vector2f position;
	private Vector2f velocity;
	
	public BeatBot(BeatMachine beatMachine, BotIndex botIndex, Vector2f startPostion) {
		
		super();
		
		this.beatMachine = beatMachine;
		
		this.botIndex = botIndex;
		
		this.startPosition = startPostion.copy();
		
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}
	
	public void init(GameContainer gameContainer) {
		
		this.position.set(this.startPosition);
		this.velocity.set(0f, 0f);
		
		this.input = gameContainer.getInput();
		
		this.input.addKeyListener(this);
		
		this.beatMachine.addBeatListener(this);
		this.beatMachine.addBarListener(this);
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		this.position.add(this.velocity);
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		
	}
	
	public void destroy() {
		
		this.input.removeKeyListener(this);
		
		this.beatMachine.removeBeatListener(this);
		this.beatMachine.removeBarListener(this);
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
		
		this.input.removeKeyListener(this);
		
		this.input = input;
		
		this.input.addKeyListener(this);
	}

	@Override
	public void keyPressed(int key, char c) {
		
		switch (this.botIndex) {
		
		case One:
			
			// WASD
			switch(key) {
			
			case Input.KEY_W: 
				
				this.nextDirection = CompassDirection.Up;
				
				break;
				
			case Input.KEY_A:
				
				this.nextDirection = CompassDirection.Left;
				
				break;
				
			case Input.KEY_S:
				
				this.nextDirection = CompassDirection.Down;
				
				break;
				
			case Input.KEY_D:
				
				this.nextDirection = CompassDirection.Right;
				
				break;
			}
			
			break;
			
		case Two: 
			
			// IJKL
			switch(key) {
			
			case Input.KEY_I: 
				
				this.nextDirection = CompassDirection.Up;
				
				break;
				
			case Input.KEY_J:
				
				this.nextDirection = CompassDirection.Left;
				
				break;
				
			case Input.KEY_K:
				
				this.nextDirection = CompassDirection.Down;
				
				break;
				
			case Input.KEY_L:
				
				this.nextDirection = CompassDirection.Right;
				
				break;
			}
			
			break;
			
		case Three:
			
			// Arrows
			switch(key) {
			
			case Input.KEY_UP: 
				
				this.nextDirection = CompassDirection.Up;
				
				break;
				
			case Input.KEY_LEFT:
				
				this.nextDirection = CompassDirection.Left;
				
				break;
				
			case Input.KEY_DOWN:
				
				this.nextDirection = CompassDirection.Down;
				
				break;
				
			case Input.KEY_RIGHT:
				
				this.nextDirection = CompassDirection.Right;
				
				break;
			}
			
			break;
			
		case Four: 
			
			// Numpad
			switch(key) {
			
			case Input.KEY_8: 
				
				this.nextDirection = CompassDirection.Up;
				
				break;
				
			case Input.KEY_4:
				
				this.nextDirection = CompassDirection.Left;
				
				break;
				
			case Input.KEY_5:
				
				this.nextDirection = CompassDirection.Down;
				
				break;
				
			case Input.KEY_6:
				
				this.nextDirection = CompassDirection.Right;
				
				break;
			}
			
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		
		
	}

	@Override
	public void bar() {
		
		// TODO: Something cool?
	}

	@Override
	public void beat() {
		
		if (this.direction != this.nextDirection) {
			
			switch (this.nextDirection) {
			
			case Up:
				
				this.velocity.set(0f, SPEED);
				
				break;
				
			case Down:
				
				this.velocity.set(0f, -SPEED);
				
				break;
				
			case Left:
				
				this.velocity.set(-SPEED, 0f);
				
				break;
				
			case Right:
				
				this.velocity.set(SPEED, 0f);
				
				break;
			}
			
			this.direction = this.nextDirection;
			
			// TODO: Something cool?
		}
	}
}
