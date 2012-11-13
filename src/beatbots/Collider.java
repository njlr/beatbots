package beatbots;

import org.newdawn.slick.geom.Vector2f;

public interface Collider extends Entity {
	
	float getRadius();
	
	Vector2f getPosition();
	
	void handle(Collider other);
}
