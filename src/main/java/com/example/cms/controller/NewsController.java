package com.example.cms.controller;


import com.example.cms.service.INewsService;
import com.example.common.model.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dutao
 * @since 2020-04-16
 */
@Controller
@RequestMapping("/cms/news")
public class NewsController {

    @Autowired
    private INewsService iNewsService;

    @GetMapping("/{id}")
    @ResponseBody
    @RequiresPermissions("cms:news:view")
    public R get(@PathVariable Integer id){
        return  R.ok("请求成功",iNewsService.getById(id));
    }


}
