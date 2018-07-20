package controller;

import java.util.Collection;
import java.util.TimerTask;

import animations.*;
import utils.AnimationUtil;
import utils.BlockStateUtil;
import utils.Callback;
import utils.CallbackInstance;
import view.RenderingCanvas;
import view.UserInterface;
import model.BrickModel;
import model.GameModel;
import model.point2D;


/**
 * Logic controller.
 * 
 * This class handles the whole game logic.
 */
public class LogicController {
	
	/**
	 * Row blinking state flag.
	 */
	private boolean isBlinking = false;
	
	/**
	 * Frame in progress flag.
	 */
	private boolean frameInProgress = false;
	
	/**
	 * GameModel instance.
	 */
	private GameModel gameModel;
	
	/**
	 * Rendering controller instance.
	 */
	private RenderingCanvas renderCtrl;
	
	/**
	 * Animation helper instance.
	 */
	private AnimationUtil animation;
	
	/**
	 * Full rows index collection.
	 */
	private Collection<Integer> fullRows;
	
	/**
	 * Block state helper instance.
	 */
	private BlockStateUtil blockState = new BlockStateUtil();
	
	/**
	 * Stage controller instance.
	 */
	private StageController stgCtrl = new StageController();
	
	/**
	 * Constructor
	 * stores gameModel instance, initializes animation utility.
	 * 
	 * @param gameModel Game model instance
	 */
	public LogicController(GameModel gameModel) {
		this.gameModel = gameModel;
		animation = new AnimationUtil(gameModel.timer);
	}

	
	/**
	 * Initialize stage.
	 * Set up the models for a new game.
	 * 
	 * @param renderCtrl Rendering controller
	 */
	public void initStage(UserInterface renderCtrl){
		this.renderCtrl = renderCtrl;
		stgCtrl.initModels(gameModel);
		renderCtrl.renderMatrix(gameModel.bricks.gameover, renderCtrl.getStageArea());
	}
	
	
	/**
	 * Animation frame handler.
	 * 
	 * Request for interaction to move down the current block.
	 * If the game is in running state then continues the loop of frameRequests.
	 */
	public void frameHandler(){
		interact("moveDown");
		if(isRunning()){
			requestNextFrame();
		}
	}
	
	
	/**
	 * Sets up a timeout for next frameHandler call if it is was not already been requested.
	 */
	private void requestNextFrame(){
		if(!frameInProgress){	
			gameModel.timer.schedule(new TimerTask() {
				@Override
				public void run() {
					frameInProgress = false;
					frameHandler();
				}
			}, Math.max(1000 - (gameModel.level*100),100));
			frameInProgress = true;
		}
	}
	
	/**
	 * Calculate the current level based on number of removed lines.
	 */
	private void calculateLevel(){
		int increment = 10;
		int currLevelDone = gameModel.level*increment;
		int sum = 0;
		for(int i=1; i<gameModel.level;i++){
			sum += i*increment;
		}
		
		gameModel.level += (int)Math.floor(gameModel.removedLines / (sum + currLevelDone));
	}
	
	/**
	 * Handle the event of current brick touching the ground.
	 */
	private void handleBlockGroundTouch(){
		gameModel.stageModel.compose(gameModel.currentBrick, gameModel.actualBrickPos);
		
		fullRows = blockState.getFullRows(gameModel.stageModel);
		if(fullRows.size() > 0 && !isBlinking){
			blinkLines();
			isBlinking = true;
			return;
		} else {
			isBlinking = false;
		}
			
		stgCtrl.addNewBrick(gameModel);
		
		if(blockState.willCollideBlock(gameModel.actualBrickPos,gameModel.currentBrick,gameModel.stageModel)){
			gameOver();
		}
		
		stgCtrl.removeRows(gameModel,fullRows);
		calculateLevel();
	}
	
