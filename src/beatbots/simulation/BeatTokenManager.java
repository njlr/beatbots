package beatbots.simulation;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class BeatTokenManager {
	
	private Ship ship;
	
	private List<BeatToken> beatTokens;
	
	private Image imageToken;

	public BeatTokenManager(Ship ship) {
		
		super();
		
		this.ship = ship;
		
		this.beatTokens = new ArrayList<BeatToken>(8);
	}
	
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.imageToken = new Image("assets/Token.png");
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		for (BeatToken i : this.beatTokens) {
			
			if (i.isActive()) {
				
				i.update(gameContainer, delta);
				
				if (Utils.checkCollision(this.ship, i)) {
					
					this.ship.collect(i.getBeat());
					
					i.destroy();
				}
			}
		}
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		for (BeatToken i : this.beatTokens) {
			
			if (i.isActive()) {
				
				i.render(gameContainer, graphics);
			}
		}
	}
	
	public void drop(Vector2f position, Beat beat) {
		
		BeatToken beatToken = this.getFreeBeatToken();
		
		beatToken.drop(this.imageToken, position, beat);
	}
	
	private BeatToken getFreeBeatToken() {
		
		for (BeatToken i : this.beatTokens) {
			
			if (!i.isActive()) {
				
				return i;
			}
		}
		
		BeatToken i = new BeatToken();
		
		this.beatTokens.add(i);
		
		return i;
	}
}
