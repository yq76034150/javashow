/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.compare;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-18下午11:28:20
 */
public class CompareShow {

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		final SynchronizedMock synchronizedMock = new SynchronizedMock();
		final LockMock lockMock = new LockMock();
		final LockMapMock lockMapMock = new LockMapMock();
		new Thread(){
			public void run(){
				synchronizedMock.lock("t1");
			}
		}.start();
		new Thread(){
			public void run(){
				synchronizedMock.lock("t2");
			}
		}.start();
		new Thread(){
			public void run(){
				synchronizedMock.lock("t3");
			}
		}.start();
		
		new Thread(){
			public void run(){
				lockMock.lock("t1");
			}
		}.start();
		new Thread(){
			public void run(){
				lockMock.lock("t2");
			}
		}.start();
		new Thread(){
			public void run(){
				lockMock.lock("t3");
			}
		}.start();
		
		new Thread(){
			public void run(){
				lockMapMock.lock("t1");
			}
		}.start();
		new Thread(){
			public void run(){
				lockMapMock.lock("t2");
			}
		}.start();
		new Thread(){
			public void run(){
				lockMapMock.lock("t3");
			}
		}.start();
	}

}
