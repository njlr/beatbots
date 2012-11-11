package beatbots.simulation;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public strictfp abstract class BeatBot implements Collider, BeatListener, BarListener {
	
	private BeatMachine beatMachine;
	private BulletManager bulletManager;
	
	private boolean isActive;
	
	private boolean isMarked;
	
	private Beat beatMark;
	private Color beatColor;
	
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public Color getBeatColor() {
		
		return this.beatColor;
	}
	
	public BeatBot(BeatMachine beatMachine, BulletManager bulletManager) {
		
		super();
		
		this.beatMachine = beatMachine;
		this.bulletManager = bulletManager;
		
		this.isActive = true;
	}
	
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.beatMark = Beat.None;
		
		this.beatColor = Utils.getBeatColor(this.beatMark);
		
		this.beatMachine.addBeatListener(this);
		this.beatMachine.addBarListener(this);
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		for (Bullet i : this.bulletManager.getBullets()) {
			
			if (i.isActive()) {
				
				if (Utils.checkCollision(this, i)) {
					
					this.mark(i.getBeat());
					
					i.destroy();
				}
			}
		}
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
	}
	
	public void destroy() {
		
		this.isActive = false;
	}
	
	@Override
	public void bar() {
		
	}

	@Override
	public void beat() {
		
		if (this.isMarked) {
			
			// TODO: Play beat!
			
			this.destroy();
		}
	}
	
	private void mark(Beat beat) {
		
		this.isMarked = true;
		
		this.beatMark = beat;
		
		this.beatColor = Utils.getBeatColor(this.beatMark);
	}
}
