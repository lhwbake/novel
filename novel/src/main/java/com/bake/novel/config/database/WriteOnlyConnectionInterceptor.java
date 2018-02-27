package com.bake.novel.config.database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WriteOnlyConnectionInterceptor implements Ordered{
	
	public static final Logger logger = LoggerFactory.getLogger(WriteOnlyConnectionInterceptor.class);

	@Around("@annotation(writeOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, WriteOnlyConnection writeOnlyConnection) throws Throwable {
		try{
			logger.info("----set database connection 2 write only---------------");
			DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.MASTER);
			Object result = proceedingJoinPoint.proceed();
			return result;
			
		}finally{
			DataBaseContextHolder.clearDataBaseType();
			logger.info("--------------------clear database connection-----------------");
		}
	
	}
	
	
	@Override
	public int getOrder() {
		return 0;
	}
	
	

}
