package controller;

import java.util.Collection;

import model.GameModel;
import model.point2D;

/**
 * Controller for stage model modification.
 */
public class StageController {
	
	/**
	 * TransformController instance.
	 */
	private TransformController trnsCtrl = new TransformController();
	
	
	/**
	 * Initialize models for a new game.
	 * 
	 * @param gameModel model to initialize
	 */
	public void initModels(GameModel gameModel){
		gameModel.reset();
		gameModel.nextBrick = gameModel.bricks.getRandomBrick();
		addNewBrick(gameModel);
	}
	
	/**
	 * Remove rows from the screen.
	 * 
	 * @param gameModel model to modify
	 * @param rows Integer collection of row indexes to remove
	 */
	public void removeRows(GameModel gameModel, Collection<Integer> rows){
		for (Integer lineIndex : rows) {
			trnsCtrl.removeRow(gameModel.stageModel, lineIndex);
			gameModel.removedLines+=1;
			gameModel.score += (20 * gameModel.level);
		}
	}
	
	/**
	 * Add a new movable brick to the stage.
	 * 
	 * @param gameModel model to modify
	 */
	public void addNewBrick(GameModel gameModel){
		gameModel.currentBrick = gameModel.nextBrick.clone();
		gameModel.nextBrick = gameModel.bricks.getRandomBrick();
		gameModel.score += 5 * ((gameModel.level*2)-1);
		moveCurrentBrickToCenter(gameModel);
	}
	
	/**
	 * Set the current brick position horizontally centered.
	 * 
	 * @param gameModel model to modify
	 */
	private void moveCurrentBrickToCenter(GameModel gameModel){
		point2D[] currentBirckConstraints = gameModel.currentBrick.getConstraints();
		gameModel.actualBrickPos = new point2D((int)(gameModel.stageModel.getDimensions().x/2 - currentBirckConstraints[0].x - (currentBirckConstraints[1].x - currentBirckConstraints[0].x)/2)-1,-currentBirckConstraints[0].y);
	}
	
	/**
	 * Concatenate the movable brick to the static ones.
	 * 
	 * @param gameModel model to modify
	 */
	public void meltCurrentBrick(GameModel gameModel){
		gameModel.stageModel.loadArea(gameModel.actualBrickPos.x, gameModel.actualBrickPos.y, gameModel.currentBrick,true);
	}
}
