package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.GameConfiguration;
import model.GameModel;
import model.RenderConfiguration;
import model.ScoreItem;
import model.point2D;
import controller.DatabaseController;
import controller.LogicController;

/**
 * Class for User interface.
 */
public class UserInterface extends RenderingCanvas {

	private static final long serialVersionUID = 1L;
	/**
	 * Main screen visual element.
	 */
	private MainPanel mainPanel = new MainPanel();
	
	/**
	 * Game controller instance.
	 */
	private LogicController logicCtrl;
	
	/**
	 * Visual element for game over screen.
	 */
	private GameOverPanel gameOverPanel;
	
	/**
	 * Visual element for high scores screen.
	 */
	private HighscoresPanel highscoresPanel;
	
	/**
	 * Database model instance
	 */
	private DatabaseController dbModel;
	
	/**
	 * Cunstructor, appends visual elements, handles interaction.
	 * 
	 * @param mainCtrl game controller instance
	 * @param gameConfig game configuration instance
	 * @param renderConfig rendering configuration instance
	 * @param gameModel game model
	 */
	public UserInterface(LogicController mainCtrl, GameConfiguration gameConfig, RenderConfiguration renderConfig, GameModel gameModel) {
		super(gameConfig, renderConfig);
		
		highscoresPanel = new HighscoresPanel(gameModel.highscoresModel);
		gameOverPanel = new GameOverPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        point2D screenDim = renderConfig.getScreenResolution();
        
        setSize(screenDim.x, screenDim.y);
        setVisible(true);
        this.logicCtrl = mainCtrl;
        
        add(mainPanel);
        mainPanel.setVisible(true);
        canvas.setVisible(false);
        gameOverPanel.setVisible(false);
        highscoresPanel.setVisible(false);
        
        mainPanel.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(false);
				canvas.setVisible(true);
				logicCtrl.restartGame();
			}
		});
        
        mainPanel.highsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(false);
				canvas.setVisible(false);
				gameOverPanel.setVisible(false);
				highscoresPanel.updateScores();
				add(highscoresPanel);
				highscoresPanel.setVisible(true);
			}
		});
        
        highscoresPanel.btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas.setVisible(false);
				gameOverPanel.setVisible(false);
				highscoresPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
        
        gameOverPanel.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(false);
				gameOverPanel.setVisible(false);
				canvas.setVisible(true);
				logicCtrl.restartGame();
			}
		});
        
        gameOverPanel.highsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(false);
				canvas.setVisible(false);
				gameOverPanel.setVisible(false);
				highscoresPanel.updateScores();
				add(highscoresPanel);
				highscoresPanel.setVisible(true);
			}
		});
        
        gameOverPanel.btnDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gameOverPanel.textField.getText().length() > 0){	
					try {
						gameOverPanel.highscorePanel.setVisible(false);
						dbModel = new DatabaseController();
						dbModel.addScore(new ScoreItem(gameOverPanel.textField.getText(), logicCtrl.getScore()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
        
	}
	
	/**
	 * Render game over screen.
	 */
	public void renderGameOverScreen(){
		canvas.setVisible(false);
		mainPanel.setVisible(false);
		gameOverPanel.setVisible(true);
		if(logicCtrl.isHighscore(logicCtrl.getScore())){
			gameOverPanel.highscorePanel.setVisible(true);
		}
		add(gameOverPanel);
	}
}
