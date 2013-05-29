package org.stone.javashow;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	"uniterror_5555".substring("uniterror_".length());
        System.out.println( "Hello World!" );
        final long start = System.currentTimeMillis();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
				}
				System.out.println(" " + (System.currentTimeMillis() - start));
			}
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }
}
