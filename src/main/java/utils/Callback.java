package utils;

/**
 * Interface for wrapped function.
 */
public interface Callback {
	
	/**
	 * Method to call.
	 * 
	 * @param callInjection parameters injected on call
	 */
	public void call(Object[] callInjection);

}