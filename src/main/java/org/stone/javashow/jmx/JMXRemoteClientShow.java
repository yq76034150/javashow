/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stone.javashow.manager.SystemShow;

/**
 * 描述：
 * 
 * @author hanlin.yq
 * 
 * @since V0.1 2013-5-1下午09:12:32
 */
public class JMXRemoteClientShow {
	private static final Logger logger = LoggerFactory
			.getLogger(JMXRemoteClientShow.class);

	/**
	 * 描述：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JMXServiceURL serviceURL = new JMXServiceURL("http", "127.0.0.1",
					8000);
			JMXConnector conn = JMXConnectorFactory.connect(serviceURL);
			MBeanServerConnection mbs = conn.getMBeanServerConnection();

			// 获取远程memorymxbean
			MemoryMXBean memBean = ManagementFactory.newPlatformMXBeanProxy(
					mbs, ManagementFactory.MEMORY_MXBEAN_NAME,
					MemoryMXBean.class);
			// 获取远程opretingsystemmxbean
			OperatingSystemMXBean opMXbean = ManagementFactory
					.newPlatformMXBeanProxy(mbs,
							ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
							OperatingSystemMXBean.class);

			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				logger.error("InterruptedException occurred while MemoryCollector sleeping...");
			}

			MemoryUsage heap = memBean.getHeapMemoryUsage();
			MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();
			long heapSizeUsed = heap.getUsed();// 堆使用的大小
			long nonHeapSizeUsed = nonHeap.getUsed();
			long heapCommitedSize = heap.getCommitted();
			long nonHeapCommitedSize = nonHeap.getCommitted();

//			Long start = System.currentTimeMillis();
//			long startT = opMXbean.getProcessCpuTime();
//			/** Collect data every 5 seconds */
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				logger.error("InterruptedException occurred while MemoryCollector sleeping...");
//			}
//			Long end = System.currentTimeMillis();
//			long endT = opMXbean.getProcessCpuTime();
			// end - start 即为当前采集的时间单元，单位ms
			// endT - startT 为当前时间单元内cpu使用的时间，单位为ns
			// 所以：double ratio =
			// (entT-startT)/1000000.0/(end-start)/opMXbean.getAvailableProcessors()
		} catch (Exception e) {
			logger.error("conn jmx error!", e);
		}

	}

}
