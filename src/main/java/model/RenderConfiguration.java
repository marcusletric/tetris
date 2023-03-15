package model;

/**
 * Model to store render configuration.
 */
public class RenderConfiguration {
	
	/**
	 * Rendering canvas resolution in pixels.
	 */
	private point2D screenResolution = new point2D(429, 600);
	
	/**
	 * Padding between stage squares in pixels.
	 */
	private int squarePadding = 3;
	
	/**
	 * Getter for screen resolution.
	 * 
	 * @return screen resolution
	 */
	public point2D getScreenResolution() {
		return screenResolution;
	}
	
	/**
	 * Getter for padding.
	 * 
	 * @return padding in pixels
	 */
	public int getSquarePadding() {
		return squarePadding;
	}
	
	

}
