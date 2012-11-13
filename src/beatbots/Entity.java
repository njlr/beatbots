package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {
	
	public boolean isActive();
	
	public boolean isReusable();

	public void init(GameContainer gameContainer) throws SlickException;
	
	public void update(GameContainer gameContainer, int delta) throws SlickException;
	
	public void render(GameContainer gameContainer, Graphics graphics);
	
	public void onDestroy();
}
