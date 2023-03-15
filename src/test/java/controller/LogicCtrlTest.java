package controller;

import static org.junit.Assert.assertArrayEquals;

import model.Bricks;
import model.GameConfiguration;
import model.GameModel;
import model.RenderConfiguration;
import model.StageModel;
import model.point2D;

import org.junit.Before;
import org.junit.Test;

import view.UserInterface;

public class LogicCtrlTest {
	private int[][] leftExcpect = {
			{1,1,0,0,0,0,0,0,0,0},
			{1,1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}
	};
	
	private int[][] rightExcpect = {
			{0,0,0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}
	};
	
	private int[][] downExcpect = {
			{0,0,0,0,0,0,1,1,0,0},
			{0,0,0,0,0,0,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,1,1}
	};
	
	private Bricks bricks = new Bricks();
	
	static GameConfiguration gameConfig = new GameConfiguration();
	static RenderConfiguration renderConfig = new RenderConfiguration();

	private void assertIntMatrixEquals(int[][] expecteds, int[][] actuals){
		for(int i = 0;i < expecteds.length;i++){
			assertArrayEquals(expecteds[i], actuals[i]);
		}
	}
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testInit() {
		GameModel gameModel = new GameModel(gameConfig);
		LogicController gameCtrl = new LogicController(gameModel);
		UserInterface renderCtrl = new UserInterface(gameCtrl, gameConfig, renderConfig, gameModel);
		gameCtrl.initStage(renderCtrl);
		
		StageModel emptyStage = new StageModel(gameConfig.getStageDimensions().x, gameConfig.getStageDimensions().y);
		emptyStage.clear();
		
		assertIntMatrixEquals(emptyStage.getMatrix(),gameModel.stageModel.getMatrix());
	}
	
	@Test
	public void testInteraction() {
		GameModel gameModel = new GameModel(gameConfig);
		LogicController gameCtrl = new LogicController(gameModel);
		UserInterface renderCtrl = new UserInterface(gameCtrl, gameConfig, renderConfig, gameModel);
		gameCtrl.initStage(renderCtrl);
		gameModel.isRunning = true;
		gameModel.currentBrick = bricks.getBrick(0);
		gameModel.actualBrickPos = new point2D(5, -1);
		
		for(int i = 0; i < 10; i++){
			gameCtrl.interact("moveLeft");
		}
		gameCtrl.renderScreen();
		
		assertIntMatrixEquals(leftExcpect,gameModel.renderModel.getMatrix());
		
		for(int i = 0; i < 15; i++){
			gameCtrl.interact("moveRight");
		}
		gameCtrl.renderScreen();
		
		assertIntMatrixEquals(rightExcpect,gameModel.renderModel.getMatrix());
		
		for(int i = 0; i < 20; i++){
			gameCtrl.interact("moveDown");
		}
		gameModel.currentBrick = bricks.getBrick(0);
		gameModel.actualBrickPos = new point2D(5, -1);
		for(int i = 0; i < 15; i++){
			gameCtrl.interact("moveRight");
		}
		for(int i = 0; i < 20; i++){
			gameCtrl.interact("moveDown");
		}
		gameModel.currentBrick = bricks.getBrick(0);
		gameModel.actualBrickPos = new point2D(5, -1);
		gameCtrl.renderScreen();
		
		assertIntMatrixEquals(downExcpect,gameModel.renderModel.getMatrix());
	}

}
