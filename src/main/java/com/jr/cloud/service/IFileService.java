package com.jr.cloud.service;

import com.jr.cloud.dto.FileInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/27.
 */
public interface IFileService {

    /**
     *列出path下的所有文件
     * @param path
     * @return
     */
    public List<FileInfo> listFile(String path) throws Exception;

    /**
     * 在path处创建文件目录
     * @param path
     */
    public void makeDir(String path) throws Exception;

    /**
     * 删除path处的文件夹及文件夹内部的文件
     * @param path
     * @throws Exception
     */
    public void deleteDir(String path) throws Exception;

    /**
     * 重命名文件
     * @param oldName
     * @param newName
     */
    public void renameFile(String oldName, String newName) throws Exception;

    /**
     * 将文件从src位置移动到des位置
     * @param src
     * @param des
     */
    public void moveFile(String src, String des) throws IOException;

    /**
     * 将文件从src位置复制一份到des位置
     * @param src
     * @param des
     */
    public void copyFile(String src, String des) throws IOException, Exception;
}
