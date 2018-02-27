package com.bake.novel.mapper;

import com.bake.novel.entity.CrawlList;

public interface CrawlListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlList record);

    int insertSelective(CrawlList record);

    CrawlList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlList record);

    int updateByPrimaryKey(CrawlList record);
}