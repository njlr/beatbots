package beatbots.simulation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp class BeatBotNone extends BeatBot {
	
	private Beat beatMark;
	private Color beatColor;
	
	public BeatBotNone(BeatMachine beatMachine, BulletManager bulletManager, Vector2f startPosition) {
		
		super(beatMachine, bulletManager, startPosition);
	}
	
	@Override
	public strictfp void init(GameContainer gameContainer) throws SlickException {
		
		super.init(gameContainer);
		
		this.animation = new Animation(new Image[] { new Image("assets/BeatBot1Frame1.png"), new Image("assets/BeatBot1Frame2.png") }, 200, true);
		
		this.beatMark = Beat.None;
	}
	
	@Override
	public strictfp void render(GameContainer gameContainer, Graphics graphics) {
		
		super.render(gameContainer, graphics);
		
		if (this.isMarked()) {
			
			float r = this.getRadius();
			
			graphics.setColor(this.beatColor);
			
			graphics.drawOval(this.getPosition().getX() - r, this.getPosition().getY() - r, r * 2, r * 2);
		}
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
		
		this.beatColor = Utils.getBeatColor(this.beatMark);
	}
}
