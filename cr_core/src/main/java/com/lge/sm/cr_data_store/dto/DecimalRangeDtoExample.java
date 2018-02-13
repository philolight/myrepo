package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class DecimalRangeDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DecimalRangeDtoExample() {
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

        public Criteria andDecimalRangeIdIsNull() {
            addCriterion("decimal_range_id is null");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdIsNotNull() {
            addCriterion("decimal_range_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdEqualTo(Long value) {
            addCriterion("decimal_range_id =", value, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdNotEqualTo(Long value) {
            addCriterion("decimal_range_id <>", value, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdGreaterThan(Long value) {
            addCriterion("decimal_range_id >", value, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("decimal_range_id >=", value, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdLessThan(Long value) {
            addCriterion("decimal_range_id <", value, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdLessThanOrEqualTo(Long value) {
            addCriterion("decimal_range_id <=", value, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdIn(List<Long> values) {
            addCriterion("decimal_range_id in", values, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdNotIn(List<Long> values) {
            addCriterion("decimal_range_id not in", values, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdBetween(Long value1, Long value2) {
            addCriterion("decimal_range_id between", value1, value2, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andDecimalRangeIdNotBetween(Long value1, Long value2) {
            addCriterion("decimal_range_id not between", value1, value2, "decimalRangeId");
            return (Criteria) this;
        }

        public Criteria andValueFromIsNull() {
            addCriterion("value_from is null");
            return (Criteria) this;
        }

        public Criteria andValueFromIsNotNull() {
            addCriterion("value_from is not null");
            return (Criteria) this;
        }

        public Criteria andValueFromEqualTo(Long value) {
            addCriterion("value_from =", value, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromNotEqualTo(Long value) {
            addCriterion("value_from <>", value, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromGreaterThan(Long value) {
            addCriterion("value_from >", value, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromGreaterThanOrEqualTo(Long value) {
            addCriterion("value_from >=", value, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromLessThan(Long value) {
            addCriterion("value_from <", value, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromLessThanOrEqualTo(Long value) {
            addCriterion("value_from <=", value, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromIn(List<Long> values) {
            addCriterion("value_from in", values, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromNotIn(List<Long> values) {
            addCriterion("value_from not in", values, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromBetween(Long value1, Long value2) {
            addCriterion("value_from between", value1, value2, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueFromNotBetween(Long value1, Long value2) {
            addCriterion("value_from not between", value1, value2, "valueFrom");
            return (Criteria) this;
        }

        public Criteria andValueToIsNull() {
            addCriterion("value_to is null");
            return (Criteria) this;
        }

        public Criteria andValueToIsNotNull() {
            addCriterion("value_to is not null");
            return (Criteria) this;
        }

        public Criteria andValueToEqualTo(Long value) {
            addCriterion("value_to =", value, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToNotEqualTo(Long value) {
            addCriterion("value_to <>", value, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToGreaterThan(Long value) {
            addCriterion("value_to >", value, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToGreaterThanOrEqualTo(Long value) {
            addCriterion("value_to >=", value, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToLessThan(Long value) {
            addCriterion("value_to <", value, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToLessThanOrEqualTo(Long value) {
            addCriterion("value_to <=", value, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToIn(List<Long> values) {
            addCriterion("value_to in", values, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToNotIn(List<Long> values) {
            addCriterion("value_to not in", values, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToBetween(Long value1, Long value2) {
            addCriterion("value_to between", value1, value2, "valueTo");
            return (Criteria) this;
        }

        public Criteria andValueToNotBetween(Long value1, Long value2) {
            addCriterion("value_to not between", value1, value2, "valueTo");
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

        public Criteria andFieldSkinIdIsNull() {
            addCriterion("field_skin_id is null");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdIsNotNull() {
            addCriterion("field_skin_id is not null");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdEqualTo(String value) {
            addCriterion("field_skin_id =", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdNotEqualTo(String value) {
            addCriterion("field_skin_id <>", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdGreaterThan(String value) {
            addCriterion("field_skin_id >", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdGreaterThanOrEqualTo(String value) {
            addCriterion("field_skin_id >=", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdLessThan(String value) {
            addCriterion("field_skin_id <", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdLessThanOrEqualTo(String value) {
            addCriterion("field_skin_id <=", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdLike(String value) {
            addCriterion("field_skin_id like", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdNotLike(String value) {
            addCriterion("field_skin_id not like", value, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdIn(List<String> values) {
            addCriterion("field_skin_id in", values, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdNotIn(List<String> values) {
            addCriterion("field_skin_id not in", values, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdBetween(String value1, String value2) {
            addCriterion("field_skin_id between", value1, value2, "fieldSkinId");
            return (Criteria) this;
        }

        public Criteria andFieldSkinIdNotBetween(String value1, String value2) {
            addCriterion("field_skin_id not between", value1, value2, "fieldSkinId");
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