package model;
/**
 * Model to store a point's coordinates on a 2D plane
 */
public class point2D {
	
	/**
	 * x and y component of coordinate
	 */
	public int x, y;
	
	/**
	 * Constructor stores the coordinate components
	 * 
	 * @param c column coordinate
	 * @param r row coordinate
	 */
	public point2D(int c, int r) {
		x = c;
		y = r;
	}

}
