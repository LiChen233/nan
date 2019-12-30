package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.utils.UuidUtil;
import com.book.entity.BooksEntity;
import com.book.entity.Result;
import com.book.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/")
    public Result select(Integer page,Integer limit,BooksEntity o){
        if (null==page){
            page=1;
        }
        if (null==limit){
            limit=10;
        }
        IPage<BooksEntity> iPage = booksService.page(new Page<>(page, limit),new QueryWrapper<>(o));
        if (null!=o){
            return Result.success()
                    .setData(iPage.getRecords())
                    .setCount((int) iPage.getTotal());
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/")
    public Result insert(BooksEntity o){
        o.setId(UuidUtil.get32UUID());
        o.setStatus("0");
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
