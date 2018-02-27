package com.bake.novel.config.database;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 
 * @author hadoop
 * 基础数据库服务 
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> { 
	
}
