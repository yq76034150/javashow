/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.nosafe;

/**
 * 描述：使用同步块保证资源线程安全
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-5-2下午09:31:28
 */
public class SynchronizedCounter implements ICounter{
	private int count = 0;
	
	public synchronized int add(int increase){
		count = count + increase;
		return count;
	}
	
	public int get(){
		return count;
	}
}
