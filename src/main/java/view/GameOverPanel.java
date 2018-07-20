package view;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Game over screen.
 */
public class GameOverPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Input field for player name.
	 */
	public JTextField textField;
	
	/**
	 * Restart game button.
	 */
	public JButton startBtn;
	
	/**
	 * Panel to show high score form.
	 */
	public JPanel highscorePanel;
	
	/**
	 * Button to go to high score list.
	 */
	public JButton highsButton;
	
	/**
	 * Button to store the high score.
	 */
	public JButton btnDone;

	/**
	 * Constructor, append visual elements.
	 */
	public GameOverPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblGameover = new JLabel("Game Over");
		lblGameover.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblGameover.setBounds(60, 120, 306, 113);
		add(lblGameover);
		
		highscorePanel = new JPanel();
		highscorePanel.setLayout(null);
		highscorePanel.setSize(310, 150);
		highscorePanel.setLocation(55, 220);
		add(highscorePanel);
		highscorePanel.setVisible(false);
		
		lblGameover.setBounds(60, 120, 306, 113);
		add(lblGameover);
		
		JLabel lblNewHighscore = new JLabel("New highscore!");
		lblNewHighscore.setForeground(new Color(255, 153, 0));
		lblNewHighscore.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewHighscore.setBounds(20, 0, 320, 80);
		highscorePanel.add(lblNewHighscore);
		
		JLabel lblPleaseEnterYour = new JLabel("please enter your name:");
		lblPleaseEnterYour.setBounds(93, 75, 140, 14);
		highscorePanel.add(lblPleaseEnterYour);
		
		textField = new JTextField();
		textField.setBounds(10, 90, 288, 20);
		highscorePanel.add(textField);
		textField.setColumns(10);
		
		btnDone = new JButton("Done");
		btnDone.setBounds(10, 111, 288, 23);
		highscorePanel.add(btnDone);
		
		startBtn = new JButton("Restart game");
		startBtn.setSize(166, 46);
		startBtn.setLocation(30, 500);
		add(startBtn);
		
		highsButton = new JButton("Highscores");
		highsButton.setSize(166, 46);
		highsButton.setLocation(212, 500);
		add(highsButton);

	}
}
