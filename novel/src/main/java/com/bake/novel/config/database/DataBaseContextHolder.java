package com.bake.novel.config.database;

public class DataBaseContextHolder {
	
	public enum DataBaseType {
		MASTER, SLAVE
	}
	
	private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<DataBaseType>();
	
	public static void setDataBaseType(DataBaseType dataBaseType) {
		contextHolder.set(dataBaseType);
	}
	
	public static DataBaseType getDataBaseType() {
		return contextHolder.get() == null ? DataBaseType.SLAVE : contextHolder.get();
	}
	
	public static void clearDataBaseType() {
		contextHolder.remove();
	}
}
