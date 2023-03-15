package model;

/**
 * Interface for storing pixel matrices.
 */
public interface ScreenModel {
	
	/**
	 * Initialize an integer matrix.
	 * 
	 * @param cols width of the matrix
	 * @param rows height of the matrix
	 */
	public void initModel(int cols, int rows);
	
	/**
	 * Getter for the constraints of the visible pixels in the matrix.
	 * 
	 * @return array of Point2D top left and bottom right point
	 */
	public point2D[] getConstraints();
	
	/**
	 * Load a {@link ScreenModel} descendant's matrix.
	 * 
	 * @param model ScreenModel to use for the load
	 */
	public void load(ScreenModel model);
	
	/**
	 * Load a {@link ScreenModel} descendant's matrix starting by specified coordinates.
	 * 
	 * @param x the x coordinate of starting point
	 * @param y the y coordinate of starting point
	 * @param model the model to load from
	 * @param overlay if <code>true</code> then the loaded matrix will be handled as a transparent one
	 */
	public void loadArea(int x, int y, ScreenModel model, boolean overlay);
	
	/**
	 * Clears the matrix.
	 */
	public void clear();
	
	/**
	 * Fills the matrix data with the specified one.
	 * 
	 * @param matrix data to fill
	 */
	public void setMatrix(int[][] matrix);
	
	/**
	 * Getter for the matrix data.
	 * 
	 * @return integer matrix
	 */
	public int[][] getMatrix();
	
	/**
	 * Getter for the dimensions of the matrix.
	 * 
	 * @return Point containing width and height of the matrix
	 */
	public point2D getDimensions();
	
}
