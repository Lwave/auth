package com.example.emp.mapper;

import com.example.emp.entity.SysUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dutao
 * @since 2020-04-18
 */
@Repository
public interface SysUsersMapper extends BaseMapper<SysUsers> {


    SysUsers getUserByUserName(String username);
}
