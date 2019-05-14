package com.ow.tracer.admin.account.mapper;

import com.ow.tracer.admin.account.dto.Menu;
import com.ow.tracer.common.base.Mapper;
import com.ow.tracer.common.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther: Easy
 * @Date: 18-9-25 21:47
 * @Description:
 */
public interface MenuMapper  extends Mapper<Menu> {
    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    List<MenuVO> findMenuByRoleName(@Param("role") String role,@Param("systemType") String systemType);

}
