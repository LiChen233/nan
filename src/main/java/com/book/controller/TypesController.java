package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.entity.BooksEntity;
import com.book.entity.Result;
import com.book.entity.TypesEntity;
import com.book.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/types")
public class TypesController {
    @Autowired
    private TypesService typesService;

    @GetMapping("/")
    public Result select(Integer page, Integer limit, TypesEntity o){
        if (null==page){
            page=1;
        }
        if (null==limit){
            limit=10;
        }
        IPage<TypesEntity> iPage = typesService.page(new Page<>(page, limit), new QueryWrapper<>(o));
        return Result.success()
                .setData(iPage.getRecords())
                .setCount((int) iPage.getTotal());
    }
}
