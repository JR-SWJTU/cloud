package com.jr.cloud.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UploadRecord {
    private Integer uploadId;

    private Integer userId;

    private String fileName;

    private String savePath;

    private Long fileSize;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;

    private Integer uploadedSliceNum;

    private Long uploadedSize;

    public Integer getUploadId() {
        return uploadId;
    }

    public void setUploadId(Integer uploadId) {
        this.uploadId = uploadId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath == null ? null : savePath.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getUploadedSliceNum() {
        return uploadedSliceNum;
    }

    public void setUploadedSliceNum(Integer uploadedSliceNum) {
        this.uploadedSliceNum = uploadedSliceNum;
    }

    public Long getUploadedSize() {
        return uploadedSize;
    }

    public void setUploadedSize(Long uploadedSize) {
        this.uploadedSize = uploadedSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uploadId=").append(uploadId);
        sb.append(", userId=").append(userId);
        sb.append(", fileName=").append(fileName);
        sb.append(", savePath=").append(savePath);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", uploadDate=").append(uploadDate);
        sb.append(", uploadedSliceNum=").append(uploadedSliceNum);
        sb.append(", uploadedSize=").append(uploadedSize);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UploadRecord other = (UploadRecord) that;
        return (this.getUploadId() == null ? other.getUploadId() == null : this.getUploadId().equals(other.getUploadId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getSavePath() == null ? other.getSavePath() == null : this.getSavePath().equals(other.getSavePath()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getUploadDate() == null ? other.getUploadDate() == null : this.getUploadDate().equals(other.getUploadDate()))
            && (this.getUploadedSliceNum() == null ? other.getUploadedSliceNum() == null : this.getUploadedSliceNum().equals(other.getUploadedSliceNum()))
            && (this.getUploadedSize() == null ? other.getUploadedSize() == null : this.getUploadedSize().equals(other.getUploadedSize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUploadId() == null) ? 0 : getUploadId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getSavePath() == null) ? 0 : getSavePath().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getUploadDate() == null) ? 0 : getUploadDate().hashCode());
        result = prime * result + ((getUploadedSliceNum() == null) ? 0 : getUploadedSliceNum().hashCode());
        result = prime * result + ((getUploadedSize() == null) ? 0 : getUploadedSize().hashCode());
        return result;
    }
}