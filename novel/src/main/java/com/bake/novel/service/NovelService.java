package com.bake.novel.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bake.novel.entity.NovelChapter;
import com.bake.novel.entity.NovelChapterDetail;
import com.bake.novel.entity.NovelInfo;
import com.bake.novel.mapper.NovelChapterDetailMapper;
import com.bake.novel.mapper.NovelChapterMapper;
import com.bake.novel.mapper.NovelInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class NovelService {
	
	private static Logger logger = LoggerFactory.getLogger(NovelService.class);
	
	@Autowired
	private NovelInfoMapper novelInfoMapper;
	
	@Autowired
	private NovelChapterMapper novelChapterMapper;
	
	@Autowired
	private NovelChapterDetailMapper novelChapterDetailMapper;
	/**
	 * 查看下小说列表
	 * @return
	 */
	public PageInfo<NovelInfo> getnovelInfoList(Map<String, Object> param) {
		Integer page = (Integer)param.get("page");
		Integer size = (Integer)param.get("size");
		
		PageHelper.startPage(page, size, true);

		List<NovelInfo> novelInfoList = novelInfoMapper.selectNovelInfoList(param);
		PageInfo<NovelInfo> pageInfo = new PageInfo<NovelInfo>(novelInfoList);
		logger.info("小说列表->{}",novelInfoList);
		return pageInfo;
	}
	
	/**
	 * 查看小说数量
	 * @param state
	 * @return
	 */
	public int getnovelInfoCount(Map<String, Object> param) {
		return novelInfoMapper.countNovelInfo(param);
	}
	
	
	/**
	 *  查看指定小说的详情信息
	 */
	public NovelInfo getNovelInfo(String novelId) {
		return novelInfoMapper.selectByPrimaryKey(novelId);
	}
	
	/**
	 * 查看指定小说的章节列表
	 * @param novelId
	 * @return
	 */
	public List<NovelChapter> getNovelChapterList(String novelId) {		
		return novelChapterMapper.selectNovelChaptersByNovelId(novelId);
	}
	
	/**
	 * 查看指定小说的章节的下一章ID
	 * @param novelId
	 * @return
	 */
	public String getNextChapterId(String novelId, int chapterId) {		
		return novelChapterMapper.selectNextChapterId(novelId,chapterId);
	}
	
	/**
	 * 查看指定小说的最新章节
	 * @param novelId
	 * @return
	 */
	public NovelChapter getLastNovelChapter(String novelId) {		
		return novelChapterMapper.selectLastNovelChaptersByNovelId(novelId);
	}
	
	/**
	 * 查看指定章节的小说内容
	 */
	public NovelChapterDetail getChapterContent(String chapterId) {
		return novelChapterDetailMapper.selectByPrimaryKey(chapterId);
	}
	
	/**
	 * 查看指定章节的信息
	 */
	public NovelChapter getChapter(String chapterId) {
		return novelChapterMapper.selectByPrimaryKey(chapterId);
	}

}
