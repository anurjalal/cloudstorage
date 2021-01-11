package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer save(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    Integer delete(Integer noteId);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription}, userid=#{userId} WHERE noteId=#{noteId}")
    Integer update(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> findAllByUserId(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note findById(Integer noteid);
}
