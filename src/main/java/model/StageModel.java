package model;

/**
 * Screen model implementation for holding pixels on stage. 
 */
public class StageModel implements ScreenModel{
	
	protected int[][] data;
	protected point2D topConstraint, bottomConstraint;
	protected int dataWidth, dataHeight;
	
	/**
	 * Constructor.
	 * 
	 * @param cols width of the matrix 
	 * @param rows height of the matrix
	 */
	public StageModel(int cols, int rows){
		initModel(cols ,rows);
	}
	
	/**
	 * Initialize the model.
	 * Create an integer matrix and reset constraints.
	 * 
	 * @param cols width of the matrix 
	 * @param rows height of the matrix
	 */
	public void initModel(int cols, int rows) {
		data = new int[rows][cols];
		dataWidth = cols;
		dataHeight = rows;
		resetConstraints();
	}

	/**
	 * Reset the constraints.
	 */
	public void resetConstraints(){
		bottomConstraint = new point2D(-1, -1);
		topConstraint = new point2D(dataWidth, dataHeight);
	}
	
	/**
	 * Calculate inner constraints.
	 */
	private void calculateConstraints(){
		for(int y = 0; y < data.length; y++){
			for(int x = 0; x < data[0].length; x++){
				if(data[y][x]>0){
					setConstraints(new point2D(x, y));
				}
			}
		}
	}
	
	/**
	 * Set the constraints according to a visible pixel.
	 * 
	 * @param pixelCoords visible pixel coordinates
	 */
	public void setConstraints(point2D pixelCoords){
		if(topConstraint.x > pixelCoords.x){
			topConstraint.x = pixelCoords.x;
		}
		if(topConstraint.y > pixelCoords.y){
			topConstraint.y = pixelCoords.y;
		}
		if(bottomConstraint.x < pixelCoords.x){
			bottomConstraint.x = pixelCoords.x;
		}
		if(bottomConstraint.y < pixelCoords.y){
			bottomConstraint.y = pixelCoords.y;
		}
	}
	
	/**
	 * Getter for constraints.
	 * 
	 * @return array of top left and bottom right constraint coordinates
	 */
	public point2D[] getConstraints(){
		point2D[] retArray = new point2D[2];
		retArray[0] = topConstraint;
		retArray[1] = bottomConstraint;
		return retArray;
	}
	
	/**
	 * Load a {@link ScreenModel} descendant's matrix.
	 * 
	 * @param model ScreenModel to use for the load
	 */
	public void load(ScreenModel model) {
		resetConstraints();
		int[][] matrix = model.getMatrix();
		if(matrix.length==data.length && matrix[0].length == data[0].length){
			for(int y = 0; y < matrix.length; y++){
				for(int x = 0; x < matrix[0].length; x++){
					data[y][x] = matrix[y][x];
					if(matrix[y][x]>0){
						setConstraints(new point2D(x, y));
					}
				}
			}
		}
	}
	
	
	/**
	 * Load a {@link ScreenModel} descendant's matrix starting by specified coordinates.
	 * 
	 * @param x the x coordinate of starting point
	 * @param y the y coordinate of starting point
	 * @param model the model to load from
	 * @param overlay if <code>true</code> then the loaded matrix will be handled as a transparent one
	 */
	public void loadArea(int x, int y, ScreenModel model, boolean overlay) {
		int[][] matrix = model.getMatrix();
		point2D[] constraints = model.getConstraints();
		int startX = x + constraints[0].x;
		int startY = y + constraints[0].y;
		if(startX < 0){
			startX = 0;
		}
		if(startY < 0){
			constraints[0].y += Math.abs(startY);
			startY = 0;
		}
		if( x + constraints[1].x <= data[0].length && 
			y + constraints[1].y <= data.length ){
			int r = constraints[0].y;
			for(int j = startY; j <= y + constraints[1].y; j++){
				int c = constraints[0].x;
				for(int i = startX; i <= x + constraints[1].x; i++){
					if(!overlay || matrix[r][c]==1){	
						data[j][i] = matrix[r][c];
					}
					c++;
				}
				r++;
			}
		}
	}
	
	/**
	 * Do a composition of current and source {@link ScreenModel} by overlaying it.
	 * 
	 * @param source ScreenModel to overlay
	 * @param position starting position
	 */
	public void compose(ScreenModel source, point2D position){
		loadArea(position.x,position.y,source,true);
	}
	
	/**
	 * Clears the matrix.
	 */
	public void clear() {
		for(int i = 0; i < data[0].length; i++){
			for(int j = 0; j < data.length; j++){
				data[j][i] = 0;
			}
		}
	}
	
	/**
	 * Clear matrix area (set to 0).
	 * 
	 * @param topLeft top left coordinates of area to clear
	 * @param bottomRight bottom right coordinates of area to clear 
	 */
	public void clearArea(point2D topLeft, point2D bottomRight) {
		for(int j = topLeft.y; j <= bottomRight.y; j++){
			for(int i = topLeft.x; i <= bottomRight.x; i++){
				data[j][i] = 0;
			}
		}
	}

	/**
	 * Getter for the matrix data.
	 * 
	 * @return integer matrix
	 */
	public int[][] getMatrix() {
		return data;
	}
	
	/**
	 * Fills the matrix data with the specified one.
	 * 
	 * @param matrix data to fill
	 */
	public void setMatrix(int[][] matrix) {
		resetConstraints();
		for(int j = 0; j < data.length; j++){
			for(int i = 0; i < data[0].length; i++){
				data[j][i] = matrix[j][i];
			}
		}
		calculateConstraints();
	}
	
	/**
	 * Getter for the dimensions of the matrix.
	 * 
	 * @return Point containing width and height of the matrix
	 */
	public point2D getDimensions(){
		return new point2D(dataWidth, dataHeight);
	}

	@Override
	public String toString() {
		String str = "";
		for(int i=0;i<dataHeight;i++){
			for(int j=0;j<dataWidth;j++){
				str += data[j][i];
			}
			str+="\n";
		}
		return str;
	}
	
	
}
