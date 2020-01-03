package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.utils.UuidUtil;
import com.book.entity.Result;
import com.book.entity.StarEntity;
import com.book.entity.UserEntity;
import com.book.service.StarService;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/star")
public class StarController {
    @Autowired
    private StarService starService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Result select(Integer page,Integer limit,StarEntity o){
        if (null==page){
            page=1;
        }
        if (null==limit){
            limit=10;
        }
        IPage<StarEntity> iPage = starService.page(new Page<>(page, limit), new QueryWrapper<>(o));
        for (StarEntity entity : iPage.getRecords()) {
            UserEntity user = UserEntity.builder()
                    .id(entity.getUserId())
                    .build();
            user = userService.getOne(new QueryWrapper<>(user));
            entity.setNickName(user.getNickName());
        }
        if (null!=o){
            return Result.success().setData(iPage.getRecords()).setCount((int) iPage.getTotal());
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/")
    public Result insert(StarEntity o){
        o.setId(UuidUtil.get32UUID()).setUpTime(new Date());
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
