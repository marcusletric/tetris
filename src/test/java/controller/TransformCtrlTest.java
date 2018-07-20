package controller;

import java.util.ArrayList;
import java.util.List;

import model.BrickModel;
import model.Bricks;
import model.StageModel;
import model.point2D;

import org.junit.*;

import static org.junit.Assert.*;

public class TransformCtrlTest {

	private List<StageModel> StageMocks = new ArrayList<StageModel>();
	private List<BrickModel> BrickMocks = new ArrayList<BrickModel>();
	private Bricks bricks = new Bricks();

	private void assertIntMatrixEquals(int[][] expecteds, int[][] actuals){
		for(int i = 0;i < expecteds.length;i++){
			assertArrayEquals(expecteds[i], actuals[i]);
		}
	}
	
	@Before
	public void setUp() {
		BrickMocks.add(bricks.getBrick(0));
		BrickMocks.add(bricks.getBrick(5));		
		StageModel stageMock = new StageModel(4, 4);
		stageMock.setMatrix(new int[][]{
			{1,1,1,0},
			{0,1,1,1},
			{0,1,0,1},
			{0,0,0,1}
		});
		StageMocks.add(stageMock);
	}
	
	@Test
	public void testLeftRotation() {
		BrickModel brick1 = BrickMocks.get(0).clone();
		TransformController ctrl = new TransformController();
		ctrl.rotateLeft(brick1, new point2D(1,1));
		int[][] afterRot = {
				{0,1,1,0},
				{0,1,1,0},
				{0,0,0,0},
				{0,0,0,0},
		};
		assertIntMatrixEquals(afterRot,brick1.getMatrix());
		ctrl.rotateLeft(brick1, new point2D(1,1));
		int[][] afterRot2 = {
				{1,1,0,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0},
		};
		assertIntMatrixEquals(afterRot2,brick1.getMatrix());
		ctrl.rotateLeft(brick1, new point2D(1,1));
		ctrl.rotateLeft(brick1, new point2D(1,1));
		assertIntMatrixEquals(BrickMocks.get(0).getMatrix(),brick1.getMatrix());
	}
	
	@Test
	public void testRighRotation() {
		BrickModel brick1 = BrickMocks.get(1).clone();
		TransformController ctrl = new TransformController();
		ctrl.rotateRight(brick1, new point2D(1,1));
		int[][] afterRot = {
				{0,0,1,0},
				{0,0,1,0},
				{0,1,1,0},
				{0,0,0,0},
		};
		assertIntMatrixEquals(afterRot,brick1.getMatrix());
		ctrl.rotateRight(brick1, new point2D(1,1));
		int[][] afterRot2 = {
				{0,0,0,0},
				{1,0,0,0},
				{1,1,1,0},
				{0,0,0,0},
		};
		assertIntMatrixEquals(afterRot2,brick1.getMatrix());
		ctrl.rotateLeft(brick1, new point2D(1,1));
		ctrl.rotateLeft(brick1, new point2D(1,1));
		assertIntMatrixEquals(BrickMocks.get(1).getMatrix(),brick1.getMatrix());
	}
	
	@Test
	public void testRemoveRow() {
		StageModel stage = StageMocks.get(0);
		TransformController ctrl = new TransformController();
		ctrl.removeRow(stage, 1);
		int[][] afterRemove = {
				{0,0,0,0},
				{1,1,1,0},
				{0,1,0,1},
				{0,0,0,1}
		};
		assertIntMatrixEquals(afterRemove,stage.getMatrix());
		ctrl.removeRow(stage, 3);
		int[][] afterRemove2 = {
				{0,0,0,0},
				{0,0,0,0},
				{1,1,1,0},
				{0,1,0,1},
		};
		assertIntMatrixEquals(afterRemove2,stage.getMatrix());
	}

}
