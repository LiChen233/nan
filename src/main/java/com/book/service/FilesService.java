package com.book.service;

import com.book.entity.Files;

import java.util.ArrayList;

public interface FilesService {

    Files getImgByUUID(String uuid);

    void addImg(Files files);

    String getserverurl();

    ArrayList<String> getFileName(String[] ids);
}
