package blackjack;

/**
 * Contains the x and y coordinates of anything
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 */
public class Position {
	private int x; // x position on play board
	private int y; // y position on play board
	
	/**
	 * Constructor of Position Class , creates position x and y
	 * @param x
	 * @param y
	 */
	Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * get x coordinate value
	 * @return integer
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * set x coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * get Y coordinate
	 * @return integer
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * set Y position
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
