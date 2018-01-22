package com.jr.cloud.entity;

public class ExpandReq {
    private Integer expandReqId;

    private Integer userId;

    private String userName;

    private Integer originalSize;

    private Integer expandSize;

    private Boolean hasDeleted;

    public Integer getExpandReqId() {
        return expandReqId;
    }

    public void setExpandReqId(Integer expandReqId) {
        this.expandReqId = expandReqId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(Integer originalSize) {
        this.originalSize = originalSize;
    }

    public Integer getExpandSize() {
        return expandSize;
    }

    public void setExpandSize(Integer expandSize) {
        this.expandSize = expandSize;
    }

    public Boolean getHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(Boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", expandReqId=").append(expandReqId);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", originalSize=").append(originalSize);
        sb.append(", expandSize=").append(expandSize);
        sb.append(", hasDeleted=").append(hasDeleted);
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
        ExpandReq other = (ExpandReq) that;
        return (this.getExpandReqId() == null ? other.getExpandReqId() == null : this.getExpandReqId().equals(other.getExpandReqId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getOriginalSize() == null ? other.getOriginalSize() == null : this.getOriginalSize().equals(other.getOriginalSize()))
            && (this.getExpandSize() == null ? other.getExpandSize() == null : this.getExpandSize().equals(other.getExpandSize()))
            && (this.getHasDeleted() == null ? other.getHasDeleted() == null : this.getHasDeleted().equals(other.getHasDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExpandReqId() == null) ? 0 : getExpandReqId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getOriginalSize() == null) ? 0 : getOriginalSize().hashCode());
        result = prime * result + ((getExpandSize() == null) ? 0 : getExpandSize().hashCode());
        result = prime * result + ((getHasDeleted() == null) ? 0 : getHasDeleted().hashCode());
        return result;
    }
}