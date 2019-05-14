package com.ow.tracer.admin.account.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;
import java.io.Serializable;
import java.util.Date;

/**
 * Explain:     [实体类]
 * Date:        [2018-12-14 02:14:09]
 * Coder:       [江雪立]
 * Version:     [1.0]
 */
 @SuppressWarnings("serial")
  @TableName("admin_system")
public class  AdminSystem extends BaseDTO<AdminSystem> {

     private String name;

    /**系统路径*/
    private String path;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString(){
        final StringBuilder strBuf = new StringBuilder("AdminSystem{\n");
        strBuf.append("name:").append(this.name).append(",\n");
        strBuf.append("path:").append(this.path).append("\n");
        strBuf.append("}");
        return strBuf.toString();
    }
}
