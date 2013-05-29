/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.nosafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 描述：使用读写锁保证资源线程安全
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-5-2下午09:17:10
 */
public class LockCounter implements ICounter{
	private int count = 0;
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();
	
	public int add(int increase){
		writeLock.lock();
		try{
			count = count + increase;
		}finally{
			writeLock.unlock();	
		}
		return count;
	}
	
	public int get(){
		readLock.lock();
		try{
			return count;
		}finally{
			readLock.unlock();
		}
	}
}
