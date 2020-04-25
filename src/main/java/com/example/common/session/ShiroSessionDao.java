package com.example.common.session;


import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;
import java.util.UUID;

public class ShiroSessionDao extends CachingSessionDAO {


    public ShiroSessionDao(CacheManager cacheManager) {
        // 注入缓存管理器
        setCacheManager(cacheManager);
    }


    @Override
    protected Serializable doCreate(Session session) {
        //生成唯一会话会话id
        Serializable sessionId = UUID.randomUUID().toString().replace("-","").toUpperCase();
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }
}