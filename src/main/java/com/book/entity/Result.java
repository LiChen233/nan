package com.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object obj;
    private List<?> list;
    private String src;
    private Object data;
    private int count;

    public static Result success(Object data){
        Result res = Result.builder()
                .code(0)
                .data(data)
                .build();
        return res;
    }

    public static Result fail(Object data){
        Result res = Result.builder()
                .code(300)
                .data(data)
                .build();
        return res;
    }
}
