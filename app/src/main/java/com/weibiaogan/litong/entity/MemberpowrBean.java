package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
public class MemberpowrBean {

    /**
     * id : 1
     * title : 会员特权
     * content : <p style="text-align: center;"><strong>会员权限</strong></p><p>①可以自己在APP上上传头像</p><p>②在首页工人以及工人列表页，排名靠前</p><p><br/></p>
     */

    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
