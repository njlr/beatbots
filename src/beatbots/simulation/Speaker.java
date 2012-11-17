package beatbots.simulation;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public class Speaker implements Entity, MetronomeListener {
	
	private Metronome metronome;
	private Node node;
	
	private boolean isActive;
	
	private Queue<NoteColor> noteColor; 
	
	private Sound soundRed;
	private Sound soundBlue;
	private Sound soundYellow;
	private Sound soundMagenta;
	private Sound soundOrange;
	private Sound soundGreen;
	
	@Override
	public boolean isActive() {
		
		return this.isActive;
	}

	public Speaker(Metronome metronome, Node node) {
		
		super();
		
		this.metronome = metronome;
		this.node = node;
		
		this.noteColor = new ConcurrentLinkedQueue<NoteColor>();
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
		
		this.noteColor.clear();
		
		this.soundRed = new Sound("assets/sfx/Red.wav");
		this.soundBlue = new Sound("assets/sfx/Blue.wav");
		this.soundYellow = new Sound("assets/sfx/Yellow.wav");
		this.soundMagenta = new Sound("assets/sfx/Magenta.wav");
		this.soundOrange = new Sound("assets/sfx/Orange.wav");
		this.soundGreen = new Sound("assets/sfx/Green.wav");
		
		this.metronome.addListener(this);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		if (this.node.isActive()) {
			
			while (!this.node.getNotes().isEmpty()) {
				
				this.noteColor.add(this.node.getNotes().remove().getNoteColor());
			}
		}
		else {
			
			this.deactivate();
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		graphics.setColor(Color.magenta);
		
		graphics.drawOval(
				this.node.getPosition().getX() - 4f, 
				this.node.getPosition().getY() - 4f, 
				8f, 
				8f);
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}
	
	@Override
	public void destroy(GameContainer gameContainer) {
		
		this.metronome.removeListener(this);
		
		this.noteColor.clear();
	}
	
	@Override
	public void beat(int beatCount) {
		
		if (!this.noteColor.isEmpty()) {
			
			switch (this.noteColor.remove()) {
			
			case Red: 
				
				this.soundRed.play();
				
				break;
				
			case Blue: 
				
				this.soundBlue.play();
				
				break;
				
			case Yellow: 
				
				this.soundYellow.play();
				
				break;
				
			case Magenta: 
				
				this.soundMagenta.play();
				
				break;
				
			case Orange:
				
				this.soundOrange.play();
				
				break;
				
			case Green:
				
				this.soundGreen.play();
				
				break;
			}
		}
	}

	@Override
	public void bar() {
		
	}
}
