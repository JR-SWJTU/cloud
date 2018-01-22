package com.jr.cloud.entity;

import java.util.ArrayList;
import java.util.List;

public class ExpandReqExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpandReqExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andExpandReqIdIsNull() {
            addCriterion("expand_req_id is null");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdIsNotNull() {
            addCriterion("expand_req_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdEqualTo(Integer value) {
            addCriterion("expand_req_id =", value, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdNotEqualTo(Integer value) {
            addCriterion("expand_req_id <>", value, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdGreaterThan(Integer value) {
            addCriterion("expand_req_id >", value, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("expand_req_id >=", value, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdLessThan(Integer value) {
            addCriterion("expand_req_id <", value, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdLessThanOrEqualTo(Integer value) {
            addCriterion("expand_req_id <=", value, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdIn(List<Integer> values) {
            addCriterion("expand_req_id in", values, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdNotIn(List<Integer> values) {
            addCriterion("expand_req_id not in", values, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdBetween(Integer value1, Integer value2) {
            addCriterion("expand_req_id between", value1, value2, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andExpandReqIdNotBetween(Integer value1, Integer value2) {
            addCriterion("expand_req_id not between", value1, value2, "expandReqId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeIsNull() {
            addCriterion("original_size is null");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeIsNotNull() {
            addCriterion("original_size is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeEqualTo(Integer value) {
            addCriterion("original_size =", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotEqualTo(Integer value) {
            addCriterion("original_size <>", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeGreaterThan(Integer value) {
            addCriterion("original_size >", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("original_size >=", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeLessThan(Integer value) {
            addCriterion("original_size <", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeLessThanOrEqualTo(Integer value) {
            addCriterion("original_size <=", value, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeIn(List<Integer> values) {
            addCriterion("original_size in", values, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotIn(List<Integer> values) {
            addCriterion("original_size not in", values, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeBetween(Integer value1, Integer value2) {
            addCriterion("original_size between", value1, value2, "originalSize");
            return (Criteria) this;
        }

        public Criteria andOriginalSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("original_size not between", value1, value2, "originalSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeIsNull() {
            addCriterion("expand_size is null");
            return (Criteria) this;
        }

        public Criteria andExpandSizeIsNotNull() {
            addCriterion("expand_size is not null");
            return (Criteria) this;
        }

        public Criteria andExpandSizeEqualTo(Integer value) {
            addCriterion("expand_size =", value, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeNotEqualTo(Integer value) {
            addCriterion("expand_size <>", value, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeGreaterThan(Integer value) {
            addCriterion("expand_size >", value, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("expand_size >=", value, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeLessThan(Integer value) {
            addCriterion("expand_size <", value, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeLessThanOrEqualTo(Integer value) {
            addCriterion("expand_size <=", value, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeIn(List<Integer> values) {
            addCriterion("expand_size in", values, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeNotIn(List<Integer> values) {
            addCriterion("expand_size not in", values, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeBetween(Integer value1, Integer value2) {
            addCriterion("expand_size between", value1, value2, "expandSize");
            return (Criteria) this;
        }

        public Criteria andExpandSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("expand_size not between", value1, value2, "expandSize");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIsNull() {
            addCriterion("has_deleted is null");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIsNotNull() {
            addCriterion("has_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andHasDeletedEqualTo(Boolean value) {
            addCriterion("has_deleted =", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotEqualTo(Boolean value) {
            addCriterion("has_deleted <>", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedGreaterThan(Boolean value) {
            addCriterion("has_deleted >", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("has_deleted >=", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedLessThan(Boolean value) {
            addCriterion("has_deleted <", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("has_deleted <=", value, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedIn(List<Boolean> values) {
            addCriterion("has_deleted in", values, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotIn(List<Boolean> values) {
            addCriterion("has_deleted not in", values, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("has_deleted between", value1, value2, "hasDeleted");
            return (Criteria) this;
        }

        public Criteria andHasDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("has_deleted not between", value1, value2, "hasDeleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}