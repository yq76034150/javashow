/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.janino;

import java.lang.reflect.InvocationTargetException;

import org.codehaus.janino.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;

/**
 * 描述：
 *
 * @author hanlin.yq
 *
 * @since V0.1 2013-4-21下午09:49:49
 */
public class ExpressionEvaluatorShow {

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExpressionEvaluator ee = new ExpressionEvaluator(
				    "c > d ? c : d",                     // expression
				    int.class,                           // expressionType
				    new String[] { "c", "d" },           // parameterNames
				    new Class[] { int.class, int.class } // parameterTypes
				);
			
			// Evaluate it with varying parameter values; very fast.
			Integer res = (Integer) ee.evaluate(
			    new Object[] {          // parameterValues
			        new Integer(10),
			        new Integer(11),
			    }
			);
			System.out.println("res = " + res);
		} catch (CompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
			
	}

}
