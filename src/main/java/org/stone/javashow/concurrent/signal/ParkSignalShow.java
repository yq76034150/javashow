/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.signal;

import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-11下午10:47:23
 */
public class ParkSignalShow {
	private static final Logger logger = LoggerFactory.getLogger(ParkSignalShow.class);
	
	/**
	 * 描述：
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final Thread t1 = new Thread(){
			public void run(){
				logger.info("t1 start wait");
				LockSupport.park();
				logger.info("t1 get signal");
			}
		};
		
		Thread t2 = new Thread(){
			public void run(){
				logger.info("t2 start signal");
				LockSupport.unpark(t1);
				logger.info("t2 end");
			}
		};
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

}