	/**
	 * Request for interaction with the current movable brick.
	 * 
	 * Generate a new brick with the state and position after interaction.
	 * If the new state and position is applicable for the current stage then
	 * it will be applied, else the interaction request is denied.
	 * 
	 * @param action the action to be done with the movable brick
	 */
	public void interact(String action){
		point2D newPos = gameModel.actualBrickPos;
		BrickModel newBrick = gameModel.currentBrick.clone();
		
		switch(action){
			case "moveLeft" : 
				newPos = new point2D(gameModel.actualBrickPos.x-1, gameModel.actualBrickPos.y); break;
			case "moveRight" : 
				newPos = new point2D(gameModel.actualBrickPos.x+1, gameModel.actualBrickPos.y); break;
			case "moveDown" : 
				newPos = new point2D(gameModel.actualBrickPos.x, gameModel.actualBrickPos.y+1); break;
			case "rotateCW" : 
				newBrick.rotateCW(); break;
			case "rotateCCW" : 
				newBrick.rotateCCW(); break;
		}
		if(isRunning()){
			int wallCollision = blockState.willCollideWall(newPos, newBrick, gameModel.stageModel);
			if(wallCollision == 0 && !blockState.willCollideBlock(newPos, newBrick, gameModel.stageModel)){
				gameModel.actualBrickPos = newPos;
				gameModel.currentBrick = newBrick.clone();
			} else if( (action == "rotateCW" || action == "rotateCCW") && wallCollision > 0 ){
				if(wallCollision == 1){
					newPos.x += Math.abs(newPos.x + (newBrick.getConstraints())[0].x);
				} else {
					newPos.x -= Math.abs((gameModel.stageModel.getMatrix())[0].length-1 - (newPos.x + (newBrick.getConstraints())[1].x));
				}
				gameModel.actualBrickPos = newPos;
				gameModel.currentBrick = newBrick.clone();
			} else if(wallCollision == 0 && action == "moveDown"){
				handleBlockGroundTouch();
			}
			renderScreen();
		}
	}
	
	/**
	 * Pause the game, do a line blinking animation then continue the game.
	 */
	public void blinkLines(){
		pauseGame();
		animation.addAnimation(6, 80, new InvertLines(new Object[]{renderCtrl,fullRows}), new startGameCB(new Object[]{gameModel,this}));
	}
	
	/**
	 * Do a restart animation then restart the game.
	 */
	public void restartGame(){
		animation.addAnimation(20, 50, new RestartAnim(new Object[]{renderCtrl}), new restartCB(new Object[]{this,renderCtrl}));
	}
	
	/**
	 * Render the elements on the screen.
	 */
	public void renderScreen(){
		if(isRunning()){
			gameModel.renderModel.load(gameModel.stageModel);
			gameModel.renderModel.compose(gameModel.currentBrick, gameModel.actualBrickPos);
			renderCtrl.RenderNextBrick(gameModel.nextBrick.getMatrix());
			renderCtrl.RenderStage(gameModel.renderModel.getMatrix());
			renderCtrl.renderScore(gameModel.score);
			renderCtrl.renderLevel(gameModel.level);
		}
	}
	
	/**
	 * Start the game.
	 */
	public void startGame(){
		gameModel.isRunning = true;
		frameHandler();
	}
	
	/**
	 * Pause the game.
	 */
	public void pauseGame(){
		gameModel.isRunning = false;
	}
	
	/**
	 * Reset the game model.
	 */
	public void resetGame(){
		gameModel.reset();
	}
	
	/**
	 * Getter for running state of the game.
	 * 
	 * @return <code>true</code> if the game is running, <code>false</code> otherwise
	 */
	public boolean isRunning(){
		return gameModel.isRunning;
	}
	
	/**
	 * Handle the game over state.
	 */
	public void gameOver(){
		renderScreen();
		pauseGame();
		renderCtrl.renderScore(gameModel.score);
		animation.addAnimation(20, 50, new GameOverAnim(new Object[]{renderCtrl}), new gameOverCB(new Object[]{renderCtrl}));
	}
	
	/**
	 * Callback for game restart.
	 */
	public class restartCB extends CallbackInstance implements Callback{
		public restartCB(Object[] params) {
			super(params);
		}
		
		@Override
		public void call(Object[] callbackInjection) {
			((LogicController)params[0]).resetGame();
			((LogicController)params[0]).initStage((UserInterface)params[1]);
			((LogicController)params[0]).startGame();
		}
		
	}
	
	/**
	 * Callback for game over.
	 */
	public class gameOverCB extends CallbackInstance implements Callback{
		public gameOverCB(Object[] params) {
			super(params);
		}
		
		@Override
		public void call(Object[] callbackInjection) {
			
			((UserInterface)params[0]).renderGameOverScreen();
		}
		
	}
	
	/**
	 * Callback for game start.
	 */
	public class startGameCB extends CallbackInstance implements Callback{
		public startGameCB(Object[] params) {
			super(params);
		}
		
		@Override
		public void call(Object[] callbackInjection) {
			((GameModel)params[0]).isRunning = true;
			((LogicController)params[1]).frameHandler();
		}
		
	}
	
	/**
	 * Getter for actual score.
	 * 
	 * @return actual score
	 */
	public long getScore(){
		return gameModel.score;
	}
	
	/**
	 * Check if score can get into highest scores.
	 * 
	 * @param score points to check
	 * @return <code>true</code> if the point amount is high enough for a high score, else returns <code>false</code> 
	 */
	public boolean isHighscore(long score){
		return gameModel.highscoresModel.isHighscore(score);
	}
}
