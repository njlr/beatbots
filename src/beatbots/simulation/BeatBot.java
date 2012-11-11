package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public strictfp abstract class BeatBot implements Collider, BeatListener, BarListener {
	
	private BeatMachine beatMachine;

	public BeatBot(BeatMachine beatMachine) {
		
		super();
		
		this.beatMachine = beatMachine;
	}
	
	public void init(GameContainer gameContainer) {
		
		this.beatMachine.addBeatListener(this);
		this.beatMachine.addBarListener(this);
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
	}

	@Override
	public void bar() {
		
	}

	@Override
	public void beat() {
		
	}
}
