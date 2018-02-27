package com.bake.novel.enumeration;

public enum NovelCategoryRank {

	DEFAULT("0"),FIRST("1"),SECOND("2"),THIRD("3"),FOURTH("4");
	
	private String code;
	
	private NovelCategoryRank(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
