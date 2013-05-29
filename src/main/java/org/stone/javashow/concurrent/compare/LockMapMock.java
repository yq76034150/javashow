/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.compare;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-18下午11:47:14
 */
public class LockMapMock {

    private final ConcurrentHashMap<String/* nsServersTopic */, Set<String/* groupID */>> groupsOfTopics =
        new ConcurrentHashMap<String, Set<String>>();
    private static Map<String,Lock> lockMap = new HashMap<String,Lock>();
    
    static{
    	lockMap.put("t1", new ReentrantLock());
    	lockMap.put("t2", new ReentrantLock());
    	lockMap.put("t3", new ReentrantLock());
    }
    
	public void lock(String str){
		final Set<String> newGroupsOfTopic = new CopyOnWriteArraySet<String>();
        Set<String> groupsOfTopic = this.groupsOfTopics.putIfAbsent(str, newGroupsOfTopic);
        if (null == groupsOfTopic) {
            groupsOfTopic = newGroupsOfTopic;
        }

        lockMap.get(str).lock();
        try {
			System.out.println("lms" + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//logger.error("handle error!", e);
			}
			System.out.println("lme" + Thread.currentThread());
		}finally{
			lockMap.get(str).unlock();
		}
	}

}
