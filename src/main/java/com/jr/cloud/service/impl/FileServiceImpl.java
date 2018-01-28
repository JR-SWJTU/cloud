package com.jr.cloud.service.impl;

import com.jr.cloud.dto.FileInfo;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.util.FileSizeFormatUtil;
import com.jr.cloud.util.FileSystemUtil;
import com.jr.cloud.web.exception.base.CustomException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.jr.cloud.util.FileSystemUtil.getFileSystem;

/**
 * Created by Administrator on 2018/1/27.
 */
@Service
public class FileServiceImpl implements IFileService {

    /**
     * @param path
     * @return
     */
    public List<FileInfo> listFile(String path) throws Exception{
        String fullUri = FileSystemUtil.uri + path;
        FileSystem fs = null;
        try{
            fs = getFileSystem(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        FileStatus[] fileStatuses = fs.listStatus(new Path(fullUri));
        List<FileInfo> fileInfos = new ArrayList<FileInfo>(fileStatuses.length);
        for(int i = 0; i < fileStatuses.length; i++){
            FileInfo fileInfo = new FileInfo(fileStatuses[i]);
            FileSizeFormatUtil.sizeFormat(fileInfo);
            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }

    /**
     * 在path处创建文件目录
     * @param path
     */
    public void makeDir(String path) throws Exception{
        String fullUri = FileSystemUtil.uri + path;
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path1 = new Path(fullUri);
        if( fs.exists(path1)){
            throw new CustomException("文件夹已经存在");
        }else
            fs.mkdirs( path1);
    }

    /**
     * 删除path处的文件夹及文件夹内部的文件
     *
     * @param path
     * @throws Exception
     */
    public void deleteDir(String path) throws Exception {
        String fullUri = FileSystemUtil.uri + path;
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path1 = new Path(fullUri);
        fs.delete(path1,true);
    }

    /**
     * 重命名文件
     *
     * @param oldName
     * @param newName
     */
    public void renameFile(String oldName, String newName) throws Exception{
        String fullUri = FileSystemUtil.uri + oldName;
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path1 = new Path(fullUri);
        fs.rename(path1, new Path(FileSystemUtil.uri + newName));
    }

    /**
     * 将文件从src位置移动到des位置
     *
     * @param src
     * @param des
     */
    public void moveFile(String src, String des) throws IOException {
        String fullUri = FileSystemUtil.uri + src;
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path srcPath = new Path(fullUri);
        Path desPath = new Path(FileSystemUtil.uri + des);
        if( fs.exists( new Path(FileSystemUtil.uri + des + "//" + src.substring(src.lastIndexOf("/")+1)))){
            throw new CustomException("目标文件夹存在同名文件");
        }
        //对于目标目录的同名文件，不覆盖
        FileUtil.copy(fs, srcPath, fs, desPath, true,false, new Configuration());
    }

    /**
     * 将文件从src位置复制一份到des位置
     *
     * @param src
     * @param des
     */
    public void copyFile(String src, String des) throws Exception {
        String fullUri = FileSystemUtil.uri + src;
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path srcPath = new Path(fullUri);
        Path desPath = new Path(FileSystemUtil.uri + des);
        if( fs.exists( new Path(FileSystemUtil.uri + des + "//" + src.substring(src.lastIndexOf("/")+1)))){
            throw new CustomException("目标文件夹存在同名文件");
        }
        //对于目标目录的同名文件，不覆盖
        FileUtil.copy(fs, srcPath, fs, desPath, false,false, new Configuration());
    }


}
