package beatbots;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public strictfp final class Metronome implements Entity {
	
	public static final int BEATS_PER_BAR = 8;
	
	private static final int TIME_BETWEEN_BEATS = 1000;
	
	private int timeTillBeat;
	
	private int beatCount;
	
	private Set<MetronomeListener> listeners;

	@Override
	public boolean isActive() {
		
		return true;
	}
	
	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	public int getBeatCount() {
		
		return this.beatCount;
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.timeTillBeat = 0;
		
		this.beatCount = 0;
		
		this.listeners = new HashSet<MetronomeListener>();
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
	public void onDestroy() {
		
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
		
		if (this.beatCount == BEATS_PER_BAR) {
			
			this.bar();
		}
	}
	
	private void bar() {
		
		for (MetronomeListener i : this.listeners) {
			
			i.bar();
		}
		
		this.beatCount = 0;
	}
}
