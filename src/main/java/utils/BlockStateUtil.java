package utils;

import java.util.ArrayList;
import java.util.Collection;

import model.BrickModel;
import model.StageModel;
import model.point2D;


/** 
 * Utility for collision and full row check.
 */
public class BlockStateUtil {
	
	/**
	 * Check if a movable brick at specified position collides static pixels  .
	 * 
	 * @param nextPos position for the brick
	 * @param brick {@link BrickModel} to check
	 * @param stage {@link StageModel} to check against
	 * @return <code>true</code> if brick is intact <code>false</code> otherwise
	 */
	public boolean willCollideBlock(point2D nextPos, BrickModel brick, StageModel stage){
		int[][] matrix = brick.getMatrix();
		int[][] data = stage.getMatrix();
		point2D[] constraints = brick.getConstraints();
		int startX = nextPos.x + constraints[0].x;
		int startY = nextPos.y + constraints[0].y;

		if(nextPos.y + constraints[1].y >= data.length){
			return true;
		}
		
		if(startX >= 0 && startY >= 0 &&
			nextPos.x + constraints[1].x <= data[0].length && 
			nextPos.y + constraints[1].y <= data.length ){
			int r = constraints[0].y;
			for(int j = startY; j <= nextPos.y + constraints[1].y; j++){
				int c = constraints[0].x;
				for(int i = startX; i <= nextPos.x + constraints[1].x; i++){
					if(data[j][i] == matrix[r][c] && data[j][i] == 1){
						return true;
					}
					c++;
				}
				r++;
			}
		}
		return false;
	}
	
	/**
	 * Check if brick collides wall of a {@link StageModel} wall.
	 * 
	 * @param nextPos position of the brick
	 * @param brick {@link BrickModel} to check
	 * @param stage StageModel to check against
	 * @return <code>true</code> if brick collides wall <code>false</code> otherwise
	 */
	public int willCollideWall(point2D nextPos, BrickModel brick, StageModel stage){
		int[][] data = stage.getMatrix();
		point2D[] constraints = brick.getConstraints();

		if(nextPos.x + constraints[1].x > data[0].length-1){
			return 2;
		}
		
		if(nextPos.x + constraints[0].x < 0){
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * Check for full rows in a {@link StageModel}.
	 * 
	 * @param stage StageModel to check
	 * @return Collection of integer indexes of full rows
	 */
	public Collection<Integer> getFullRows(StageModel stage){
		Collection<Integer> fullRows = new ArrayList<Integer>();
		int[][] data = stage.getMatrix();
		
		for(int y=0;y<data.length;y++){
			for(int x=0;x<data[0].length && data[y][x] > 0;x++){
				if(x == data[0].length-1){
					fullRows.add(y);
				}
			}
		}
		return fullRows;
	}
}
