package view;

import java.awt.Color;

import model.point2D;

import org.piccolo2d.PNode;
import org.piccolo2d.nodes.PPath;

/**
 * Class to render a square.
 */
public class Pixel {
	
	/**
	 * Visual element for the square.
	 */
	private PPath itemNode;
	
	/**
	 * Filled state of the square.
	 */
	private int filled = 0;
	
	/**
	 * Set the filled state of the square then render it again.
	 * 
	 * @param filled boolean state of fill
	 */
	public void setFilled(int filled) {
		this.filled = filled;
		if(this.filled > 0){
			itemNode.setPaint(Color.getHSBColor(0.0f, 0.0f, 0.6f));
		} else {
			itemNode.setPaint(Color.WHITE);
		}
		itemNode.repaint();
	}
	
	/**
	 * Get the filled state of the square.
	 * 
	 * @return <code>true</code> if filled <code>false</code> otherwise
	 */
	public int getFilled(){
		return filled;
	}
	
	/**
	 * Constructor, sets position and size of the square.
	 * 
	 * @param position position coordinates
	 * @param size size in pixels
	 */
	public Pixel(point2D position, point2D size) {
		itemNode = PPath.createRectangle(position.x,position.y,size.x,size.y);
		itemNode.setPaint(Color.WHITE);
		itemNode.setStrokePaint(Color.getHSBColor(0.0f, 0.0f, 0.9f));
	}

	/**
	 * Getter for the visual element.
	 * 
	 * @return visual element instance
	 */
	public PNode getItemNode() {
		return itemNode;
	}
}
