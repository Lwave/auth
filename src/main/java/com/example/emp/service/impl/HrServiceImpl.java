package com.example.emp.service.impl;

import com.example.emp.entity.Employee;
import com.example.emp.entity.Hr;
import com.example.emp.mapper.HrMapper;
import com.example.emp.service.IHrService;
import com.example.common.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dutao
 * @since 2020-04-17
 */
@Service
@CacheConfig(cacheNames = "com.example.hr",cacheManager = "springCacheManager")
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService {


    @Override
    @Cacheable
    public  List<Hr> list() {
        return super.list();
    }
}
