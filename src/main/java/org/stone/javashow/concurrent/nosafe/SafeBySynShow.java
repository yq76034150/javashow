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
 * @since V0.1 2013-5-2下午08:57:04
 */
public class SafeBySynShow extends BaseCounterShow{
	private static Logger logger = LoggerFactory.getLogger(SafeBySynShow.class);

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		SafeBySynShow safeCounterShow = new SafeBySynShow();
		safeCounterShow.doShow();	
	}
	
	@Override
	ICounter getCounter() {
		return new SynchronizedCounter();
	}

	@Override
	int getLoop() {
		return 10;
	}

}
