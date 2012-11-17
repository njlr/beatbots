package beatbots.simulation;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public strictfp final class Metronome implements Entity {
	
	public static final int BEATS_PER_BAR = 8;
	
	private static final int TIME_BETWEEN_BEATS = 3000;
	
	private int timeTillBeat;
	private int beatCount;
	
	private Set<MetronomeListener> listeners;
	
	private Sound soundBeat;

	@Override
	public boolean isActive() {
		
		return true;
	}
	
	public int getBeatCount() {
		
		return this.beatCount;
	}
	
	public Metronome() {
		
		super();
		
		this.listeners = new HashSet<MetronomeListener>();
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.timeTillBeat = 0;
		
		this.beatCount = 0;
		
		this.soundBeat = new Sound("assets/sfx/Beat.wav");
	}

	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
		this.timeTillBeat -= delta;
		
		while (this.timeTillBeat < 0) {
			
			this.beat();
			
			this.timeTillBeat += TIME_BETWEEN_BEATS;
		}
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
	}
	
	@Override
	public void deactivate() {
		
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
	
	public void addListener(MetronomeListener listener) {
		
		this.listeners.add(listener);
	}
	
	public void removeListener(MetronomeListener listener) {
		
		this.listeners.add(listener);
	}
	
	public void removeAllListeners() {
		
		this.listeners.clear();
	}
	
	private void beat() {
		
		for (MetronomeListener i : this.listeners) {
			
			i.beat(this.beatCount);
		}
		
		this.beatCount++;
		
		this.soundBeat.play();
	}
}
