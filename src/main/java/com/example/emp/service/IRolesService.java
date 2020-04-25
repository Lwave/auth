package com.example.emp.service;

import com.example.emp.entity.Roles;
import com.example.common.service.IService;
import com.example.emp.entity.SysUsers;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dutao
 * @since 2020-04-19
 */
public interface IRolesService extends IService<Roles> {

    SysUsers getRoleByUserId(Integer id);


}
