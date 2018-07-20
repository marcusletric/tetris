package view;

import java.util.Collection;

import javax.swing.JFrame;

import model.GameConfiguration;
import model.RenderConfiguration;
import model.point2D;

import org.piccolo2d.*;
import org.piccolo2d.nodes.PText;

/**
 * Rendering window.
 */
public class RenderingCanvas extends JFrame implements RenderInterface{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Visual element instantiation.
	 */
	final PCanvas canvas = new PCanvas();
	
	/**
	 * Rendering configuration.
	 */
	private RenderConfiguration rConf;
	
	/**
	 * Game configuration.
	 */
	private GameConfiguration gConf;
	
	/**
	 * Matrix of squares for the stage.
	 */
	private Pixel[][] stagePixels;
	
	/**
	 * Matrix of squares for next brick rendering.
	 */
	private Pixel[][] nextBrickPixels;
	
	/**
	 * Visual elements for displaying level and score.
	 */
	private PText scoreLabel, levelLabel;
	
	/**
	 * Constructor, stores configuration instances, add rendering canvas to window element.
	 * 
	 * @param gConf game configuration instance
	 * @param rConf rendering configuration instance
	 */
	public RenderingCanvas(GameConfiguration gConf, RenderConfiguration rConf) {
        this.rConf = rConf;
        this.gConf = gConf;
        
        canvas.setPanEventHandler(null);
        canvas.setZoomEventHandler(null);
        
		add(canvas);
        point2D screenDim = rConf.getScreenResolution();
        
        setSize(screenDim.x, screenDim.y);
        generateUI();
	}
	
	/**
	 * Generate user interface elements, append them to rendering canvas.
	 */
	private void generateUI(){
		
		stagePixels = new Pixel[gConf.getStageDimensions().x][gConf.getStageDimensions().y];
		nextBrickPixels = new Pixel[gConf.getMaxBrickSize()][gConf.getMaxBrickSize()];
		
		int pixelSize = (int)rConf.getScreenResolution().x/15; 
		
		for(int j=0;j<gConf.getStageDimensions().y;j++){
			for(int i=0;i<gConf.getStageDimensions().x;i++){
				point2D actualPos = new point2D(rConf.getSquarePadding()+i*(pixelSize),rConf.getSquarePadding()+j*(pixelSize));
				stagePixels[i][j] = new Pixel(actualPos, new point2D(pixelSize-rConf.getSquarePadding(), pixelSize-rConf.getSquarePadding()));
				canvas.getLayer().addChild(stagePixels[i][j].getItemNode());
			}
		}
		
		for(int j=0;j<gConf.getMaxBrickSize();j++){
			for(int i=0;i<gConf.getMaxBrickSize();i++){
				point2D actualPos = new point2D(rConf.getSquarePadding()+i*(pixelSize) + 11 * pixelSize,rConf.getSquarePadding()+j*(pixelSize));
				nextBrickPixels[i][j] = new Pixel(actualPos, new point2D(pixelSize-rConf.getSquarePadding(), pixelSize-rConf.getSquarePadding()));
				canvas.getLayer().addChild(nextBrickPixels[i][j].getItemNode());
			}
		}
		
		scoreLabel = new PText();
		
		scoreLabel.scale(1.3);
		scoreLabel.setX(11 * pixelSize / scoreLabel.getScale());
		scoreLabel.setY(5 * pixelSize / scoreLabel.getScale());
		
		canvas.getLayer().addChild(scoreLabel);
		
		levelLabel = new PText();
		
		levelLabel.scale(1.3);
		levelLabel.setX(11 * pixelSize / levelLabel.getScale());
		levelLabel.setY(6 * pixelSize / levelLabel.getScale());
		
		canvas.getLayer().addChild(levelLabel);
		
	}
	
	/**
	 * Render an integer matrix on a {@link Pixel} matrix.
	 * 
	 * @param matrix integer matrix to render
	 * @param grid square matrix to render on
	 */
	public void renderMatrix(int[][] matrix, Pixel[][] grid){
		for(int y=0;y<matrix.length;y++){
			for(int x=0;x<matrix[0].length;x++){
				if(matrix[y][x]>0){
					grid[x][y].setFilled(1);
				} else {
					grid[x][y].setFilled(0);
				}
			}
		}
	}
	
	/**
	 * Render next brick from integer matrix.
	 * 
	 * @param matrix integer matrix to render
	 */
	public void RenderNextBrick(int[][] matrix) {
		renderMatrix(matrix, nextBrickPixels);
	}
	
	/**
	 * Render integer matrix on stage {@link Pixel}s.
	 */
	public void RenderStage(int[][] matrix) {
		renderMatrix(matrix, stagePixels);
	}

	/**
	 * Invert {@link Pixel} fill states on whole rows specified.
	 * 
	 * @param lineIndexes row indexes to invert
	 */
	public void invertLines(Collection<Integer> lineIndexes){
		for(int x=0;x<stagePixels.length;x++){
			for (int lineIndex : lineIndexes) {
				if(stagePixels[x][lineIndex].getFilled() == 1){	
					stagePixels[x][lineIndex].setFilled(0);
				} else {
					stagePixels[x][lineIndex].setFilled(1);
				}
			}
		}
	}
	
	/**
	 * Set {@link Pixel} fill states to unfilled on whole rows specified.
	 * 
	 * @param lineIndexes row indexes to invert
	 */
	public void emptyLines(Collection<Integer> lineIndexes){
		for(int x=0;x<stagePixels.length;x++){
			for (int lineIndex : lineIndexes) {
				stagePixels[x][lineIndex].setFilled(0);
			}
		}
	}
	
	/**
	 * Render score.
	 * 
	 * @param score score to render
	 */
	public void renderScore(long score){
		scoreLabel.setText("Score: " + score);
		scoreLabel.repaint();
	}
	
	/**
	 * Render level.
	 * 
	 * @param level level to render
	 */
	public void renderLevel(int level){
		levelLabel.setText("Level: " + level);
		levelLabel.repaint();
	}
	

	/**
	 * Getter for stage rendering area {@link Pixel} matrix
	 * 
	 * @return Pixel matrix
	 */
	public Pixel[][] getStageArea() {
		return stagePixels;
	}

	/**
	 * Getter for next brick rendering area {@link Pixel} matrix
	 * 
	 * @return Pixel matrix
	 */
	public Pixel[][] getNextBrickArea() {
		return nextBrickPixels;
	}

}
