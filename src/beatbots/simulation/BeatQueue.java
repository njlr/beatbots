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
		
		this.beats.add(Beat.Red);
		this.beats.add(Beat.Green);
		this.beats.add(Beat.Blue);
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		int x = 640 - 32;
		int y = 480 - 64;
		
		for (Beat i : this.beats) {
			
			graphics.setColor(Utils.getBeatColor(i));
			
			graphics.fillRect(x, y, 24, 24);
			
			y -= 32;
		}
		
		graphics.setColor(Utils.getBeatColor(Beat.None));
		
		graphics.fillRect(x, y, 24, 24);
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
