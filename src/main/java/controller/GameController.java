package controller;

import java.util.TimerTask;

import model.GameConfiguration;
import model.GameModel;

public class GameController {
	private GameController ctrlInstance;
	
	private GameConfiguration config = new GameConfiguration();
	private StageController stageCtrl;
	private RenderController renderCtrl;
	private GameModel gameModel;
	
	
	public void initGame(GameController ctrl){
		ctrlInstance = ctrl;
		resetGame();
		gameModel = new GameModel(config);
		stageCtrl = new StageController();
		stageCtrl.initModels(gameModel);
	}
	
	public void resetGame(){
		stageCtrl = new StageController();
		stageCtrl.initModels(gameModel);
	}
	
	private void requestNextFrame(){
		gameModel.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				ctrlInstance.frameHandler();
				//System.out.println(stageCtrl.getGameModel().renderModel.toString());
			}
		}, 1000 / gameModel.level);
	}
	
	public void startGame(){
		gameModel.isRunning = true;
		requestNextFrame();
	}
	
	public void pauseGame(){
		gameModel.isRunning = false;
		
	}
	
	public void frameHandler(){
		//stageCtrl.frameStep();
		//renderCtrl.renderFrame(stageCtrl.getGameModel().renderModel.getMatrix());
		if(gameModel.isRunning){
			requestNextFrame();
		}
	}
}
