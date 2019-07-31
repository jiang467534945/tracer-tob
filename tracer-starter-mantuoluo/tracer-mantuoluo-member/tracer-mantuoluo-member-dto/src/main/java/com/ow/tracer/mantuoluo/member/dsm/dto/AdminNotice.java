package com.ow.tracer.mantuoluo.member.dsm.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

import java.util.Date;

/**
 * Explain:     [实体类]
 * Date:        [2019-06-12 17:27:19]
 * Coder:       [becky]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("admin_notice")
public class AdminNotice extends BaseDTO<AdminNotice> {

     private String title;
    private String content;

    private Date createTime;

    private int type;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("AdminNotice{\n");
        strBuf.append("content:").append(this.content).append(",\n");
        strBuf.append("createTime:").append(this.createTime).append(",\n");
        strBuf.append("type:").append(this.type).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
