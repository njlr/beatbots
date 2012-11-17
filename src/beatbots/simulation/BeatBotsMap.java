package beatbots.simulation;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public strictfp final class BeatBotsMap implements Entity {
	
	private EntityManager entityManager;
	private Metronome metronome;
	private Sequence sequence;
	
	private String mapReference;
	
	private boolean isActive;

	@Override
	public boolean isActive() {
		
		return this.isActive;
	}
	
	public BeatBotsMap(EntityManager entityManager, Metronome metronome, Sequence sequence, String mapReference) {
		
		super();
		
		this.entityManager = entityManager;
		this.metronome = metronome;
		this.sequence = sequence;
		
		this.mapReference = mapReference;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.isActive = true;
		
		TiledMap tiledMap = new TiledMap(this.mapReference);
		
		// Create the nodes
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
									
									this.entityManager.addEntity(new Speaker(this.sequence, node));
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
									
									this.entityManager.addEntity(new Speaker(this.sequence, node));
								}
								
								change = true;
							}
						}
					}
					else if (type.equals("Switch")) {
						
						String name = tiledMap.getObjectName(g, i);
						
						if (!nodes.containsKey(name)) {
							
							String successorA = tiledMap.getObjectProperty(g, i, "SuccessorA", "");
							String successorB = tiledMap.getObjectProperty(g, i, "SuccessorB", "");
							
							if (nodes.containsKey(successorA) && nodes.containsKey(successorB)) {
								
								float x = tiledMap.getObjectX(g, i);
								float y = tiledMap.getObjectY(g, i);
								
								Switch switchNode = new Switch(new Vector2f(x, y), nodes.get(successorA), nodes.get(successorB));
								
								nodes.put(name, switchNode);
								
								change = true;
							}
						}
					}
				}
			}
		}
		
		for (Node node : nodes.values()) {
			
			this.entityManager.addEntity(node);
		}
		
		// Create the rest
		for (int g = 0; g < tiledMap.getObjectGroupCount(); g++) {
			
			for (int i = 0; i < tiledMap.getObjectCount(g); i++) {
				
				String type = tiledMap.getObjectType(g, i);
				
				if (type.equals("Colorizer")) {
					
					int c = Integer.parseInt(tiledMap.getObjectProperty(g, i, "Color", "0"));
					
					float x = tiledMap.getObjectX(g, i);
					float y = tiledMap.getObjectY(g, i);
					
					this.entityManager.addEntity(new Colorizer(this.entityManager, Utils.getNoteColor(c), new Vector2f(x, y)));
				}
				else if (type.equals("Alternator")) {
					
					float x = tiledMap.getObjectX(g, i);
					float y = tiledMap.getObjectY(g, i);
					
					this.entityManager.addEntity(new Alternator(this.entityManager, new Vector2f(x, y)));
				}
			}
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
