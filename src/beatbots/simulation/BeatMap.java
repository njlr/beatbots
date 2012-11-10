package beatbots.simulation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BeatMap {
	
	private String mapFile;
	
	private TiledMap map;
	
	private BeatColor[][] beatColors;
	
	public BeatMap(String mapFile) {
		
		super();
		
		this.mapFile = mapFile;
	}
	
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.map = new TiledMap(this.mapFile);
		
		this.beatColors = new BeatColor[this.map.getWidth()][this.map.getHeight()];
		
		for (int l = 0; l < this.map.getLayerCount(); l++) {
			
			for (int x = 0; x < this.map.getWidth(); x++) {
				
				for (int y = 0; y < this.map.getHeight(); y++) {
					
					int tid = this.map.getTileId(x, y, l);
					
					int beat = Integer.parseInt(this.map.getTileProperty(tid, "Beat", "0"));
					
					switch (beat) {
						
					case 1: 
						
						this.beatColors[x][y] = BeatColor.Red;
						
						break;
						
					case 2: 
						
						this.beatColors[x][y] = BeatColor.Green;
						
						break;
						
					case 3: 
						
						this.beatColors[x][y] = BeatColor.Blue;
						
						break;
						
					default:
						
						this.beatColors[x][y] = BeatColor.None;
						
						break;
					}
				}
			}
		}
	}
	
	public void update(GameContainer gameContainer, int delta) {
		
		
	}
	
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		this.map.render(0, 0);
	}
}
