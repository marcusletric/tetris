package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

/**
 * Model to hold a score list applicable to {@link JList}.
 */
@SuppressWarnings("serial")
public class ScoreListModel extends AbstractListModel<String> {
	
	/**
	 * List of score items.
	 */
	List<String> values = new ArrayList<String>();
	
	/**
	 * Constructor, concatenates string list for {@link JList}.
	 * 
	 * @param datas high scores model
	 */
	public ScoreListModel(HighscoresModel datas){
		int i = 1;
		for (ScoreItem score: (datas.getScores())){
			values.add(i + "." + " " + score.getName() + " " + score.getScore());
			i++;
		}
	}
	
	/**
	 * Getter for size of the list.
	 * 
	 * @return size
	 */
	public int getSize() {
		return values.size();
	}
	
	/**
	 * Getter for a list element.
	 * 
	 * @param index index of the element to get
	 * @return ScoreItem
	 */
	public String getElementAt(int index) {
		return values.get(index);
	}


}
