package animations;

import java.util.Collection;

import utils.AnimationUtil;
import utils.Callback;
import utils.CallbackInstance;
import view.RenderInterface;

/**
 * Blinking lines animation.
 *  
 * extends {@link CallbackInstance} class, implements {@link Callback} interface
 */
public class InvertLines extends CallbackInstance implements Callback{	
	
	/**
	 * Constructor method, stores the parameters for callback.
	 * 
	 * @param params parameters to store
	 */
	public InvertLines(Object[] params) {
		super(params);
	}
	
	/**
	 * Method called on every frame.
	 * 
	 * @param callInjection array of parameters injected by {@link AnimationUtil} frameStep method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void call(Object[] callInjection) {
		((RenderInterface)params[0]).invertLines((Collection<Integer>)params[1]);
	}
}