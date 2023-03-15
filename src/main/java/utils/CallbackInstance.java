package utils;

/**
 * Callback instance to hold parameters of callback.
 */
public class CallbackInstance {

	/**
	 * Parameters to store for the callback.
	 */
	protected Object[] params;
	
	/**
	 * Constructor,
	 * store parameters for callback.
	 * 
	 * @param params parameters to store
	 */
	public CallbackInstance(Object[] params) {
		this.params = params.clone();
	}
}
