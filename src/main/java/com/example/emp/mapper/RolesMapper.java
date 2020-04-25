package com.example.emp.mapper;

import com.example.emp.entity.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emp.entity.SysUsers;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dutao
 * @since 2020-04-19
 */
public interface RolesMapper extends BaseMapper<Roles> {

    SysUsers getRoleByUserId(Integer id);

}
