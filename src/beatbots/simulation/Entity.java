package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Entity {
	
	boolean isActive();

	void init(GameContainer container);
	
	void update(GameContainer container, int delta);
	
	void render(GameContainer container, Graphics graphics);
	
	void deactivate();
	
	void destroy(GameContainer gameContainer);
}
