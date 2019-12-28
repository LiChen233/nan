package com.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result {
    private int code;
    private String msg;
    private Object obj;
    private List<?> list;
    private String src;
    private Object data;
    private int count;

    public static Result success(){
        Result res = Result.builder()
                .code(0)
                .msg("成功！")
                .build();
        return res;
    }

    public static Result fail(){
        Result res = Result.builder()
                .code(500)
                .msg("失败！")
                .build();
        return res;
    }
}
