/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.signal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-6下午10:24:16
 */
public class SignalNotifyMock {
	private static final Logger logger = LoggerFactory.getLogger(SignalNotifyMock.class);
	
	private Monitor monitor = new Monitor();
	
	public void doSomething4Wait(){
		synchronized (monitor) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				logger.error("interrupted error!", e);
			}
		}		
	}
	
	public void doNotify(){
		synchronized (monitor) {
			monitor.notify();
		}
	}
}
