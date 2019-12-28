package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.common.utils.UuidUtil;
import com.book.entity.Result;
import com.book.entity.UserEntity;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Result select(UserEntity o){
        o = userService.getOne(new QueryWrapper<>(o));
        if (null!=o){
            return Result.success().setData(o);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/")
    public Result insert(UserEntity o){
        o.setId(UuidUtil.get32UUID());
        boolean b = userService.save(o);
        if (b){
            return Result.success().setData(o.getId());
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping("/")
    public Result delete(UserEntity o){
        boolean b = userService.removeById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/")
    public Result update(UserEntity o){
        boolean b = userService.updateById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
