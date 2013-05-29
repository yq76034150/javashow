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
public class SafeByLockShow extends BaseCounterShow{
	private static Logger logger = LoggerFactory.getLogger(SafeByLockShow.class);

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		SafeByLockShow safeCounterShow = new SafeByLockShow();
		safeCounterShow.doShow();	
	}
	
	@Override
	ICounter getCounter() {
		return new LockCounter();
	}

	@Override
	int getLoop() {
		return 10;
	}

}
