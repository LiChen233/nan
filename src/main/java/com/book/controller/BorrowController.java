package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.common.utils.UuidUtil;
import com.book.entity.BooksEntity;
import com.book.entity.BorrowEntity;
import com.book.entity.Result;
import com.book.service.BooksService;
import com.book.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BooksService booksService;

    @GetMapping("/")
    public Result select(BorrowEntity o){
        o = borrowService.getOne(new QueryWrapper<>(o));
        if (null!=o){
            return Result.success().setData(o);
        }else {
            return Result.fail();
        }
    }

    /**
     * 借书方法
     * 借书的同时，书总量-1
     */
    @PostMapping("/")
    public Result insert(BorrowEntity o){
        o.setId(UuidUtil.get32UUID()).setBorrowTime(new Date()).setStatus("B");
        boolean b = borrowService.save(o);
        if (b){
            booksService.borrowBook(o.getBookId());
            return Result.success().setData(o.getId());
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping("/")
    public Result delete(BorrowEntity o){
        boolean b = borrowService.removeById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PutMapping("/")
    public Result update(BorrowEntity o){
        boolean b = borrowService.updateById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
