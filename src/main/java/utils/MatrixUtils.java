package utils;

/**
 * Utility to debug matrices
 */
public class MatrixUtils {
	public MatrixUtils() {
	
	}
	
	public void printMatrix(int[][] matrix){
		String str = "";
		for(int y=0;y<matrix[0].length;y++){
			for(int x=0;x<matrix.length;x++){
				str += (matrix[x][y]>0?"[]":"  ");
			}
			str+="\n";
		}
		System.out.println(str);
	}
}
