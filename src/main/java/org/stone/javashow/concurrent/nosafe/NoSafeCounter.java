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
 * @since V0.1 2013-5-2下午05:33:32
 */
public class NoSafeCounter implements ICounter{
	private int count = 0;
	
	public int add(int increase){
		count = count + increase;
		return count;
	}
	
	public int get(){
		return count;
	}
}
