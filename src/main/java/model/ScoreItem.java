package model;


/**
 * Model to hold a score item.
 */
public class ScoreItem {
	/**
	 * Score amount.
	 */
	private long score;
	
	/**
	 * Name of player.
	 */
	private String name;
	
	/**
	 * Constructor, sets values.
	 * 
	 * @param name name of player
	 * @param score score reached
	 */
	public ScoreItem(String name, long score) {
		super();
		this.score = score;
		this.name = name;
	}

	/**
	 * Getter for score.
	 * 
	 * @return score amount
	 */
	public long getScore() {
		return score;
	}

	/**
	 * Setter for score.
	 * 
	 * @param score socre amount
	 */
	public void setScore(long score) {
		this.score = score;
	}

	
	/**
	 * Getter for player name.
	 * 
	 * @return name of the player
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Setter of player name.
	 * 
	 * @param name name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}