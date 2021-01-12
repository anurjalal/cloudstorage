package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public Integer createFile(File file) {
        return fileMapper.addFile(file);
    }

    public boolean isFilenameAlreadyTaken(String filename, Integer userId){
        return fileMapper.selectFilenameByUserId(filename, userId) != null;

    }

    public List<File> getAllFiles(Integer userId) {
        return fileMapper.getAllFilesByUserId(userId);
    }

    public File getFile(Integer id, Integer userId) {
        return fileMapper.selectFileByIdAndUserId(id, userId);
    }

    public Integer deleteFile(Integer id) {
        return fileMapper.deleteFile(id);
    }

}
