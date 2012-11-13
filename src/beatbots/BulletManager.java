package beatbots;

import org.newdawn.slick.geom.Vector2f;

public strictfp final class BulletManager {
	
	private static final int MAX_BULLET_COUNT = 1024;
	
	public static Bullet[] BULLETS;
	
	private BulletManager() {
		
		super();
	}
	
	public static void init(EntityManager entityManager) {
		
		BULLETS = new Bullet[MAX_BULLET_COUNT];
		
		for (int i = 0; i < MAX_BULLET_COUNT; i++) {
			
			BULLETS[i] = new Bullet();
			
			entityManager.addEntity(BULLETS[i]);
		}
	}
	
	public static void fire(Vector2f position, Vector2f direction) {
		
		for (int i = 0; i < BULLETS.length; i++) {
			
			if (!BULLETS[i].isActive()) {
				
				BULLETS[i].fire(position, direction);
				
				return;
			}
		}
	}
}
