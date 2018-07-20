package view;

import java.util.Collection;

public interface RenderInterface {

	public void RenderNextBrick(int[][] matrix);
	
	public void RenderStage(int[][] matrix);
	
	public void renderScore(long score);
	
	public void renderLevel(int level);
	
	public void invertLines(Collection<Integer> lineIndexes);
	
	public void emptyLines(Collection<Integer> lineIndexes);
}
