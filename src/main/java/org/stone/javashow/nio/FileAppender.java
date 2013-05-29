/**
 * javaShow
 *
 * www.1lifes.com
 * (C) 易生活 2003-2011
 */
package org.stone.javashow.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：采用nio存储文件
 *
 * @author <a href="mailto:yq76034150@gmail.com">yq76034150</a>
 *
 * @since V0.1 2013-5-4下午08:31:12
 */
public class FileAppender {
	private static final Logger logger = LoggerFactory.getLogger(FileAppender.class);
	private File file;
	private FileChannel fileChannel;
	private MappedByteBuffer mappedByteBuffer;
	
	private static final int fileSize = 1024*1024;
	
	public FileAppender(String path, String fileName, int size) {
		try {
			init(path, fileName, size);
		} catch (IOException e) {
			logger.error("file create error!", e);
		}
	}

	private void init(String path, String fileName, int size) throws IOException {
		file = new File(path + fileName);
		File filePath = new File(path);
		if(!filePath.exists()){
			filePath.mkdir();
		}
		if(!file.exists()){
			file.createNewFile();
		}
		
		fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
		mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, size);
	}
	
	public void write(byte[] datas){
		mappedByteBuffer.put(datas);
		byte[] bs = "test".getBytes();
		int i = 0;
		for(byte b : bs){
			mappedByteBuffer.put(i, b);
			i++;
		}
	}
	
	public String get(int offset, int length){
		byte[] datas = new byte[length];
		mappedByteBuffer.position(offset);
		mappedByteBuffer.get(datas);
		return new String(datas);
	}
	
}
