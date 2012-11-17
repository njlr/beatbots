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
	private String mapReference;
	
	private boolean isActive;

	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public BeatBotsMap(EntityManager entityManager, String mapReference) {
		
		super();
		
		this.entityManager = entityManager;
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
					
					if (type.equals("Node")) {
						
						String name = tiledMap.getObjectName(g, i);
						
						if (!nodes.containsKey(name)) {
							
							String successor = tiledMap.getObjectProperty(g, i, "Successor", "");
							
							float x = tiledMap.getObjectX(g, i);
							float y = tiledMap.getObjectY(g, i);
							
							if (successor.equals("")) {
								
								nodes.put(name, new Node(new Vector2f(x, y)));
								
								change = true;
							}
							else {
								
								if (nodes.containsKey(successor)) {
									
									nodes.put(name, new Node(new Vector2f(x, y), nodes.get(successor)));
									
									change = true;
								}
							}
						}
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
