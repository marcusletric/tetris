package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TransformCtrlTest.class,
	LogicCtrlTest.class
})
public class ControllerSuite {

	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(ControllerSuite.class);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

}

