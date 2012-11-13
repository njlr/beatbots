package beatbots;

import org.newdawn.slick.Color;

public strictfp final class Utils {

	private Utils() {
		
		super();
	}
	
	public static Beat beat(int index) {
		
		switch (index) {
		
		default:
		
		case 0:
			
			return Beat.White;
			
		case 1: 
			
			return Beat.Red;
			
		case 2:
			
			return Beat.Green;
				
		case 3: 
			
			return Beat.Blue;
		}
	}
	
	public static Color beatColor(Beat beat) {
		
		switch (beat) {
		
		case Red:
			
			return Color.red;
			
		case Green:
			
			return Color.green;
			
		case Blue:
			
			return Color.cyan;

		default:
			
			return Color.white;
		}
	}
	
	public static boolean checkCollision(Collider a, Collider b) {
		
		float r = a.getRadius() + b.getRadius();
		
		return (a.getPosition().distanceSquared(b.getPosition()) <= Math.pow(r, 2));
	}
}
