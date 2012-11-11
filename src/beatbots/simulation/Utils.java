package beatbots.simulation;

import org.newdawn.slick.Color;

public strictfp final class Utils {

	private Utils() {
		
		super();
	}
	
	public static Color getBeatColor(Beat beat) {
		
		Color color;
		
		switch (beat) {
		
		case Red:
			
			color = Color.red;
			
			break;
			
		case Green:
			
			color = Color.green;
			
			break;
			
		case Blue:
			
			color = Color.blue;
			
			break;
			
		case Yellow: 
			
			color = Color.yellow;
			
			break;
			
		case Purple:
			
			color = Color.magenta;
			
			break;
			
		case Orange:
			
			color = Color.orange;
			
			break;

		default:
			
			color = Color.white;
			
			break;
		}
		
		return color;
	}
}
