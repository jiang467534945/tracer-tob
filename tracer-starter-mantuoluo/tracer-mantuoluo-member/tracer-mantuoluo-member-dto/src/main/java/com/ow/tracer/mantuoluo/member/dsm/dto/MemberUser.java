package com.ow.tracer.mantuoluo.member.dsm.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;

/**
 * Explain:     [实体类]
 * Date:        [2019-05-11 15:36:35]
 * Coder:       [becky]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("member_user")
public class MemberUser extends BaseDTO<MemberUser> {

    private String nickName;

    private String avatarUrl;

    private String openId;

    private int gender;

    private int userType;

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("MemberUser{\n");
        strBuf.append("nickName:").append(this.nickName).append(",\n");
        strBuf.append("avatarUrl:").append(this.avatarUrl).append(",\n");
        strBuf.append("openId:").append(this.openId).append(",\n");
        strBuf.append("gender:").append(this.gender).append(",\n");
        strBuf.append("userType:").append(this.userType).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
