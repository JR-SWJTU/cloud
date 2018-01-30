package com.jr.cloud.entity;

import java.util.Date;

public class DelRecord {
    private Integer delId;

    private Integer userId;

    private String fileName;

    private Date delDate;

    public Integer getDelId() {
        return delId;
    }

    public void setDelId(Integer delId) {
        this.delId = delId;
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

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", delId=").append(delId);
        sb.append(", userId=").append(userId);
        sb.append(", fileName=").append(fileName);
        sb.append(", delDate=").append(delDate);
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
        DelRecord other = (DelRecord) that;
        return (this.getDelId() == null ? other.getDelId() == null : this.getDelId().equals(other.getDelId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getDelDate() == null ? other.getDelDate() == null : this.getDelDate().equals(other.getDelDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDelId() == null) ? 0 : getDelId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getDelDate() == null) ? 0 : getDelDate().hashCode());
        return result;
    }
}