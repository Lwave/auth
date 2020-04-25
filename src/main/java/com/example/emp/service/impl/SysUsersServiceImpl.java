package com.example.emp.service.impl;

import com.example.emp.entity.SysUsers;
import com.example.emp.mapper.SysUsersMapper;
import com.example.emp.service.ISysUsersService;
import com.example.common.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dutao
 * @since 2020-04-18
 */
@Service
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements ISysUsersService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    public SysUsers getUserByUserName(String username) {
        return sysUsersMapper.getUserByUserName(username);
    }


}
