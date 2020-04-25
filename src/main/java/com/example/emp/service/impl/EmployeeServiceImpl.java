package com.example.emp.service.impl;

import com.example.common.service.impl.ServiceImpl;
import com.example.emp.entity.Employee;
import com.example.emp.mapper.EmployeeMapper;
import com.example.emp.service.IEmployeeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dutao
 * @since 2020-04-16
 */
@Service
@CacheConfig(cacheNames = "com.example.emp",cacheManager = "springCacheManager")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Override
    @Cacheable(key = "#id")
    public Employee getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#employee.id")
    public Employee save(Employee employee) {
        return  super.save(employee);
    }

    @Override
    @CachePut(key = "#employee.id")
    public Employee updateById(Employee employee) {
        return super.updateById(employee);
    }


    @Override
    @CacheEvict(key = "#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }


}
