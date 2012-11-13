package beatbots;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public strictfp final class WaveManager implements Entity, MetronomeListener {

	private Metronome metronome;
	private EntityManager entityManager;
	
	private String mapReference;
	
	private int line;
	
	private TiledMap map;
	
	@Override
	public boolean isActive() {
		
		return true;
	}

	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	public WaveManager(Metronome metronome, EntityManager entityManager, String mapReference) {
		
		super();
		
		this.metronome = metronome;
		this.entityManager = entityManager;
		
		this.mapReference = mapReference;
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.line = 0;
		
		this.map = new TiledMap(this.mapReference);
		
		this.metronome.addListener(this);
		
		this.spawnWave();
	}

	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
	}

	@Override
	public void onDestroy() {
		
	}

	@Override
	public void beat(int beatCount) {
		
	}

	@Override
	public void bar() {
		
		this.line++;
		
		this.spawnWave();
	}
	
	private void spawnWave() {
		
		if (this.line < this.map.getHeight()) {
			
			for (int l = 0; l < this.map.getLayerCount(); l++) {
				
				for (int x = 0; x < this.map.getWidth(); x++) {
					
					int beatValue = Integer.parseInt(this.map.getTileProperty(this.map.getTileId(x, (this.map.getHeight() - 1) - this.line, l), "Beat", "-1"));
					
					if (beatValue > -1) {
						
						Beat beat = Utils.beat(beatValue);
						
						Vector2f v = new Vector2f((x + 0.5f) * this.map.getTileWidth(), 0.5f * this.map.getTileHeight());
						
						if (beat == Beat.White) {
							
							this.entityManager.addEntity(new BeatBot(v));
						}
						else {
							
							this.entityManager.addEntity(new BeatBotColored(v, this.entityManager, beat));
						}
					}
				}
			}
		}
	}
}
