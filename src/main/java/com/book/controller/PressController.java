package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.PressEntity;
import com.book.entity.Result;
import com.book.service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("press")
public class PressController {
    @Autowired
    private PressService pressService;

    @GetMapping("/")
    public Result select(PressEntity o){
        List<PressEntity> list = pressService.list(new QueryWrapper<>(o));
        if (null!=list){
            return Result.success().setData(list);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/")
    public Result insert(PressEntity o){
        boolean b = pressService.save(o);
        if (b){
            return Result.success().setData(o.getId());
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping("/")
    public Result delete(PressEntity o){
        boolean b = pressService.removeById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/")
    public Result update(PressEntity o){
        boolean b = pressService.updateById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
