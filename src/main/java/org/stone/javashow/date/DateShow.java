/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.date;

import java.util.Date;

/**
 * 描述：
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-4-18下午03:53:38
 */
public class DateShow {

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		Date oldDate = new Date();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date newDate = new Date();
		System.out.println(newDate.after(oldDate));
		System.out.println(newDate.equals(oldDate));
		System.out.println(newDate.getYear() == oldDate.getYear() &&
				newDate.getMonth() == oldDate.getMonth() &&
				newDate.getDay() == oldDate.getDay());
	}

}
