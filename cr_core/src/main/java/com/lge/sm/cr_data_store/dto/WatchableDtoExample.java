package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class WatchableDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WatchableDtoExample() {
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

        public Criteria andWatchableIdIsNull() {
            addCriterion("watchable_id is null");
            return (Criteria) this;
        }

        public Criteria andWatchableIdIsNotNull() {
            addCriterion("watchable_id is not null");
            return (Criteria) this;
        }

        public Criteria andWatchableIdEqualTo(String value) {
            addCriterion("watchable_id =", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdNotEqualTo(String value) {
            addCriterion("watchable_id <>", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdGreaterThan(String value) {
            addCriterion("watchable_id >", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdGreaterThanOrEqualTo(String value) {
            addCriterion("watchable_id >=", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdLessThan(String value) {
            addCriterion("watchable_id <", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdLessThanOrEqualTo(String value) {
            addCriterion("watchable_id <=", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdLike(String value) {
            addCriterion("watchable_id like", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdNotLike(String value) {
            addCriterion("watchable_id not like", value, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdIn(List<String> values) {
            addCriterion("watchable_id in", values, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdNotIn(List<String> values) {
            addCriterion("watchable_id not in", values, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdBetween(String value1, String value2) {
            addCriterion("watchable_id between", value1, value2, "watchableId");
            return (Criteria) this;
        }

        public Criteria andWatchableIdNotBetween(String value1, String value2) {
            addCriterion("watchable_id not between", value1, value2, "watchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdIsNull() {
            addCriterion("upper_watchable_id is null");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdIsNotNull() {
            addCriterion("upper_watchable_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdEqualTo(String value) {
            addCriterion("upper_watchable_id =", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdNotEqualTo(String value) {
            addCriterion("upper_watchable_id <>", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdGreaterThan(String value) {
            addCriterion("upper_watchable_id >", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdGreaterThanOrEqualTo(String value) {
            addCriterion("upper_watchable_id >=", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdLessThan(String value) {
            addCriterion("upper_watchable_id <", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdLessThanOrEqualTo(String value) {
            addCriterion("upper_watchable_id <=", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdLike(String value) {
            addCriterion("upper_watchable_id like", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdNotLike(String value) {
            addCriterion("upper_watchable_id not like", value, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdIn(List<String> values) {
            addCriterion("upper_watchable_id in", values, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdNotIn(List<String> values) {
            addCriterion("upper_watchable_id not in", values, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdBetween(String value1, String value2) {
            addCriterion("upper_watchable_id between", value1, value2, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andUpperWatchableIdNotBetween(String value1, String value2) {
            addCriterion("upper_watchable_id not between", value1, value2, "upperWatchableId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdIsNull() {
            addCriterion("time_zone_id is null");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdIsNotNull() {
            addCriterion("time_zone_id is not null");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdEqualTo(String value) {
            addCriterion("time_zone_id =", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdNotEqualTo(String value) {
            addCriterion("time_zone_id <>", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdGreaterThan(String value) {
            addCriterion("time_zone_id >", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdGreaterThanOrEqualTo(String value) {
            addCriterion("time_zone_id >=", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdLessThan(String value) {
            addCriterion("time_zone_id <", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdLessThanOrEqualTo(String value) {
            addCriterion("time_zone_id <=", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdLike(String value) {
            addCriterion("time_zone_id like", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdNotLike(String value) {
            addCriterion("time_zone_id not like", value, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdIn(List<String> values) {
            addCriterion("time_zone_id in", values, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdNotIn(List<String> values) {
            addCriterion("time_zone_id not in", values, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdBetween(String value1, String value2) {
            addCriterion("time_zone_id between", value1, value2, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andTimeZoneIdNotBetween(String value1, String value2) {
            addCriterion("time_zone_id not between", value1, value2, "timeZoneId");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("modified_time is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(String value) {
            addCriterion("modified_time =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(String value) {
            addCriterion("modified_time <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(String value) {
            addCriterion("modified_time >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(String value) {
            addCriterion("modified_time >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(String value) {
            addCriterion("modified_time <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(String value) {
            addCriterion("modified_time <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLike(String value) {
            addCriterion("modified_time like", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotLike(String value) {
            addCriterion("modified_time not like", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<String> values) {
            addCriterion("modified_time in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<String> values) {
            addCriterion("modified_time not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(String value1, String value2) {
            addCriterion("modified_time between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(String value1, String value2) {
            addCriterion("modified_time not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCdateIsNull() {
            addCriterion("cdate is null");
            return (Criteria) this;
        }

        public Criteria andCdateIsNotNull() {
            addCriterion("cdate is not null");
            return (Criteria) this;
        }

        public Criteria andCdateEqualTo(String value) {
            addCriterion("cdate =", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotEqualTo(String value) {
            addCriterion("cdate <>", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateGreaterThan(String value) {
            addCriterion("cdate >", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateGreaterThanOrEqualTo(String value) {
            addCriterion("cdate >=", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLessThan(String value) {
            addCriterion("cdate <", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLessThanOrEqualTo(String value) {
            addCriterion("cdate <=", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateLike(String value) {
            addCriterion("cdate like", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotLike(String value) {
            addCriterion("cdate not like", value, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateIn(List<String> values) {
            addCriterion("cdate in", values, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotIn(List<String> values) {
            addCriterion("cdate not in", values, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateBetween(String value1, String value2) {
            addCriterion("cdate between", value1, value2, "cdate");
            return (Criteria) this;
        }

        public Criteria andCdateNotBetween(String value1, String value2) {
            addCriterion("cdate not between", value1, value2, "cdate");
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