/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-4-17上午10:21:51
 */
public class LogbackShow {
	private static final Logger logger = LoggerFactory.getLogger(LogbackShow.class);
	
	/**
	 * 
	 * 描述：简单字符的字符串 + 比 format性能好
	 * @param args
	 */
	public static void main(String[] args){
		long start = System.nanoTime();
		for(int i = 0; i < 200; i++){
			logger.error("test format {}", "str");
		}
		System.out.println(System.nanoTime() - start);
		
		start = System.nanoTime();
		for(int i = 0; i < 200; i++){
			logger.error("test format {}" + "str");
		}
		System.out.println(System.nanoTime() - start);
		
		logger.info("test info");
	}
}
