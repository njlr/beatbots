package beatbots;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EntityManager implements Entity {
	
	private List<Entity> entities;
	
	private Queue<Entity> entitiesToAdd;
	private Queue<Entity> entitiesToRemove;
	
	@Override
	public boolean isActive() {
		
		return true;
	}
	
	@Override
	public boolean isReusable() {
		
		return false;
	}
	
	public EntityManager() {
		
		super();
		
		this.entities = new ArrayList<Entity>();
		
		this.entitiesToAdd = new LinkedList<Entity>();
		
		this.entitiesToRemove = new LinkedList<Entity>();
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		
		this.addAnyEntities(gameContainer);
	}

	@Override
	public void update(GameContainer gameContainer, int delta) throws SlickException {
		
		this.addAnyEntities(gameContainer);
		
		for (Entity entity : this.entities) {
			
			if (entity.isActive()) {
				
				entity.update(gameContainer, delta);
			}
			else {
				
				if (!entity.isReusable()) {
					
					this.entitiesToRemove.add(entity);
				}
			}
		}
		
		while (!this.entitiesToRemove.isEmpty()) {
			
			Entity entity = this.entitiesToRemove.remove();
			
			entity.onDestroy();
			
			this.entities.remove(entity);
		}
		
		for (int i = 0; i < this.entities.size(); i++) {
			
			Entity e = this.entities.get(i);
			
			if (e.isActive()) {
				
				if (e instanceof Collider) {
					
					Collider a = (Collider) e;
					
					for (int j = i + 1; j < this.entities.size(); j++) {
						
						Entity f = this.entities.get(j);
						
						if (f.isActive()) {
							
							if (f instanceof Collider) {
								
								Collider b = (Collider) f;
								
								if (Utils.checkCollision(a, b)) {
									
									a.handle(b);
									b.handle(a);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) {
		
		for (Entity entity : this.entities) {
			
			if (entity.isActive()) {
				
				entity.render(gameContainer, graphics);
			}
		}
	}
	
	@Override
	public void onDestroy() {
		
	}
	
	public void addEntity(Entity entity) {
		
		this.entitiesToAdd.add(entity);
	}
	
	private void addAnyEntities(GameContainer gameContainer) throws SlickException {
		
		while (!this.entitiesToAdd.isEmpty()) {
			
			Entity entity = this.entitiesToAdd.remove();
			
			entity.init(gameContainer);
			
			this.entities.add(entity);
		}
	}
}
