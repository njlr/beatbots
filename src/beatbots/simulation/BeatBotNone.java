package beatbots.simulation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp class BeatBotNone extends BeatBot {
	
	private Animation animation;
	
	private Beat beatMark;
	private Color color;

	@Override
	public float getRadius() {
		
		return this.animation.getWidth() / 2;
	}
	
	public BeatBotNone(BeatMachine beatMachine, BulletManager bulletManager, Vector2f startPosition) {
		
		super(beatMachine, bulletManager, startPosition);
	}
	
	@Override
	public strictfp void init(GameContainer gameContainer) throws SlickException {
		
		super.init(gameContainer);
		
		this.animation = new Animation(new Image[] { new Image("assets/BeatBot1Frame1.png"), new Image("assets/BeatBot1Frame2.png") }, 200, true);
		
		this.beatMark = Beat.None;
		
		this.color = Utils.getBeatColor(this.beatMark);
	}
	
	@Override
	public strictfp void render(GameContainer gameContainer, Graphics graphics) {
		
		super.render(gameContainer, graphics);
		
		graphics.drawAnimation(
				this.animation, 
				this.getPosition().getX() - this.animation.getWidth() / 2, 
				this.getPosition().getY() - this.animation.getHeight() / 2, 
				this.color);
	}
	
	@Override
	public strictfp void beat() {
		
		super.beat();
		
		if (!this.isActive()) {
			
			// TODO: Play beat!
		}
	}
	
	@Override
	public strictfp void mark(Beat beat) {
		
		super.mark(beat);
		
		this.beatMark = beat;
		
		this.color = Utils.getBeatColor(this.beatMark);
	}
}
