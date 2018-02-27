package com.bake.novel;

import java.io.IOException;
import java.util.Date;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bake.novel.dict.NovelCategoryService;
import com.bake.novel.entity.NovelCategory;
import com.bake.novel.enumeration.NovelCategoryRank;
import com.bake.novel.util.KeyUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NovelApplicationTests {
	
	@Autowired
	private NovelCategoryService novelCategoryService;

	//@Test
	public void contextLoads() {
		              		  		
		//String[] type = {"奇幻玄幻","武侠仙侠","历史军事","都市娱乐","科幻游戏","悬疑灵异","竞技同人","评论文集"};
		
//		String[] type = {"东方玄幻","上古神话","吸血传奇","奇幻修真","穿越历史","灵异神怪","异世大陆","异术超能","穿梭时空","西方奇幻","现代修真","进化变异","转世重生"};   //奇幻玄幻 
//		String[] type = {"传统武侠","古典仙侠","新派武侠","江湖情仇","王朝争霸","变身情缘"};   //武侠仙侠
//		String[] type = {"历史传奇","历史传记","战争幻想","架空历史","特种军旅","现代战争","间谍暗战"};   //历史军事
//		String[] type = {"都市异能","都市生活","都市重生","婚恋家庭","宦海仕途","职场商战","青春校园","魔法校园"};   //都市娱乐
//		String[] type = {"星际争霸","虚拟网游","游戏评论","游戏设定","游戏攻略","游戏小说","末世危机"};   //科幻游戏
//		String[] type = {" 恐怖惊悚","推理悬念","探险异闻"};   //悬疑灵异
//		String[] type = {"体育竞技","动漫同人","小说同人","影视同人","游戏同人","电子竞技"};   //竞技同人
		String[] type = {"个人文集","神秘文化","集体创作","科技时代"};   //评论文集
		
		
		for(int i=0;i<type.length;i++) {
			NovelCategory nc = new NovelCategory();
			nc.setId(KeyUtil.generatorUUID());
			nc.setName(type[i]);
			nc.setParentId("fb421b4c-4f24-11e7-a195-005056c00008");
			nc.setRank(NovelCategoryRank.SECOND.getCode());
			nc.setSortNum(i+1);
			Date now = new Date();
			nc.setUpdateTime(now);
			nc.setCreateTime(now);
			novelCategoryService.saveCategory(nc);
		}		
		
	}
	
	@Test
	public void getHTML() {
		try {
			Document doc = Jsoup.connect("https://www.zhihu.com/question/39501641").get();
			System.err.println(doc);
			Elements newsHeadLines = doc.select("span.RichText.CopyrightRichText-richText");
			ListIterator<String> iterator = newsHeadLines.eachText().listIterator();
			while(iterator.hasNext()) {
				String content = iterator.next();
				System.out.println(content);
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
