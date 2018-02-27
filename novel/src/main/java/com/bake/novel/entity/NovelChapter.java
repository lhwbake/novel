package com.bake.novel.entity;

public class NovelChapter {
	
	/*
	 * 主键ID
	 */
    private String id;

    /*
     * 小说ID
     */
    private String novelid;

    /*
     * 章对应的RUL
     */
    private String url;

    /*
     * 章名称
     */
    private String title;

   /*
    * 正文字数
    */
    private Integer wordcount;

    /*
     * 章节排序ID
     */
    private Integer chapterid;

    /*
     * 更新时间
     */
    private Long chaptertime;

    private Long createtime;

    /*
     * 章的状态
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNovelid() {
        return novelid;
    }

    public void setNovelid(String novelid) {
        this.novelid = novelid == null ? null : novelid.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getWordcount() {
        return wordcount;
    }

    public void setWordcount(Integer wordcount) {
        this.wordcount = wordcount;
    }

    public Integer getChapterid() {
        return chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public Long getChaptertime() {
        return chaptertime;
    }

    public void setChaptertime(Long chaptertime) {
        this.chaptertime = chaptertime;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}