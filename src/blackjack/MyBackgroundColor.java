package blackjack;

import java.awt.Color;
/**
 * A class specifically contains the color and the name of the color
 * 
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 *
 */
public class MyBackgroundColor extends Color {
	
	private static final long serialVersionUID = 1L;

	private String name; // color name
	
	/**
	 * Creates a color with Java color code and name
	 * @param r
	 * @param g
	 * @param b
	 * @param name
	 */
	public MyBackgroundColor(int r, int g, int b, String name) {
		super(r, g, b);
		this.name=name;
	}
	
	/**
	 * represents the color in String
	 */
	@Override
	public String toString() {
		return name;
	}
	
	
	
}
