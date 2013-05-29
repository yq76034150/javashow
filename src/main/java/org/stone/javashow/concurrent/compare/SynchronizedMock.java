/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.concurrent.compare;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-18下午11:23:20
 */
public class SynchronizedMock {
    private final ConcurrentHashMap<String/* nsServersTopic */, Set<String/* groupID */>> groupsOfTopics =
        new ConcurrentHashMap<String, Set<String>>();
    
	public void lock(String str){
		final Set<String> newGroupsOfTopic = new CopyOnWriteArraySet<String>();
        Set<String> groupsOfTopic = this.groupsOfTopics.putIfAbsent(str, newGroupsOfTopic);
        if (null == groupsOfTopic) {
            groupsOfTopic = newGroupsOfTopic;
        }
        
        synchronized (groupsOfTopic) {
        	System.out.println("ss" + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//logger.error("handle error!", e);
			}
			System.out.println("se" + Thread.currentThread());
		}
	}
}
