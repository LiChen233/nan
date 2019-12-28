package com.book.dao;

import com.book.entity.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FilesDao {
    @Select("SELECT * FROM files WHERE uuid = #{uuid};")
    Files getImgByUUID(String uuid);

    @Insert("INSERT INTO files (uuid,suffix,filename) VALUES(#{uuid},#{suffix},#{filename});")
    void insertImage(Files files);

    @Delete("DELETE FROM files WHERE uuid = #{uuid};")
    void deleteFile(String uuid);

    @Select("SELECT url FROM server;")
    String  getserverurl();
}
