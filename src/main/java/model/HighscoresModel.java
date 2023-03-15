package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import controller.DatabaseController;


/**
 * Model to store list of high scores.
 */
public class HighscoresModel {
	
	/**
	 * Database controller instance.
	 */
	private DatabaseController dbCtrl;
	
	/**
	 * List of scores.
	 */
	private List<ScoreItem> scoreList;
	
	/**
	 * Constructor.
	 * 
	 * Gets fresh high score list from database.
	 */
	public HighscoresModel() {
		updateScores();
	}
	
	/**
	 * Request a fresh list of high scores from database.
	 */
	public void updateScores(){
		try {
			dbCtrl = new DatabaseController();
			scoreList = dbCtrl.getScores();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter for high scores list.
	 * 
	 * @return List of {@link ScoreItem}
	 */
	public List<ScoreItem> getScores(){
		return scoreList;
	}
	
	/**
	 * Checks if a score is high score.
	 * 
	 * @param score point amount to check
	 * @return <code>ture</code> if score is a high score, <code>false</code> else.
	 */
	public boolean isHighscore(long score){
		if(scoreList.size() < 20 || scoreList.get(19).getScore() < score){
			return true;
		}
		return false;
	}
	
}
