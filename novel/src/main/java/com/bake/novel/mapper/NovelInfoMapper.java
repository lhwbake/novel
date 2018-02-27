package com.bake.novel.mapper;

import java.util.List;
import java.util.Map;

import com.bake.novel.entity.NovelInfo;

public interface NovelInfoMapper {
	
	 int deleteByPrimaryKey(String id);

	 int insert(NovelInfo record);

	 int insertSelective(NovelInfo record);

	 NovelInfo selectByPrimaryKey(String id);

	 int updateByPrimaryKey(NovelInfo record);
    
     List<NovelInfo> selectNovelInfoList(Map<String, Object> param);
    
    /**
     * 查询小说数量
     * @param state
     * @return
     */
    int countNovelInfo(Map<String, Object> param);

    int updateByPrimaryKeySelective(NovelInfo record);

    int updateByPrimaryKeyWithBLOBs(NovelInfo record);

}