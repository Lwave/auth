package com.example.cms.service.impl;

import com.example.cms.entity.News;
import com.example.cms.service.INewsService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    private INewsService newsService;


    @Test
    public void test01() {
        News news = newsService.getById(24);
        System.out.println(news);

    }

    @Test
    public void test02() {
        News news = new News();
        news.setTitle("测试新闻");
        newsService.save(news);


    }
}






