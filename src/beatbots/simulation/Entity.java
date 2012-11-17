package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {
	
	boolean isActive();

	void init(GameContainer container) throws SlickException;
	
	void update(GameContainer container, int delta) throws SlickException;
	
	void render(GameContainer container, Graphics graphics);
	
	void deactivate();
	
	void destroy(GameContainer gameContainer);
}
