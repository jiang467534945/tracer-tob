package com.ow.tracer.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.*;
import com.ow.tracer.common.constats.CommonConstant;
import com.ow.tracer.common.constats.Constants;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: Easy
 * @Date: 18-9-6 17:04
 * @Description:基础实体类
 */
public class BaseDTO <T extends Model> extends Model<T> {




    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    /**
     * 操作类型，add/update/delete 参考：{@link Constants.Operation}
     */
    @Transient
    @TableField(exist = false)
    private String _operate;

    /**
     * 数据版本号,每发生update则自增,用于实现乐观锁.
     */
    @Version
    private int versionNumber;

    //
    // 下面是标准 WHO 字段
    // ----------------------------------------------------------------------------------------------------
    /**
     * 创建人用户名
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createBy;
    //
    // 下面是标准 WHO 字段
    // ----------------------------------------------------------------------------------------------------
    /**
     * 创建人用户名
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String delFlag;
    /**
     * 创建人名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @TableField(exist = false)

    private String creator;
    /**
     * 创建时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private Date createDate;

    /**
     * 更新人用户名
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updateBy;
    /**
     * 更新人名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @TableField(exist = false)
    private String updater;
    /**
     * 更新时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private Date updateDate;

    /**
     * 其它属性
     */
    @JsonIgnore
    @Transient
    @TableField(exist = false)
    protected Map<String, Object> innerMap = new HashMap<>();

    public BaseDTO(){
        super();
        this.delFlag= CommonConstant.STATUS_NORMAL;
    }

    public String get_operate() {
        return _operate;
    }

    public void set_operate(String _operate) {
        this._operate = _operate;
    }
    @Override

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String toJSONString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
