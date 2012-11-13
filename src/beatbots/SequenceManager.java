package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SequenceManager implements Entity, MetronomeListener {
	
	private Metronome metronome;
	
	private Sequence current;
	private Sequence previous;
	
	private Sound soundRed;
	private Sound soundBlue;

	@Override
	public boolean isActive() {
		
		return true;
	}
	
	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	public SequenceManager(Metronome metronome) {
		
		super();
		
		this.metronome = metronome;
		
		this.current = new Sequence();
		this.previous = new Sequence();
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.soundRed = new Sound("assets/sfx/Red.wav");
		this.soundBlue = new Sound("assets/sfx/Blue.wav");
		
		this.metronome.addListener(this);
	}

	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		int x_current = 4;
		int x_previous = gameContainer.getWidth() - Metronome.BEATS_PER_BAR * 36;
		int y = gameContainer.getHeight() - 36;
		
		for (int i = 0; i < Metronome.BEATS_PER_BAR; i++) {
			
			// Current
			graphics.setColor(Utils.beatColor(this.current.getFromSequence(i)));
			
			graphics.drawRect(x_current, y, 32, 32);
			
			Beat beat = this.current.playback(i);
			
			if (i < this.metronome.getBeatCount()) {
				
				graphics.setColor(Utils.beatColor(beat));
				
				graphics.fillRect(x_current + 8, y + 8, 16, 16);
			}
			else if (beat != Beat.White) {
				
				graphics.setColor(Utils.beatColor(beat));
				
				graphics.drawRect(x_current + 8, y + 8, 16, 16);
			}
			
			// Previous
			graphics.setColor(Utils.beatColor(this.previous.getFromSequence(i)));
			
			graphics.drawRect(x_previous, y, 32, 32);
			
			graphics.setColor(Utils.beatColor(this.previous.playback(i)));
			
			if (i < this.metronome.getBeatCount()) {
				
				graphics.fillRect(x_previous + 8, y + 8, 16, 16);
			}
			else {
				
				graphics.drawRect(x_previous + 8, y + 8, 16, 16);
			}
			
			x_current += 36;
			x_previous += 36;
		}
	}

	@Override
	public void onDestroy() {
		
	}

	@Override
	public void beat(int beatCount) {
		
		switch (this.previous.playback(beatCount)) {
		
		case Red:
			
			this.soundRed.play();
			
			break;
		
		case Blue: 
			
			this.soundBlue.play();
			
			break;
		}
	}

	@Override
	public void bar() {
		
		this.previous = current;
		this.current = new Sequence();
	}
	
	public void record(Beat beat) {
		
		this.current.record(this.metronome.getBeatCount(), beat);
	}
}
