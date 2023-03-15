package view;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListModel;

import java.awt.Font;
import java.sql.Array;

import javax.swing.JButton;

import model.HighscoresModel;
import model.ScoreListModel;


/**
 * High scores list screen.
 */
public class HighscoresPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Button for going to the home screen.
	 */
	public JButton btnHome;
	
	/**
	 * JList visual element to show the scores.
	 */
	private JList<Array> list = new JList<Array>();
	
	/**
	 * High scores model to handle score items.
	 */
	private HighscoresModel highScores;
	
	/**
	 * Constructor, appends visual elements.
	 * 
	 * @param highScores model to get highest scores
	 */
	public HighscoresPanel(HighscoresModel highScores) {
		setLayout(null);
		this.highScores = highScores;
		
		list.setBounds(10, 43, 399, 469);
		add(list);
		highScores.updateScores();
		
		ScoreListModel scoreList = new ScoreListModel(highScores);
		list.setModel((ListModel)scoreList);
		
		JLabel lblHighestScores = new JLabel("Highest scores");
		lblHighestScores.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHighestScores.setBounds(151, 0, 117, 45);
		add(lblHighestScores);
		
		btnHome = new JButton("Home");
		btnHome.setBounds(10, 520, 399, 47);
		add(btnHome);

	}
	
	/**
	 * Fetch the list of high scores.
	 */
	public void updateScores(){
		highScores.updateScores();
		ScoreListModel scoreList = new ScoreListModel(highScores);
		list.setModel((ListModel)scoreList);
	}
}
