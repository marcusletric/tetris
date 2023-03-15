package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ScoreItem;
import utils.SQLConnUtil;

/**
 * Controller for database transactions.
 */
public class DatabaseController {
	 
	/**
	 * SQL connection utility instance.
	 */
	private SQLConnUtil connUtil = new SQLConnUtil();
	
	/**
	 * Stored SQL connection instance.
	 */
	private Connection conn;
	
	/**
	 * Constructor, initializes connection to database.
	 * 
	 * @throws SQLException if the connection was not successful
	 */
	public DatabaseController() throws SQLException {
		this.conn = connUtil.getConnection();
	}
 
	/**
	 * Method to get scores stored in the database.
	 * 
	 * @return list of ScoreItem
	 * @throws SQLException if there was error during query execution
	 * @throws IOException if there was error during query execution
	 */
	public ArrayList<ScoreItem> getScores() throws SQLException, IOException{
		Statement stmt = null;
		ArrayList<ScoreItem> retList = new ArrayList<ScoreItem>();
		String query = "SELECT * FROM tetris_scores ORDER BY score DESC LIMIT 0, 20";
 
		try {
			stmt = conn.createStatement();      
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())  {
				retList.add(new ScoreItem(rs.getString("name"), rs.getInt("score")));
			}
		} catch (SQLException e){
	 
		}
 
		return retList;
	}
	
	/**
	 * Stores a ScoreItem into the database.
	 * 
	 * @param scoreItem score item to store
	 * @throws SQLException if there was error during query execution
	 */
	public void addScore(ScoreItem scoreItem) throws SQLException{
		Statement stmt = null;
		String query = "INSERT INTO tetris_scores SET `name`='" + scoreItem.getName() + "', `score`='" + scoreItem.getScore() + "'";
		stmt = conn.createStatement();
		stmt.execute(query);
	 }
}
