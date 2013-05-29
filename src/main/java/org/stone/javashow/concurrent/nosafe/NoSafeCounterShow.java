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
 * @since V0.1 2013-5-2下午05:35:32
 */
public class NoSafeCounterShow extends BaseCounterShow{
	private static Logger logger = LoggerFactory.getLogger(NoSafeCounterShow.class);

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		NoSafeCounterShow noSafeCounterShow = new NoSafeCounterShow();
		noSafeCounterShow.doShow();
	}

	@Override
	ICounter getCounter() {
		return new NoSafeCounter();
	}

	@Override
	int getLoop() {
		return 10;
	}

}
