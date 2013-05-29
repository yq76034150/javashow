/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.nosafe;

/**
 * 描述：
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-5-2下午08:55:53
 */
public class VolatileCounter {
	private volatile int count = 0;
	
	public int add(int increase){
		count = count + increase;
		return count;
	}
	
	public int get(){
		return count;
	}
}
