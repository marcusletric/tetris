package model;

/**
 * Game configuration holder.
 */
public class GameConfiguration {
	
	/**
	 * Maximum width and height of brick in pixels.
	 */
	private int maxBrickSize = 4;
	
	/**
	 * Center point of the brick matrix.
	 */
	private point2D brickCenter = new point2D(1, 1);
	
	/**
	 * Dimensions of the playing stage.
	 */
	private point2D stageDimensions = new point2D(10, 20);
	
	/**
	 * Getter for brick center.
	 * 
	 * @return brick center point
	 */
	public point2D getBrickCenter() {
		return brickCenter;
	}
	
	/**
	 * Getter for maximum brick width / height.
	 * 
	 * @return the maximum brick size
	 */
	public int getMaxBrickSize() {
		return maxBrickSize;
	}
	
	/**
	 * Getter for stage dimensions.
	 * 
	 * @return the dimensions of the playing stage
	 */
	public point2D getStageDimensions() {
		return stageDimensions;
	}
	
	
}
