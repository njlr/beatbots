package beatbots.simulation;

import java.util.Queue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public strictfp final class BeatQueue implements BeatListener, BarListener {

	private BeatMachine beatMachine;
	
	private Queue<Beat> beats;
	
	public BeatQueue(BeatMachine beatMachine) {
		
		super();
		
		this.beatMachine = beatMachine;
		
		this.beatMachine.addBeatListener(this);
		this.beatMachine.addBarListener(this);
	}
	
	public void init(GameContainer gameContainer) {
		
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
