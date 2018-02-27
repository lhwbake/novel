package com.bake.novel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bake.novel.entity.NovelChapter;

public interface NovelChapterMapper {
    int deleteByPrimaryKey(String id);

    int insert(NovelChapter record);

    int insertSelective(NovelChapter record);

    NovelChapter selectByPrimaryKey(String id);
    
    NovelChapter selectLastNovelChaptersByNovelId(String id);
    
    List<NovelChapter> selectNovelChaptersByNovelId(String novelId);
    
    String selectNextChapterId(@Param("novelId") String novelId, @Param("chapterId") int chapterId);

    int updateByPrimaryKeySelective(NovelChapter record);

    int updateByPrimaryKey(NovelChapter record);
}