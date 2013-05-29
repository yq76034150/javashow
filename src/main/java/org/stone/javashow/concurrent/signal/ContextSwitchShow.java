/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.signal;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-11下午10:08:42
 */
public class ContextSwitchShow {
	private static final Logger logger = LoggerFactory.getLogger(ContextSwitchShow.class);
	
	static final int RUNS = 3;
	static final int ITERATES = 1000000;
	static AtomicReference<Thread> turn = new AtomicReference<Thread>();

	static final class WorkerThread extends Thread {
		volatile Thread other;
		volatile int nparks;

		public void run() {
			final AtomicReference<Thread> t = turn;
			final Thread other = this.other;
			if (turn == null || other == null)
				throw new NullPointerException();
			int p = 0;
			for (int i = 0; i < ITERATES; ++i) {
				while (!t.compareAndSet(other, this)) {
					LockSupport.park();
					++p;
				}
				LockSupport.unpark(other);
			}
			LockSupport.unpark(other);
			nparks = p;
			logger.info("parks: " + p);

		}
	}

	static void test() throws Exception {
		WorkerThread a = new WorkerThread();
		WorkerThread b = new WorkerThread();
		a.other = b;
		b.other = a;
		turn.set(a);
		long startTime = System.nanoTime();
		a.start();
		b.start();
		a.join();
		b.join();
		long endTime = System.nanoTime();
		int parkNum = a.nparks + b.nparks;
		logger.info("Average time: " + ((endTime - startTime) / parkNum)
				+ "ns");
	}

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < RUNS; i++) {
			test();
		}
		logger.info("cost time: " + (System.currentTimeMillis() - start) + " ms");
	}

}
