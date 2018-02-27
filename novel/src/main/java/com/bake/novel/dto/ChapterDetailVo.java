package com.bake.novel.dto;

public class ChapterDetailVo {

	private String title;

	private Integer wordcount;

	private Long chaptertime;

	private String content;
	
	private String novelName;
	
	private String novelId;
	
	private String author;
	
	private String nextChapterId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWordcount() {
		return wordcount;
	}

	public void setWordcount(Integer wordcount) {
		this.wordcount = wordcount;
	}

	public Long getChaptertime() {
		return chaptertime;
	}

	public void setChaptertime(Long chaptertime) {
		this.chaptertime = chaptertime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNovelName() {
		return novelName;
	}

	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNextChapterId() {
		return nextChapterId;
	}

	public void setNextChapterId(String nextChapterId) {
		this.nextChapterId = nextChapterId;
	}

	public String getNovelId() {
		return novelId;
	}

	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}
	
	
	
}
