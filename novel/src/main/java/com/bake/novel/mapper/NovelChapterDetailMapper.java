package com.bake.novel.mapper;

import com.bake.novel.entity.NovelChapterDetail;

public interface NovelChapterDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(NovelChapterDetail record);

    int insertSelective(NovelChapterDetail record);

    NovelChapterDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NovelChapterDetail record);

    int updateByPrimaryKey(NovelChapterDetail record);
}