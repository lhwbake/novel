package com.bake.novel.conn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadNovelContent {
	
	private static Logger logger = LoggerFactory.getLogger(ReadNovelContent.class);
		
	private static int maxTryTimes = 3;

	public static String read(String fileName) throws Exception {
		//重试次数
		int n = maxTryTimes;
		
		while (n>0) {
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = null;
			
			try{
					
				//连接hadoop
				HadoopConnectionPool conn = HadoopConnectionPool.getInstance();
				Configuration conf = conn.getConfiguration();
				
				//向hadoop里写文件
				FileSystem fs = FileSystem.get(conf);
				
				String filePath = "/novel/"+fileName;
				Path path = new Path(filePath);
				
				in = fs.open(path);				
								
				IOUtils.copyBytes(in, out, 4096, true);
				
				byte[] b = out.toByteArray();
							
				String novelContent = new String(b);
				
				out.flush();
				
				logger.info("read content success!content = : {}", novelContent);		
				
				return novelContent;
				
			}catch(FileNotFoundException e) {
				logger.error("File->{} does not exist", fileName);
				throw e;
				
			}catch(Exception e) {
				n--;
				logger.error("file can`t read from hdfs! tryTime = {}" , n);
				logger.error("error message->{}" , e.getMessage());		
				
				//当n=0时抛出异常
				if(n==0) {
					throw e;
				}
				
			}finally{
				
				if(in != null) {
					in.close();
				}
				
				if(out != null) {
					out.close();
				}						
			}
		}
		
		return "";
				
	}
	
}
