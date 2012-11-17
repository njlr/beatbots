package beatbots.simulation;

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
	
	public EntityManager() {
		
		super();
		
		this.entities = new ArrayList<Entity>();
		
		this.entitiesToAdd = new LinkedList<Entity>();
		this.entitiesToRemove = new LinkedList<Entity>();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		this.addAnyEntities(container);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		this.addAnyEntities(container);
		
		for (Entity entity : this.entities) {
			
			if (entity.isActive()) {
				
				entity.update(container, delta);
			}
			else {
				
				this.entitiesToRemove.add(entity);
			}
		}
		
		while (!this.entitiesToRemove.isEmpty()) {
			
			Entity entity = this.entitiesToRemove.remove();
			
			entity.destroy(container);
			
			this.entities.remove(entity);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		
		for (Entity entity : this.entities) {
			
			if (entity.isActive()) {
				
				entity.render(container, graphics);
			}
		}
	}
	
	@Override
	public void deactivate() {
		
	}

	@Override
	public void destroy(GameContainer gameContainer) {
		
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
