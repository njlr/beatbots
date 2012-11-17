package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;


public strictfp final class Switch extends Node {
	
	private Node successorB;
	
	private boolean chooseAlternate;
	
	@Override
	public Node getSuccessor() {
		
		if (this.chooseAlternate) {
			
			return this.successorB;
		}
		else {
			
			return super.getSuccessor();
		}
	}

	public Switch(Vector2f position, Node successorA, Node successorB) {
		
		super(position, successorA);
		
		this.successorB = successorB;
	}
	
	@Override
	public void init(GameContainer container) {
		
		super.init(container);
		
		this.chooseAlternate = false;
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		super.render(container, graphics);
		
		Node inactiveSuccessor;
		
		if (this.chooseAlternate) {
			
			inactiveSuccessor = this.successorB;
		}
		else {
			
			inactiveSuccessor = super.getSuccessor();
		}
		
		graphics.setColor(Color.gray);
		
		graphics.drawLine(
				this.getPosition().getX(), 
				this.getPosition().getY(), 
				inactiveSuccessor.getPosition().getX(), 
				inactiveSuccessor.getPosition().getY());
	}
}
