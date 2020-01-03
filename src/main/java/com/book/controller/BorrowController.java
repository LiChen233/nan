package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.utils.UuidUtil;
import com.book.entity.BooksEntity;
import com.book.entity.BorrowEntity;
import com.book.entity.Result;
import com.book.entity.UserEntity;
import com.book.service.BooksService;
import com.book.service.BorrowService;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BooksService booksService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Result select(Integer page,Integer limit,BorrowEntity o){
        if (null==page){
            page=1;
        }
        if (null==limit){
            limit=10;
        }
        IPage<BorrowEntity> iPage = borrowService.page(new Page<>(page, limit), new QueryWrapper<>(o));
        for (BorrowEntity borrow : iPage.getRecords()) {
            BooksEntity book = BooksEntity.builder()
                    .id(borrow.getBookId())
                    .build();
            //获取书籍详细信息
            book = booksService.getOne(new QueryWrapper<>(book));
            borrow.setName(book.getName())
                    .setAuthor(book.getAuthor())
                    .setType(book.getType())
                    .setPress(book.getPress())
                    .setPrice(book.getPrice());
            //获取借书人名字
            UserEntity user = UserEntity.builder()
                    .id(borrow.getUserId())
                    .build();
            user = userService.getOne(new QueryWrapper<>(user));
            borrow.setNickName(user.getNickName());
        }
        if (null!=o){
            return Result.success()
                    .setData(iPage.getRecords())
                    .setCount((int)iPage.getTotal());
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
        if (o.getStatus().equals("R")){
            o.setReturnTime(new Date());
            BorrowEntity borrow = BorrowEntity.builder()
                    .id(o.getId())
                    .build();
            borrow = borrowService.getOne(new QueryWrapper<>(borrow));
            booksService.returnBook(borrow.getBookId());
        }
        if (o.getStatus().equals("S")){
            o.setPayTime(new Date());
        }
        boolean b = borrowService.updateById(o);
        if (b){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
