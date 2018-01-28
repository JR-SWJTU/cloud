package com.jr.cloud.dto;

import com.jr.cloud.util.DateUtile;
import org.apache.hadoop.fs.FileStatus;

/**
 * Created by Administrator on 2018/1/27.
 */
public class FileInfo {

    private String fullPath;

    private String fileName;

    private String createTime;

    private boolean category;

    private long fileSize;

    private String formatSize;

    private String unit; //文件大小的单位，b、kb、mb、gb

    public  FileInfo(FileStatus status){
        this.fullPath = status.getPath().toUri().getPath();
        this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
        this.createTime = DateUtile.longToStringDate(status.getModificationTime());
        this.category = status.isDir();
        this.fileSize = status.getLen();
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean getCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFormatSize() {
        return formatSize;
    }

    public void setFormatSize(String formatSize) {
        this.formatSize = formatSize;
    }
}
