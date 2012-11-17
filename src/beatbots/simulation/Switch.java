package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Vector2f;


public strictfp final class Switch extends Node implements MouseListener {
	
	private Node successorA;
	private Node successorB;
	
	private boolean chooseAlternate;
	
	private Input input;
	
	@Override
	public Node getSuccessor() {
		
		if (this.chooseAlternate) {
			
			return this.successorB;
		}
		else {
			
			return this.successorA;
		}
	}

	public Switch(Vector2f position, Node successorA, Node successorB) {
		
		super(position);
		
		this.successorA = successorA;
		this.successorB = successorB;
	}
	
	@Override
	public void init(GameContainer container) {
		
		super.init(container);
		
		this.chooseAlternate = true;
		
		this.input = container.getInput();
		
		this.input.addMouseListener(this);
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		super.render(container, graphics);
		
		Node inactiveSuccessor;
		
		if (this.chooseAlternate) {
			
			inactiveSuccessor = this.successorA;
		}
		else {
			
			inactiveSuccessor = this.successorB;
		}
		
		graphics.setColor(Color.gray);
		
		graphics.drawLine(
				this.getPosition().getX(), 
				this.getPosition().getY(), 
				inactiveSuccessor.getPosition().getX(), 
				inactiveSuccessor.getPosition().getY());
		
		graphics.setColor(Color.white);
		
		graphics.drawOval(
				this.getPosition().getX() - 16f, 
				this.getPosition().getY() - 16f, 
				32f, 
				32f);
	}
	
	@Override
	public void destroy(GameContainer gameContainer) {
		
		super.destroy(gameContainer);
		
		this.input.removeMouseListener(this);
	}
	
	public void flip() {
		
		this.chooseAlternate = !this.chooseAlternate;
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
		
		if (this.input != input) {
			
			this.input.removeMouseListener(this);
			
			this.input = input;
			
			this.input.addMouseListener(this);
		}
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		
		if ((button == Input.MOUSE_LEFT_BUTTON) && (clickCount == 1)) {
			
			if (this.getPosition().distanceSquared(new Vector2f(x, y)) < 32f * 32f) {
				
				this.flip();
			}
		}
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		
	}
}
