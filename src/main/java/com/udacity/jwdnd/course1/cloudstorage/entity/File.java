package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class File {
    private Integer id;
    private String name;
    private String contentType;
    private String size;
    private Integer userid;
    private byte[] data;

    public File(Integer id, String name, String contentType, String size, Integer userid, byte[] data) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.userid = userid;
        this.data = data;
    }
}
