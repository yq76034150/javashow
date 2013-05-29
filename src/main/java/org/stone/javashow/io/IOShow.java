/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 * 
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 * 
 * @since V0.1 2013-5-7下午10:50:38
 */
public class IOShow {
	private static final Logger logger = LoggerFactory.getLogger(IOShow.class);
	
	public static void main(String args[]) throws IOException {
		String fileName = "d:/iotest/test.txt";
		int size = 200;
		IOShow ioShow = new IOShow();
		
		ioShow.write(fileName, size);
		ioShow.read(fileName, size);
		
		//write("d:/iotest/test.txt", size);
//		seq_read(fileName, size);
//		random_read(fileName, size);
	}

	/**
	 * 
	 * 描述：比较 顺序(IO\NIO),随机写
	 * @param fileName
	 * @param size
	 * @throws IOException 
	 */
	public void write(String fileName, int size) throws IOException{
		long init = Runtime.getRuntime().freeMemory();
		ioSeqWrite(fileName, size);
		logger.info("" + (init - Runtime.getRuntime().freeMemory()));
		
		//map不会占用java heap
		init = Runtime.getRuntime().freeMemory();
		nioSeqWrite(fileName, size);
		logger.info("" + (init - Runtime.getRuntime().freeMemory()));
		
		init = Runtime.getRuntime().freeMemory();
		ioRandomWrite(fileName, size);
		logger.info("" + (init - Runtime.getRuntime().freeMemory()));
		
	}
	
	private void ioSeqWrite(String fileName, int size) {
		long start = 0;
		
		try {
			delFile(fileName);
			
			FileOutputStream fis = new FileOutputStream(fileName);
			byte buf[] = new byte[1024];
			int total = 1024 * size;

			start = System.currentTimeMillis();
			int count = 0;
			while (count <= total) {
				fis.write(buf);
				count++;
			}

			fis.close();
		} catch (IOException e) {
			logger.error("handle error!", e);
		}

		logger.info("io seq write file " + size + "M cost: " + (System.currentTimeMillis() - start) + " ms");
	}
	
	private void nioSeqWrite(String fileName, int size) throws IOException {  
		delFile(fileName);		
        FileChannel fc = new RandomAccessFile(fileName, "rw").getChannel(); 
        
        long start = System.currentTimeMillis();
        final ByteBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0, 1024 * 1024 * size);  
        byte buf[] = new byte[1024];
        //ib.put(0);  
        int count = 0;
        int total = 1024 * size;
        while (count < total) {  
            ib.put(buf); 
            count++;
        }  
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @SuppressWarnings("restriction")
            public Object run() {
                try {
                    Method getCleanerMethod = ib.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);

                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(ib, new Object[0]);
                    cleaner.clean();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        fc.close(); 
        
        logger.info("nio seq write file " + size + "M cost: " + (System.currentTimeMillis() - start) + " ms");
    }
	
	private void ioRandomWrite(String fileName, int size) throws IOException {
		delFile(fileName);
		
		byte buf[] = new byte[1024];
        RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw");  
        
        long start = System.currentTimeMillis();
        int count = 0;
        int total = 1024 * size;
        Random random = new Random();
        
        while (count <= total) {                
            raf.write(buf);
            raf.seek(raf.length() - count);
            count++;
        }  
        raf.close();  
        
        logger.info("io random write file " + size + "M cost: " + (System.currentTimeMillis() - start) + " ms");
    }
	
	private void delFile(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			file.delete();
		}
	}


	
	public void read(String fileName, int size) throws IOException{
		long init = Runtime.getRuntime().freeMemory();
		ioSeqRead(fileName, size);
		logger.info("" + (init - Runtime.getRuntime().freeMemory()));
		
		//map不会占用java heap
		init = Runtime.getRuntime().freeMemory();
		nioSeqRead(fileName, size);
		logger.info("" + (init - Runtime.getRuntime().freeMemory()));
		
		init = Runtime.getRuntime().freeMemory();
		ioRandomRead(fileName, size);
		logger.info("" + (init - Runtime.getRuntime().freeMemory()));
		
	}

	private static void ioSeqRead(final String file, int size) {
		long start = 0;
		try {
			FileInputStream fis = new FileInputStream(file);
			byte buf[] = new byte[1024];
			int cnt = 0;
			int n;
			int total = 1024 * size;
			
			start = System.currentTimeMillis();
			while ((n = fis.read(buf)) != -1) {
				size += n;
				if (size >= total) {
					break;
				}

			}
			fis.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		logger.info("io seq read file " + size + "M cost: " + (System.currentTimeMillis() - start) + " ms");
	}
	
	private void nioSeqRead(String fileName, int size) throws IOException {  
		delFile(fileName);		
        FileChannel fc = new RandomAccessFile(fileName, "rw").getChannel(); 
        
        long start = System.currentTimeMillis();
        final ByteBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0, 1024 * 1024 * size);  
        byte buf[] = new byte[1024];
        //ib.put(0);  
        int count = 0;
        int total = 1024 * size;
        while (count < total) {  
            ib.get(buf); 
            count++;
        }  
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @SuppressWarnings("restriction")
            public Object run() {
                try {
                    Method getCleanerMethod = ib.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);

                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(ib, new Object[0]);
                    cleaner.clean();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        fc.close(); 
        
        logger.info("nio seq read file " + size + "M cost: " + (System.currentTimeMillis() - start) + " ms");
    }
	
	private static void ioRandomRead(final String fileName, int size) {
		long start = 0;
		int total = 0;
		int cnt = 0;
		try {
			File file = new File(fileName);
			RandomAccessFile fis = new RandomAccessFile(file, "r");

			byte buf[] = new byte[1024];
			
			int n = 0;

			//分成5块  每次 size/5 大小
			Map<Integer, Integer> seekMap = new HashMap<Integer, Integer>();
			int len = 1024 * 1024 * size / 102400;
			start = System.currentTimeMillis();
			for(int i = 102400 - 1; i >= 0; i--){
				n = 0;
				
				fis.seek(i * len);
				while (n != len) {
					n += fis.read(buf);
					total += 1024;
					cnt++;
				}
			}
			
			fis.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		long end = System.currentTimeMillis();
		//System.out.println(total + " time: " + (end - start) + " count " + cnt);
		logger.info("io random read file " + size + "M cost: " + (System.currentTimeMillis() - start) + " ms count " + cnt);

	}
}
