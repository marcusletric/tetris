package controller;

import model.GameConfiguration;
import model.GameModel;
import model.RenderConfiguration;
import view.UserInterface;

/**
 * Main class.
 */
public class tetrisMain {
	/**
	 * Game configuration instance.
	 */
	static GameConfiguration gameConfig = new GameConfiguration();
	
	/**
	 * Rendering configuration instance.
	 */
	static RenderConfiguration renderConfig = new RenderConfiguration();
	
	/**
	 * Main entry point
	 * initialize game model, instantiate user interface.
	 * 
	 * @param args array of external arguments passed
	 */
	public static void main(String[] args) {
		GameModel gameModel = new GameModel(gameConfig);
		LogicController gameCtrl = new LogicController(gameModel);
		UserInterface renderCtrl = new UserInterface(gameCtrl, gameConfig, renderConfig, gameModel);
		gameCtrl.initStage(renderCtrl);
		new KeyboardCtrl(gameCtrl);
	}
}
