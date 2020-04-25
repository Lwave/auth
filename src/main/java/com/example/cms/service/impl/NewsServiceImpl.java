package com.example.cms.service.impl;

import com.example.cms.entity.News;
import com.example.cms.mapper.NewsMapper;
import com.example.cms.service.INewsService;
import com.example.common.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dutao
 * @since 2020-04-16
 */
@Service
@CacheConfig(cacheNames = "com.example.cms",cacheManager = "springCacheManager")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Override
    @Cacheable(key = "#id")
    public News getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Transactional
    @CachePut(key = "#news.newsId")
    public News save(News news) {
        super.save(news);
        return news;
    }

    @Override
    @CachePut(key = "#news.newsId")
    public News updateById(News news) {
        return super.updateById(news);
    }


    @Override
    @CacheEvict(key = "#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

}
