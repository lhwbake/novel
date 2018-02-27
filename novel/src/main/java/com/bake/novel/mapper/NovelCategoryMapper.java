package com.bake.novel.mapper;

import java.util.List;
import java.util.Map;

import com.bake.novel.entity.NovelCategory;

public interface NovelCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(NovelCategory record);

    int insertSelective(NovelCategory record);

    NovelCategory selectByPrimaryKey(String id);
    
    List<Map<String, String>> selectCategoryByRank(String rank);
    
    List<String> selectCategoryByParentId(String parentId);

    int updateByPrimaryKeySelective(NovelCategory record);

    int updateByPrimaryKey(NovelCategory record);
}