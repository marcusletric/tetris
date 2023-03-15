package animations;

import java.util.ArrayList;
import java.util.Collection;

import utils.AnimationUtil;
import utils.Callback;
import utils.CallbackInstance;
import view.RenderInterface;

/**
 * Game over animation. 
 * 
 * extends {@link CallbackInstance} class, implements {@link Callback} interface
 */

public class GameOverAnim extends CallbackInstance implements Callback{
	
	/**
	 * Constructor method, stores the parameters for callback.
	 * 
	 * @param params parameters to store
	 */
	public GameOverAnim(Object[] params) {
		super(params);
	}
	
	/**
	 * Method called on every frame.
	 * 
	 * @param callInjection array of parameters injected by {@link AnimationUtil} frameStep method
	 */
	@Override
	public void call(Object[] callInjection) {
		Collection<Integer> animatedLine = new ArrayList<Integer>();
		animatedLine.add((int)(callInjection[1]) - 1 - (int)(callInjection[0]));
		((RenderInterface)params[0]).invertLines(animatedLine);
	}
}
