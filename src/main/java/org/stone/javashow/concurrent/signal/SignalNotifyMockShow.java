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
 * 描述：通过共享对象线程间通信
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-6下午10:06:22
 */
public class SignalNotifyMockShow {
	private static final Logger logger = LoggerFactory.getLogger(SignalNotifyMockShow.class);
	
	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		final SignalNotifyMock signalNotifyMock = new SignalNotifyMock();
		
		//Thread 1 等待另一线程设置signal
		new Thread(){
			public void run(){
				logger.info("Thread 1 doSomething4Wait");
				signalNotifyMock.doSomething4Wait();
				logger.info("Thread 1 get signal!");
			}
		}.start();
		
		new Thread(){
			public void run(){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.error("sleep error!", e);
				}
				signalNotifyMock.doNotify();
				logger.info("give Thread 1 the signal!");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					logger.error("sleep error!", e);
				}
			}
		}.start();
	}

}
