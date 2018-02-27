package com.bake.novel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bake.novel.dict.NovelCategoryService;
import com.bake.novel.dto.ChapterDetailVo;
import com.bake.novel.entity.NovelChapter;
import com.bake.novel.entity.NovelChapterDetail;
import com.bake.novel.entity.NovelInfo;
import com.bake.novel.enumeration.NovelCategoryRank;
import com.bake.novel.enumeration.NovelStatus;
import com.bake.novel.service.NovelService;
import com.bake.novel.util.MongoDBUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Controller
@RequestMapping(value="novel/")
public class NovelController {
	
	private static Logger logger = LoggerFactory.getLogger(NovelController.class);
	
	@Autowired
	private NovelService novelService;
	
	@Autowired
	NovelCategoryService novelCategoryService;

	@RequestMapping(value="/novelList", method=RequestMethod.GET)
	public ModelAndView novelList(HttpServletRequest request) {
		
		int page = 0;
		int size = 20;	
		
		List<String> categpryNameList = Lists.newArrayList();	
		//全部类型
		String type = "all";

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", page);
		param.put("size", size);
		param.put("state", NovelStatus.DONE.getCode());
		param.put("categpryNameList", categpryNameList);
		param.put("type", type);
		
		PageInfo<NovelInfo> pageInfo = novelService.getnovelInfoList(param);
		List<Map<String,String>> categoryList = novelCategoryService.getCategoryByRank(NovelCategoryRank.FIRST.getCode());
		
		ModelAndView mav = new ModelAndView();
		mav.getModelMap().put("pageInfo", pageInfo);
 		mav.getModelMap().put("categoryList", categoryList);
		mav.setViewName("novel-list");
		return mav;
	}
	
	
	@RequestMapping(value="/novelListByCategory", method=RequestMethod.POST)
	@ResponseBody
	public Object novelListByCategory(HttpServletRequest request) {
		
		int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size")==null?"20":request.getParameter("size"));	
		String categoryId = request.getParameter("categoryId");
		
		List<String> categpryNameList = novelCategoryService.getCategoryByParentId(categoryId);
		
		//全部类型
		String type = "category";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", page);
		param.put("size", size);
		param.put("state", NovelStatus.DONE.getCode());
		param.put("categpryNameList", categpryNameList);
		param.put("type", type);
		
		PageInfo<NovelInfo> pageInfo = novelService.getnovelInfoList(param);
 		
		return pageInfo;
	}
	
	/**
	 *  查看指定小说的详情信息
	 */
	@RequestMapping(value="/novelInfo", method=RequestMethod.GET)
	public ModelAndView getNovelInfo(HttpServletRequest request) {
		
		String novelId = request.getParameter("novelId");		
		ModelAndView mav = new ModelAndView();		
		NovelInfo novelInfo = novelService.getNovelInfo(novelId);
		List<NovelChapter> novelChapterList = novelService.getNovelChapterList(novelId);
		
		mav.setViewName("novel-detail");
		mav.getModelMap().put("novelInfo", novelInfo);
		mav.getModelMap().put("novelChapterList", novelChapterList);
		
		return mav;
	}
	
	/**
	 * 查看指定小说的最新章节
	 * @param novelId
	 * @return
	 */
	@RequestMapping(value="/lastNovelChapter", method=RequestMethod.GET)
	public ModelAndView getLastNovelChapter(HttpServletRequest request) {		
		
		String novelId = request.getParameter("novelId");
		
		ModelAndView mav = new ModelAndView();		
		NovelChapter novelChapter =  novelService.getLastNovelChapter(novelId);		
		mav.setViewName("");
		mav.getModelMap().put("novelChapter", novelChapter);		
		return mav;
		
	}
	
	/**
	 * 查看指定章节的小说内容
	 * @throws Exception 
	 */
	@RequestMapping(value="/chapterContent", method=RequestMethod.GET)
	public ModelAndView getChapterContent(HttpServletRequest request) throws Exception {
		
		String novelChapterId = request.getParameter("novelChapterId");		
		ModelAndView mav = new ModelAndView();		
		NovelChapterDetail novelChapterDetail =  novelService.getChapterContent(novelChapterId);
		NovelChapter novelChapter = novelService.getChapter(novelChapterId);
		NovelInfo novelInfo = novelService.getNovelInfo(novelChapter.getNovelid());
		
		String nextChapterId = novelService.getNextChapterId(novelChapter.getNovelid(), novelChapter.getChapterid());
		
		//mongoDB
		String dbName = "novel";
	    String collName = null;
	    if(novelInfo.getId().hashCode()%2==0){
	    	collName = "novelOne";
	    }else{
	    	collName = "novelTwo";
	    }
	    MongoCollection<Document> coll = MongoDBUtil.instance.getCollection(dbName, collName);
	    BasicDBObject query = new BasicDBObject();
	    query.append("_id", novelChapterId);
	    
	    FindIterable<Document> iter = coll.find(query).limit(1);
	    MongoCursor<Document> mongoCursor = iter.iterator();
	    String content = "";
	    while (mongoCursor.hasNext()) {
	    	Document doc = mongoCursor.next();
	    	content = doc.getString("content");
	    	System.out.println("name=" + doc.toJson());
		}
	    
		ChapterDetailVo chapterDetailVo = new ChapterDetailVo();
		
		chapterDetailVo.setTitle(novelChapterDetail.getTitle());
		chapterDetailVo.setContent(content);
		chapterDetailVo.setWordcount(novelChapterDetail.getWordcount());
		chapterDetailVo.setChaptertime(novelChapterDetail.getChaptertime());
		chapterDetailVo.setNovelName(novelInfo.getName());
		chapterDetailVo.setNovelId(novelChapter.getNovelid());
		chapterDetailVo.setAuthor(novelInfo.getAuthor());
		chapterDetailVo.setNextChapterId(nextChapterId);
					
		mav.setViewName("novel-content");
		mav.getModelMap().put("chapterDetailVo", chapterDetailVo);
		
		return mav;
		
	}
	
}
