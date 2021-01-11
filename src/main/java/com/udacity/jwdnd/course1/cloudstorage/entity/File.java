package com.udacity.jwdnd.course1.cloudstorage.entity;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
