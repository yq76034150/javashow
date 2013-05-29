/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.nio;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 描述：
 *
 * @author <a href="mailto:yq76034150@gmail.com>yq76034150</a>
 *
 * @since V0.1 2013-5-4下午09:07:42
 */
public class NioShow {

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		FileAppender fileAppender = new FileAppender("G:/data/", "test1", 1024*5);
		byte[] datas = null;
		Map<Long, Integer> offsetMap = new HashMap<Long, Integer>();
		int offset = 0;
		for(int i = 0; i < 100; i++){
			DataBean dataBean = new DataBean();
			dataBean.setId(i);
			dataBean.setContent("content" + i);
			datas = JSON.toJSONString(dataBean).getBytes();
			fileAppender.write(datas);
			offsetMap.put(Long.valueOf(i), Integer.valueOf(offset));
			offset = offset + datas.length;						
		}
		
		
		int  i = 10;
		System.out.println(fileAppender.get(offsetMap.get(Long.valueOf(i - 1)), 
				offsetMap.get(Long.valueOf(i)) - offsetMap.get(Long.valueOf(i - 1))));
	}

}
