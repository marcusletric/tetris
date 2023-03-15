package model;

import java.util.Random;

/**
 * Class for holding the usable bricks.
 */
public class Bricks {
	
	/**
	 * Random generator instance.
	 */
	private Random rand = new Random();
	
	/**
	 * List of applicable brick matrices.
	 */
	private int[][][] bricks = {{	{0,0,0,0},
									{0,1,1,0},
									{0,1,1,0},
									{0,0,0,0} },
								{	{0,0,0,0},
									{1,1,1,1},
									{0,0,0,0},
									{0,0,0,0} },
								{	{1,1,0,0},
									{0,1,1,0},
									{0,0,0,0},
									{0,0,0,0} },
								{	{0,1,1,0},
									{1,1,0,0},
									{0,0,0,0},
									{0,0,0,0} },
								{	{1,1,1,0},
									{1,0,0,0},
									{0,0,0,0},
									{0,0,0,0} },
								{	{1,1,1,0},
									{0,0,1,0},
									{0,0,0,0},
									{0,0,0,0} },
								{	{1,1,1,0},
									{0,1,0,0},
									{0,0,0,0},
									{0,0,0,0} }};
	
	/**
	 * List of maximum clockwise 90° rotations per brick.
	 */
	private int[] maxRot = {0,1,1,1,3,3,3};
	
	
	/**
	 * Constructor.
	 */
	public Bricks() {
		
	}
	
	/**
	 * Screen matrix of game over state.
	 */
	public int[][] gameover = { 
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1}
	};
	
	/**
	 * Get a brick form the list.
	 * 
	 * @param index of brick to get
	 * @return a brick matrix
	 */
	public BrickModel getBrick(int index){
		GameConfiguration cnf = new GameConfiguration();
		BrickModel brick = new BrickModel(bricks[index], cnf.getBrickCenter(), maxRot[index]);
		
		return brick;
	}
	
	/**
	 * Get a random brick.
	 * 
	 * @return a new brick
	 */
	public BrickModel getRandomBrick(){
		BrickModel newBrick = getBrick(rand.nextInt(7));
		int randRot = rand.nextInt(4);
		for(int i=0;i<randRot;i++){
			newBrick.rotateCW();
		}
		
		return newBrick;
	}
}
