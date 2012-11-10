package beatbots.simulation;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.GameContainer;

public strictfp final class BeatMachine {

	private static final int BEAT_TIME = 1000;
	
	private static final int BEATS_PER_BAR = 8;
	
	private int beatTimer;
	private int beatCounter;
	
	private Set<BeatListener> beatListeners;
	private Set<BarListener> barListeners;
	
	public BeatMachine() {
		
		super();
		
		this.beatListeners = new HashSet<BeatListener>();
		
		this.barListeners = new HashSet<BarListener>();
	}
	
	public void init(GameContainer gameContainer) {
		
		this.beatTimer = 0;
		this.beatCounter = 0;
		
		this.notifyBeatListeners();
		this.notifyBarListeners();
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		this.beatTimer += delta;
		
		if (this.beatTimer >= BEAT_TIME) {
			
			this.notifyBeatListeners();
			
			this.beatTimer = 0;
			
			this.beatCounter++;
			
			if (this.beatCounter == BEATS_PER_BAR) {
				
				this.notifyBarListeners();
				
				this.beatCounter = 0;
			}
		}
	}
	
	public void addBeatListener(BeatListener beatListener) {
		
		this.beatListeners.add(beatListener);
	}
	
	public void addBarListener(BarListener barListener) {
		
		this.barListeners.add(barListener);
	}
	
	public void removeBeatListener(BeatListener beatListener) {
		
		this.beatListeners.remove(beatListener);
	}
	
	public void removeBarListener(BarListener barListener) {
		
		this.barListeners.remove(barListener);
	}
	
	public void removeAllBeatListeners() {
		
		this.beatListeners.clear();
	}
	
	public void removeAllBarListeners() {
		
		this.barListeners.clear();
	}
	
	private void notifyBeatListeners() {
		
		for (BeatListener i : this.beatListeners) {
			
			i.beat();
		}
	}
	
	private void notifyBarListeners() {
		
		for (BarListener i : this.barListeners) {
			
			i.bar();
		}
	}
}
