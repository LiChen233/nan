package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.Result;
import com.book.entity.StarEntity;
import com.book.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/star")
public class StarController {
    @Autowired
    private StarService starService;

    @GetMapping("/")
    public Result select(StarEntity o){
        o = starService.getOne(new QueryWrapper<>(o));
        if (null!=o){
            return Result.success().setData(o);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/")
    public Result insert(StarEntity o){
        boolean b = starService.save(o);
        if (b){
            return Result.success().setData(o.getId());
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping("/")
    public Result delete(StarEntity o){
        boolean b = starService.removeById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/")
    public Result update(StarEntity o){
        boolean b = starService.updateById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
