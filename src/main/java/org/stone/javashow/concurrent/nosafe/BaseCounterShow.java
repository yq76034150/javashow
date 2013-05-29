/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.nosafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-5-2下午09:43:57
 */
public abstract class BaseCounterShow {
	private static Logger logger = LoggerFactory.getLogger(BaseCounterShow.class);
	
	protected void doShow(){
		final ICounter counter = getCounter();
		for(int i = 0, size = getLoop(); i < size; i++){
			new Thread(){
				public void run(){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						
					}
					counter.add(1);
					logger.info("c should is 1, but is " + counter.get());
				}
			}.start();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
		logger.info("==========================" + counter.get());
	}
	
	abstract ICounter getCounter();
	abstract int getLoop();
}
