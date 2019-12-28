package com.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Files {
    private String uuid;
    private String suffix;
    private String filename;
    private String serverurl;
}
