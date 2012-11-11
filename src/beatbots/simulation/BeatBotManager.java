package beatbots.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public strictfp final class BeatBotManager {
	
	private BeatMachine beatMachine;
	private BulletManager bulletManager;
	
	private List<BeatBot> beatBots;
	private Stack<BeatBot> beatBotsToRemove;
	
	public BeatBotManager(BeatMachine beatMachine, BulletManager bulletManager) {
		
		super();
		
		this.beatMachine = beatMachine;
		this.bulletManager = bulletManager;
		
		this.beatBots = new ArrayList<BeatBot>();
		this.beatBotsToRemove = new Stack<BeatBot>();
	}
	
	public void init(GameContainer gameContainer) {
		
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		for (BeatBot i : this.beatBots) {
			
			if (i.isActive()) {
				
				i.update(gameContainer, delta);
			}
			else {
				
				this.beatBotsToRemove.add(i);
			}
		}
		
		while (!this.beatBotsToRemove.isEmpty()) {
			
			this.beatBots.remove(this.beatBotsToRemove.pop());
		}
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		for (BeatBot i : this.beatBots) {
			
			if (i.isActive()) {
				
				i.render(gameContainer, graphics);
			}
		}
	}
	
	public void spawn(GameContainer gameContainer, BeatBotType beatBotType, Vector2f startPosition) throws SlickException {
		
		switch (beatBotType) {
		
		case One:
			
			BeatBotOne beatBotOne = new BeatBotOne(this.beatMachine, this.bulletManager, startPosition);
			
			this.beatBots.add(beatBotOne);
			
			beatBotOne.init(gameContainer);
			
			break;
		}
	}
}
