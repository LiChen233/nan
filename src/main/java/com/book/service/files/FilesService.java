package com.book.service.files;

import com.book.entity.files.Files;

import java.util.ArrayList;

public interface FilesService {

    Files getImgByUUID(String uuid);

    void addImg(Files files);

    String getserverurl();

    ArrayList<String> getFileName(String[] ids);
}
