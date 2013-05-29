/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.manager;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：内存管理相关
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-4-27下午10:40:14
 */
public class MemoryShow {
	private static final Logger logger = LoggerFactory.getLogger(MemoryShow.class); 
	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		MemoryShow memoryShow = new MemoryShow();
		memoryShow.showMemory();
	}
	
	private void showMemory(){
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();     
		logger.info("dump max " + memoryMXBean.getHeapMemoryUsage().getMax());   
		logger.info("dump l " + memoryMXBean.getHeapMemoryUsage().getInit());   
		logger.info("dump used " + memoryMXBean.getHeapMemoryUsage().getUsed());   
		logger.info("getHeapMemoryUsage " + memoryMXBean.getHeapMemoryUsage());   
		logger.info("getHeapMemoryUsage " + memoryMXBean.getHeapMemoryUsage().hashCode());     
		logger.info("=================");   
		logger.info("no dump max" + memoryMXBean.getNonHeapMemoryUsage().getMax());   
		logger.info("no dump l " + memoryMXBean.getNonHeapMemoryUsage().getInit());   
		logger.info("getNonHeapMemoryUsage " + memoryMXBean.getNonHeapMemoryUsage());   
		logger.info("getNonHeapMemoryUsage " + memoryMXBean.getNonHeapMemoryUsage().hashCode());
	}

}
