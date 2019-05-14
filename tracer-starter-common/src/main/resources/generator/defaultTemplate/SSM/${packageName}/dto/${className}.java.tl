@DIRECTIVE SAFE_OUTPUT_OPEN;
package com.ow.tracer.${data.packageName}.${data.functionName}.dto;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ow.tracer.common.base.BaseDTO;
import java.io.Serializable;
import java.util.Date;

/**
 * Explain:     [${data.describe!}实体类]
 * Date:        [${date(),"yyyy-MM-dd HH:mm:ss"}]
 * Coder:       [${data.author!}]
 * Version:     [1.0]
 */
 \@SuppressWarnings("serial")
  \@TableName("${data.tableName}")
public class ${data.className} extends BaseDTO<${data.className}> {

@for(c in data.columns){
    @if(isNotEmpty(c.simpleColumnComment)){
    /**${c.simpleColumnComment}*/
    @}
    private ${c.baseJavaType} ${c.property};

@}
@for(c in data.columns){
    public ${c.baseJavaType} get${c.upperProperty}() {
        return this.${c.property};
    }

    public void set${c.upperProperty}(${c.baseJavaType} ${c.property}) {
        this.${c.property} = ${c.property};
    }

@}
    public String toString(){
        final StringBuilder strBuf = new StringBuilder("${data.className}{\n");
@for(c in data.columns){
    @if(cLP.last){
        strBuf.append("${c.property}:").append(this.${c.property}).append("\n");
    @} else {
        strBuf.append("${c.property}:").append(this.${c.property}).append(",\n");
    @}
@}
        strBuf.append("}");
        return strBuf.toString();
    }
}
