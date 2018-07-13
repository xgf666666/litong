package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/7/12
 * describe:
 */
public class ProjectPublicNoteBean {

    /**
     * id : 2
     * title : 项目规则须知
     * content : <p style="text-align: center;">项目规则须知</p><p>①用户注册登入，需要上传需求方资料，进行需求方认证，后台审核通过，才有发布项目权限。</p><p>②项目发布成功，支付预支付金额成功，就等待管理员审核。</p><p>③管理员审核通过，则在项目模块进行项目推送。</p><p>④工人在项目列表，可以看到推送的项目。然后，项目详情接单项目。</p><p>⑤需求方在个人中心，我发布的项目里面，可以看到项目进入了待确认工人的状态，点击确认工人，然后支付项目首款。项目就正式进入开发。</p><p>⑥需求方支付的首款，进入了总账号，然后以企业转账个人的方式，转账到工人支付宝账号零钱里。</p><p>⑦当需求方支付项目首款成功后，项目进入了待收取二期款状态。需求方可以在任何时候支付二期款。</p><p>⑧项目支付二期款成功后，项目进入待收取尾款状态。需求方可以在任何时候支付尾款。</p><p>⑨需求方支付尾款，项目进入完成。需求方可以评论工人。工人可以评论需求方。</p><p><br/></p>
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
