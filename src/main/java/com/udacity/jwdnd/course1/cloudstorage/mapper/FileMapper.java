package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<File> getAllFilesByUserId(Integer userId);

    @Insert("INSERT INTO FILES(fileId, filename, contenttype, filesize, userid, filedata) VALUES(#{id}, #{name}, #{contentType}, #{size}, #{userid},#{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    Integer deleteFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE fileId=#{id} AND userid=#{userId}")
    File selectFileByIdAndUserId(Integer id, Integer userId);

    @Select("SELECT * FROM FILES WHERE userid=#{userId} AND filename=#{filename}")
    String selectFilenameByUserId(String filename, Integer userId);
}