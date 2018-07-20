package model;

/**
 * Model to store the state of the game
 * Aggregates all the models used to store a game state
 */
import java.util.Timer;

/**
 * Model to store game state.
 */
public class GameModel {
	
	/**
	 * Brick matrices collection instance.
	 */
	public Bricks bricks = new Bricks();
	
	/**
	 * Models storing current and next brick.
	 */
	public BrickModel nextBrick, currentBrick;
	
	/**
	 * Models storing stage and render model.
	 */
	public StageModel stageModel, renderModel;
	
	/**
	 * High scores model instance.
	 */
	public HighscoresModel highscoresModel;
	
	/**
	 * Timer instance.
	 */
	public Timer timer = new Timer();
	
	/**
	 * Flag to store running state of game.
	 */
	public boolean isRunning = false;
	
	/**
	 * Current level.
	 */
	public int level;
	
	/**
	 * Current score in points.
	 */
	public long score;
	
	/**
	 * Amount of removed lines.
	 */
	public long removedLines;
	
	/**
	 * Actual position of the movable brick.
	 */
	public point2D actualBrickPos = new point2D(0, 0);
	
	/**
	 * Stage dimensions.
	 */
	private point2D stageDim;
	
	/**
	 * Constructor, initializes all the models based on a GameConfiguration.
	 * 
	 * @param config game configuration instance for initialization
	 */
	public GameModel(GameConfiguration config){
		stageDim = config.getStageDimensions();
		reset();
	}
	
	/**
	 * Reset models to starting state.
	 */
	public void reset(){
		score = 0;
		level = 1;
		removedLines = 0;
		isRunning = false;
		stageModel = new StageModel(stageDim.x, stageDim.y);
		renderModel = new StageModel(stageDim.x, stageDim.y);
		highscoresModel = new HighscoresModel();
	}
	
}
