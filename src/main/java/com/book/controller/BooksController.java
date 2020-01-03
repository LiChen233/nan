package com.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.utils.UuidUtil;
import com.book.entity.BooksEntity;
import com.book.entity.Result;
import com.book.service.BooksService;
import com.book.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private StarService starService;

    @GetMapping("/")
    public Result select(Integer page,Integer limit,BooksEntity o){
        if (null==page){
            page=1;
        }
        if (null==limit){
            limit=10;
        }
        QueryWrapper<BooksEntity> wrapper = new QueryWrapper<>(o);
        String searchName = o.getSearchName();
        String searchAuthor = o.getSearchAuthor();
        String searchPushTime = o.getSearchPushTime();
        String searchPress = o.getSearchPress();
        if (searchName !=null && !searchName.equals("")){
            wrapper.like("name", searchName);
        }
        if (searchAuthor !=null && !searchAuthor.equals("")){
            wrapper.like("author", searchAuthor);
        }
        if (searchPushTime !=null && !searchPushTime.equals("")){
            wrapper.ge("push_time", searchPushTime);
        }
        if (searchPress !=null && !searchPress.equals("")){
            wrapper.eq("press", searchPress);
        }
        IPage<BooksEntity> iPage = booksService.page(new Page<>(page, limit),wrapper);
        for (BooksEntity entity : iPage.getRecords()) {
            //通过书的id拿到该书的评分
            BigDecimal avg = starService.getAvg(entity.getId());
            if (null!=avg)
            entity.setStar(avg.setScale(1, BigDecimal.ROUND_HALF_UP));
        }
        return Result.success()
                .setData(iPage.getRecords())
                .setCount((int) iPage.getTotal());
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
