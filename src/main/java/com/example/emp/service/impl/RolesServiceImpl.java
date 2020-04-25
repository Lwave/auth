package com.example.emp.service.impl;

import com.example.emp.entity.Roles;
import com.example.emp.entity.SysUsers;
import com.example.emp.mapper.RolesMapper;
import com.example.emp.service.IRolesService;
import com.example.common.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dutao
 * @since 2020-04-19
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public SysUsers getRoleByUserId(Integer id) {
        return rolesMapper.getRoleByUserId(id);
    }
}
