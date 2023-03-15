package controller;

import model.StageModel;
import model.point2D;


/**
 * Controller for transformations.
 */
public class TransformController {
	
	/**
	 * Rotate a point around another with an angle.
	 * 
	 * @param point point to rotate
	 * @param center center of the rotation
	 * @param rad angle radix
	 * @return transformed point
	 */
	private point2D rotatePointAround(point2D point, point2D center, double rad){
		int x = (int) Math.round(Math.cos(rad)) * (point.x-center.x) - (int) Math.round(Math.sin(rad)) * (point.y-center.y) + center.x;
		int y = (int) Math.round(Math.sin(rad)) * (point.x-center.x) + (int) Math.round(Math.cos(rad)) * (point.y-center.y) + center.y;
		return new point2D(x, y);
	}
	
	/**
	 * Rotates a whole matrix of a {@link StageModel} descendant around specified center with specified angle.
	 * 
	 * @param origModel StageModel descendant to rotate
	 * @param center center of rotation
	 * @param rad angle radix
	 */
	private void rotateMatrix(StageModel origModel, point2D center, double rad){
		point2D origDims = origModel.getDimensions();
		int[][] data = origModel.getMatrix();
		int[][] newMatrix = new int[origDims.y][origDims.x];
		point2D[] origConstraints = origModel.getConstraints();
		
		for(int y = origConstraints[0].y; y <= origConstraints[1].y; y++ ){
			for(int x = origConstraints[0].x; x <= origConstraints[1].x; x++){
				if(data[y][x] > 0){
					point2D transformed = rotatePointAround(new point2D(x, y), center, rad);
					newMatrix[transformed.y][transformed.x] = 1;
				}
			}
		}
		
		origModel.setMatrix(newMatrix);	
	}
	
	/**
	 * Copies a part of a {@link StageModel} descendant.
	 * 
	 * @param origModel StageModel descendant to copy from
	 * @param topLeft top left coordinate of the area to copy
	 * @param bottomRight bottom right coordinate of the area to copy
	 * @return StageModel containing copied part
	 */
	private StageModel copyArea(StageModel origModel, point2D topLeft, point2D bottomRight){
		int[][] copyedData = new int[(bottomRight.y-topLeft.y)+1][(bottomRight.x-topLeft.x)+1];
		int[][] data = origModel.getMatrix();
		for(int y=topLeft.y;y<=bottomRight.y;y++){
			for(int x=topLeft.x;x<=bottomRight.x;x++){
				copyedData[y][x]=data[y][x];
			}
		}
		StageModel copy = new StageModel(copyedData[0].length, copyedData.length);
		copy.setMatrix(copyedData);
		return copy;
	}
	
	/**
	 * Do a 90° counterclockwise rotation on a {@link StageModel} descendant.
	 * 
	 * @param origModel model to rotate
	 * @param centerPoint rotation center coordinates
	 */
	public void rotateLeft(StageModel origModel, point2D centerPoint){
		rotateMatrix(origModel, centerPoint, -Math.PI/2);
	}
	
	/**
	 * Do a 90° clockwise rotation on a {@link StageModel} descendant.
	 * 
	 * @param origModel model to rotate
	 * @param centerPoint rotation center coordinates
	 */
	public void rotateRight(StageModel origModel, point2D centerPoint){
		rotateMatrix(origModel, centerPoint, Math.PI/2);
	}
	
	/**
	 * Removes a row from a {@link StageModel} descendant.
	 * 
	 * @param origModel StageModel to remove from
	 * @param rowIndex row index to remove
	 */
	public void removeRow(StageModel origModel, int rowIndex){
		point2D stageDim = origModel.getDimensions();
		StageModel copy = copyArea(origModel,new point2D(0, 0),new point2D(stageDim.x-1, rowIndex-1));
		origModel.clearArea(new point2D(0, 0), new point2D(stageDim.x-1, rowIndex));
		origModel.loadArea(0, 1, copy,false);
	}
}
