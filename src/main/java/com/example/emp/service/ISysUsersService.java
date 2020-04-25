package com.example.emp.service;

import com.example.emp.entity.SysUsers;
import com.example.common.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dutao
 * @since 2020-04-18
 */
public interface ISysUsersService extends IService<SysUsers> {

    SysUsers getUserByUserName(String username);



}
