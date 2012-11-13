package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class BeatBotColored extends BeatBot {

	private EntityManager entityManager;
	private Beat beat;
	
	public BeatBotColored(Vector2f startingPosition, EntityManager entityManager, Beat beat) {
		
		super(startingPosition);
		
		this.entityManager = entityManager;
		this.beat = beat;
	}
	
	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		graphics.setColor(Utils.beatColor(this.beat));
		
		graphics.fillRect(
				this.getPosition().getX() - 4, 
				this.getPosition().getY() - 4, 
				8, 
				8);
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		
		this.entityManager.addEntity(new BeatToken(this.beat, this.getPosition()));
	}
}
