package controller;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import controller.KeyDispatcher;

/**
 * Key event dispatcher.
 * 
 * This class is functioning as a proxy for key events 
 * implements {@link KeyEventDispatcher} interface
 */
class KeyDispatcher implements KeyEventDispatcher {
    
	/**
	 * Stored controller instance.
	 */
	private LogicController logicCtrl;
	
	/**
	 * Constructor, stores controller for keyboard actions.
	 * 
	 * @param logicCtrl controller to store
	 */
	public KeyDispatcher(LogicController logicCtrl) {
		this.logicCtrl = logicCtrl;
	}
	
	/**
	 * Dispatch key event to controller.
	 * 
	 * @param e keyboard event
	 * @return <code>false</code>
	 */
	public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED){
        	int c = e.getKeyCode();
        		if( c == KeyEvent.VK_LEFT){
        			logicCtrl.interact("moveLeft");
        		}
        		if( c == KeyEvent.VK_RIGHT){
        			logicCtrl.interact("moveRight");
        		}
        		if( c == KeyEvent.VK_DOWN){
        			logicCtrl.interact("moveDown");
        		}
        		if( c == KeyEvent.VK_SPACE){
        			logicCtrl.interact("rotateCW");
        		}
        		if( c == KeyEvent.VK_CONTROL){
        			logicCtrl.interact("rotateCCW");
        		}
        }
           
        return false;
    }
}

/**
 * Keyboard controller.
 */
public class KeyboardCtrl {
	
	/**
	 * Constructor, finds focused element, then applies the dispatcher on it.
	 * 
	 * @param logicCtrl the controller for method invocation on key event
	 */
	public KeyboardCtrl(LogicController logicCtrl) {
	     KeyboardFocusManager manager =
	     KeyboardFocusManager.getCurrentKeyboardFocusManager();
	     manager.addKeyEventDispatcher( new KeyDispatcher(logicCtrl) );
	}
}
