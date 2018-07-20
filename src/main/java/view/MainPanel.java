package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * Main screen.
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Button to start the game.
	 */
	public JButton startBtn;
	
	/**
	 * Button to show high scores list.
	 */
	public JButton highsBtn;
	
	/**
	 * Constructor, append visual elements.
	 */
	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblTetris = new JLabel("TETRIS");
		lblTetris.setBounds(81, 60, 266, 61);
		lblTetris.setVerticalAlignment(SwingConstants.TOP);
		lblTetris.setHorizontalAlignment(SwingConstants.CENTER);
		lblTetris.setFont(new Font("Tahoma", Font.PLAIN, 60));
		add(lblTetris, "2, 2, center, top");
		
		JLabel lblTheGame = new JLabel("The game");
		lblTheGame.setBackground(Color.WHITE);
		lblTheGame.setBounds(186, 120, 56, 22);
		add(lblTheGame, "2, 4, center, top");
		
		startBtn = new JButton("Start");
		startBtn.setBounds(131, 171, 166, 46);
		add(startBtn);
		
		highsBtn = new JButton("Highscores");
		highsBtn.setSize(166, 46);
		highsBtn.setLocation(131, 228);
		add(highsBtn);

	}
}
