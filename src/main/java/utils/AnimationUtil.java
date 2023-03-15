package utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Factory utility for animations.
 */
public class AnimationUtil {
	
	private Timer timer;
	
	/**
	 * Constructor, store passed timer instance.
	 * 
	 * @param timer timer instance to store
	 */
	public AnimationUtil(Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * Factory prototype for animation.
	 */
	public class Animation{
		
		/**
		 * Current frame index.
		 */
		private int frame = 0;
		
		/**
		 * Number of frames to step through.
		 */
		private int frameNum = 0;
		
		/**
		 * Length of frame in ms. 
		 */
		private int frameLength = 0;
		
		/**
		 * Wrapped method called on frame execution.
		 */
		private Callback frameHandler;
		
		/**
		 * Wrapped method called after animation end.
		 */
		private Callback callback;
		
		/**
		 * Constructor, create new animation instance.
		 * 
		 * @param frameNum number of frames to render
		 * @param frameLength length of a frame in ms
		 * @param frameHandler encapsulated method to invoke on frame step
		 * @param callback encapsulated method to call after animation
		 */
		public Animation(int frameNum, int frameLength, Callback frameHandler, Callback callback) {
			this.frameNum = frameNum;
			this.frameLength = frameLength;
			this.frameHandler = frameHandler;
			this.callback = callback;
			frameStep();
		}
		
		/**
		 * Schedule timed frame handler call.
		 */
		private void registerTimeout(){
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					frameStep();
				}
			}, frameLength);
		}
		
		/**
		 * Invoke wrapped methods on frame, inject information
		 * about animation as parameters.
		 */
		public void frameStep(){
			if(frame < frameNum){
				frameHandler.call(new Object[]{frame,frameNum});
				frame++;
				registerTimeout();
			} else {
				callback.call(new Object[]{frame,frameNum});
			}
		}
	}
	
	/**
	 * Register an animation.
	 * 
	 * @param frameNum number of frames to render
	 * @param frameLength length of a frame in ms
	 * @param frameHandler encapsulated method to invoke on frame step
	 * @param callback encapsulated method to call after animation
	 */
	public void addAnimation(int frameNum, int frameLength, Callback frameHandler, Callback callback){
		new Animation(frameNum, frameLength, frameHandler, callback);
	}
	
	
}
