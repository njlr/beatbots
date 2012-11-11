package beatbots.simulation;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public strictfp final class BeatQueue {
	
	private Queue<Beat> beats;
	
	public BeatQueue() {
		
		super();
		
		this.beats = new LinkedList<Beat>();
	}
	
	public void init(GameContainer gameContainer) {
		
		this.beats.clear();
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		
	}
	
	public void storeBeat(Beat beat) {
		
		this.beats.add(beat);
	}
	
	public Beat getNextBeat() {
		
		if (this.beats.isEmpty()) {
			
			return Beat.None;
		}
		else {
			
			return this.beats.remove();
		}
	}
}
