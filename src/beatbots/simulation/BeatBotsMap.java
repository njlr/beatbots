package beatbots.simulation;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class BeatBotsMap implements Entity {
	
	private EntityManager entityManager;
	private Metronome metronome;
	
	private String mapReference;
	
	private boolean isActive;

	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public BeatBotsMap(EntityManager entityManager, Metronome metronome, String mapReference) {
		
		super();
		
		this.entityManager = entityManager;
		this.metronome = metronome;
		
		this.mapReference = mapReference;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
		
		TiledMap tiledMap = new TiledMap(this.mapReference);
		
		boolean change = true;
		
		Map<String, Node> nodes = new HashMap<String, Node>();
		
		while (change) {
			
			change = false;
			
			for (int g = 0; g < tiledMap.getObjectGroupCount(); g++) {
				
				for (int i = 0; i < tiledMap.getObjectCount(g); i++) {
					
					String type = tiledMap.getObjectType(g, i);
					
					if (type.equals("Node") || type.equals("Generator") || type.equals("Speaker")) {
						
						String name = tiledMap.getObjectName(g, i);
						
						if (!nodes.containsKey(name)) {
							
							String successor = tiledMap.getObjectProperty(g, i, "Successor", "");
							
							float x = tiledMap.getObjectX(g, i);
							float y = tiledMap.getObjectY(g, i);
							
							if (successor.equals("")) {
								
								Node node = new Node(new Vector2f(x, y));
								
								nodes.put(name, node);
								
								if (type.equals("Generator")) {
									
									this.entityManager.addEntity(new Generator(this.entityManager, this.metronome, node));
								}
								else if (type.equals("Speaker")) {
									
									this.entityManager.addEntity(new Speaker(this.metronome, node));
								}
								
								change = true;
							}
							else if (nodes.containsKey(successor)) {
								
								Node node = new Node(new Vector2f(x, y), nodes.get(successor));
								
								nodes.put(name, node);
								
								if (type.equals("Generator")) {
									
									this.entityManager.addEntity(new Generator(this.entityManager, this.metronome, node));
								}
								else if (type.equals("Speaker")) {
									
									this.entityManager.addEntity(new Speaker(this.metronome, node));
								}
								
								change = true;
							}
						}
					}
					else if (type.equals("Colorizer")) {
						
						int c = Integer.parseInt(tiledMap.getObjectProperty(g, i, "Color", "0"));
						
						float x = tiledMap.getObjectX(g, i);
						float y = tiledMap.getObjectY(g, i);
						
						this.entityManager.addEntity(new Colorizer(this.entityManager, Utils.getNoteColor(c), new Vector2f(x, y)));
					}
				}
			}
		}
		
		for (Node node : nodes.values()) {
			
			this.entityManager.addEntity(node);
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
	}

	@Override
	public void deactivate() {
		
		this.isActive = false;
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
	}
}
