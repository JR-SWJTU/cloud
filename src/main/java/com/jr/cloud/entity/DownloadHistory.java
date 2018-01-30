package com.jr.cloud.entity;

import java.util.Date;

public class DownloadHistory {
    private Integer downloadId;

    private Integer userId;

    private String fileName;

    private Long fileSize;

    private Date downloadDate;

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", downloadId=").append(downloadId);
        sb.append(", userId=").append(userId);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", downloadDate=").append(downloadDate);
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
        DownloadHistory other = (DownloadHistory) that;
        return (this.getDownloadId() == null ? other.getDownloadId() == null : this.getDownloadId().equals(other.getDownloadId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getDownloadDate() == null ? other.getDownloadDate() == null : this.getDownloadDate().equals(other.getDownloadDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDownloadId() == null) ? 0 : getDownloadId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getDownloadDate() == null) ? 0 : getDownloadDate().hashCode());
        return result;
    }
}