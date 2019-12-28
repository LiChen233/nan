package com.book.service.impl;

import com.book.dao.FilesDao;
import com.book.entity.Files;
import com.book.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    FilesDao filesDao;

    @Override
    public Files getImgByUUID(String uuid) {
        return filesDao.getImgByUUID(uuid);
    }

    @Override
    public void addImg(Files files) {
        filesDao.insertImage(files);
    }

    @Override
    public String getserverurl() {
        return filesDao.getserverurl();
    }

    @Override
    public ArrayList<String> getFileName(String[] ids) {
        ArrayList<String> names = new ArrayList<>();
        for (String id : ids) {
            if(!id.equals("")){
                Files files = filesDao.getImgByUUID(id);
                String fileName = files.getFilename() + files.getSuffix();
                names.add(fileName);
            }
        }
        return names;
    }
}
