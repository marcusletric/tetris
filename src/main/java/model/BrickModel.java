package model;

import controller.TransformController;

/**
 * Model for storing bricks.
 * 
 * extends {@link StageModel} class, implements {@link ScreenModel} interface
 */
public class BrickModel extends StageModel implements ScreenModel {
	
	/**
	 * Center point coordinates.
	 */
	private point2D centerPoint;
	
	/**
	 * Maximum amount of 90° clockwise rotations.
	 */
	private int maxRotation;
	
	/**
	 * Current amount of 90° clockwise rotations.
	 */
	private int currRotation;
	
	/**
	 * Transform controller instance.
	 */
	private TransformController trns = new TransformController();
	
	/**
	 * Constructor, initializes the model.
	 * 
	 * @param brickPixels integer matrix containing the pixel states
	 * @param center {@link point2D} center point of the pixel matrix
	 * @param maxRot integer holding maximum amount of 90° clockwise rotations
	 */
	public BrickModel(int[][] brickPixels, point2D center, int maxRot) {
		super(brickPixels.length, brickPixels[0].length);
		this.centerPoint = center;
		this.maxRotation = maxRot;
		this.currRotation = 0;
		setMatrix(brickPixels);
	}
	
	/**
	 * Getter of center point.
	 * 
	 * @return center point
	 */
	public point2D getCenter(){
		return centerPoint;
	}

	/**
	 * Do a 90° rotation clockwise if it is allowed.
	 * 
	 * else restore the original rotation
	 */
	public void rotateCW(){
		if(currRotation < maxRotation && maxRotation > 0){
			trns.rotateRight(this, this.centerPoint);
			this.currRotation++;
		} else {
			for(int i=0;i<maxRotation;i++){
				rotateCCW();
			}
		}
	}
	
	/**
	 * Do a 90° rotation counterclockwise if it is allowed.
	 * 
	 * else restore the original rotation
	 */
	public void rotateCCW(){
		if(currRotation > 0 && maxRotation > 0){
			trns.rotateLeft(this, this.centerPoint);
			this.currRotation--;
		} else {
			for(int i=0;i<maxRotation;i++){
				rotateCW();
			}
		}
	}
	
	/**
	 * Setter for current amount of clockwise 90° rotations.
	 * 
	 * @param currRotation current amount of rotations
	 */
	public void setCurrRotation(int currRotation) {
		this.currRotation = currRotation;
	}

	/**
	 * Clone actual instance to a new one.
	 * 
	 * @return copy of current instance
	 */
	public BrickModel clone(){
		BrickModel clone = new BrickModel(data, centerPoint, maxRotation);
		clone.setCurrRotation(currRotation);
		return clone;
	}
	
}
