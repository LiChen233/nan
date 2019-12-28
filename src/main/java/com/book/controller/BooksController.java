package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.common.utils.UuidUtil;
import com.book.entity.BooksEntity;
import com.book.entity.Result;
import com.book.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/")
    public Result select(BooksEntity o){
        o = booksService.getOne(new QueryWrapper<>(o));
        if (null!=o){
            return Result.success().setData(o);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/")
    public Result insert(BooksEntity o){
        o.setId(UuidUtil.get32UUID());
        boolean b = booksService.save(o);
        if (b){
            return Result.success().setData(o.getId());
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping("/")
    public Result delete(BooksEntity o){
        boolean b = booksService.removeById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/")
    public Result update(BooksEntity o){
        boolean b = booksService.updateById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
