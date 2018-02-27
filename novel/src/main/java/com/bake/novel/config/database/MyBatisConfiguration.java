package com.bake.novel.config.database;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.aspectj.apache.bcel.util.ClassLoaderRepository.SoftHashMap;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MyBatisConfiguration extends MybatisAutoConfiguration {
	
	@Resource(name="masterDataSource")
	private DataSource masterDataSource;
	
	@Resource(name="slaveDataSource")
	private DataSource  slaveDataSource;
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		return super.sqlSessionFactory(roundRobinDataSourceProxy());
	}
	
	public AbstractRoutingDataSource roundRobinDataSourceProxy() {
		ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
		
		SoftHashMap targetDataSource = new ClassLoaderRepository.SoftHashMap();
		
		targetDataSource.put(DataBaseContextHolder.DataBaseType.MASTER, masterDataSource);
		
		targetDataSource.put(DataBaseContextHolder.DataBaseType.SLAVE, slaveDataSource);
		
		//默认数据源
		//proxy.setDefaultTargetDataSource(masterDataSource);
		proxy.setDefaultTargetDataSource(slaveDataSource);
		
		//装入两个主从数据源
		proxy.setTargetDataSources(targetDataSource);
		return proxy;
		
												
	}
	
	

}
