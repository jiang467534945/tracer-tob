package com.ow.${data.projectName}.${data.packageName}.${data.functionName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ow.tracer.common.base.BaseServiceImpl;
import com.ow.tracer.${data.packageName}.${data.functionName}.dto.${data.className};
import com.ow.tracer.${data.packageName}.${data.functionName}.mapper.${data.className}Mapper;
import com.ow.tracer.${data.packageName}.${data.functionName}.service.I${data.className}Service;
import com.ow.tracer.common.constats.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:     [${data.describe}业务接口实现类]
 * 创建人:     [${data.author}]
 * 创建时间:   [${date(),"yyyy-MM-dd HH:mm:ss"}]
 * 版本:       [v1.0]
 */
\@Service
public class ${data.className}ServiceImpl extends BaseServiceImpl<${data.className}Mapper, ${data.className}> implements I${data.className}Service {


}
