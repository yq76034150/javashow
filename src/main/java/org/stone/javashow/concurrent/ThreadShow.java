/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stone.javashow.jmx.JMXRemoteClientShow;

/**
 * 描述：使用线程展示
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-4-27下午10:26:32
 */
public class ThreadShow {
	private static final Logger logger = LoggerFactory
	.getLogger(ThreadShow.class);
	
	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadShow threadShow = new ThreadShow();
		threadShow.createOneThread();
		threadShow.createOneRunnable();
	}
	
	private void createOneThread(){
		new Thread(){
			public void run(){
				logger.info("create by Thread run");
			}
		}.start();
	}
	
	private void createOneRunnable(){
		Runnable runnable = new Runnable() {			
			public void run() {
				logger.info("create by Runnable run");
			}
		};
		new Thread(runnable).start();
	}

}
