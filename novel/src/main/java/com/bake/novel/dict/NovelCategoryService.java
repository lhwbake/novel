package com.bake.novel.dict;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bake.novel.config.database.WriteOnlyConnection;
import com.bake.novel.entity.NovelCategory;
import com.bake.novel.mapper.NovelCategoryMapper;

@Service
public class NovelCategoryService {
	
	@Autowired
	private NovelCategoryMapper novelCategoryMapper;
	
	@WriteOnlyConnection
	public int saveCategory(NovelCategory novelCategory) {
		return novelCategoryMapper.insert(novelCategory);
	}
	
	public List<Map<String, String>> getCategoryByRank(String rank) {
		return novelCategoryMapper.selectCategoryByRank(rank);
	}
	
	public List<String> getCategoryByParentId(String parentId) {
		return novelCategoryMapper.selectCategoryByParentId(parentId);
	}
	
	
}
