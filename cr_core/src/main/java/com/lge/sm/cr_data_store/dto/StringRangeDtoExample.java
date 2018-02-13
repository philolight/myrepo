package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class StringRangeDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StringRangeDtoExample() {
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

        public Criteria andStringRangeIdIsNull() {
            addCriterion("string_range_id is null");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdIsNotNull() {
            addCriterion("string_range_id is not null");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdEqualTo(Long value) {
            addCriterion("string_range_id =", value, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdNotEqualTo(Long value) {
            addCriterion("string_range_id <>", value, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdGreaterThan(Long value) {
            addCriterion("string_range_id >", value, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("string_range_id >=", value, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdLessThan(Long value) {
            addCriterion("string_range_id <", value, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdLessThanOrEqualTo(Long value) {
            addCriterion("string_range_id <=", value, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdIn(List<Long> values) {
            addCriterion("string_range_id in", values, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdNotIn(List<Long> values) {
            addCriterion("string_range_id not in", values, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdBetween(Long value1, Long value2) {
            addCriterion("string_range_id between", value1, value2, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andStringRangeIdNotBetween(Long value1, Long value2) {
            addCriterion("string_range_id not between", value1, value2, "stringRangeId");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(Integer value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(Integer value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(Integer value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(Integer value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(Integer value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<Integer> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<Integer> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(Integer value1, Integer value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("length not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andRegexIsNull() {
            addCriterion("regex is null");
            return (Criteria) this;
        }

        public Criteria andRegexIsNotNull() {
            addCriterion("regex is not null");
            return (Criteria) this;
        }

        public Criteria andRegexEqualTo(String value) {
            addCriterion("regex =", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotEqualTo(String value) {
            addCriterion("regex <>", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexGreaterThan(String value) {
            addCriterion("regex >", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexGreaterThanOrEqualTo(String value) {
            addCriterion("regex >=", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexLessThan(String value) {
            addCriterion("regex <", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexLessThanOrEqualTo(String value) {
            addCriterion("regex <=", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexLike(String value) {
            addCriterion("regex like", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotLike(String value) {
            addCriterion("regex not like", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexIn(List<String> values) {
            addCriterion("regex in", values, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotIn(List<String> values) {
            addCriterion("regex not in", values, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexBetween(String value1, String value2) {
            addCriterion("regex between", value1, value2, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotBetween(String value1, String value2) {
            addCriterion("regex not between", value1, value2, "regex");
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

        public Criteria andScriptNameIsNull() {
            addCriterion("script_name is null");
            return (Criteria) this;
        }

        public Criteria andScriptNameIsNotNull() {
            addCriterion("script_name is not null");
            return (Criteria) this;
        }

        public Criteria andScriptNameEqualTo(String value) {
            addCriterion("script_name =", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameNotEqualTo(String value) {
            addCriterion("script_name <>", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameGreaterThan(String value) {
            addCriterion("script_name >", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameGreaterThanOrEqualTo(String value) {
            addCriterion("script_name >=", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameLessThan(String value) {
            addCriterion("script_name <", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameLessThanOrEqualTo(String value) {
            addCriterion("script_name <=", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameLike(String value) {
            addCriterion("script_name like", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameNotLike(String value) {
            addCriterion("script_name not like", value, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameIn(List<String> values) {
            addCriterion("script_name in", values, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameNotIn(List<String> values) {
            addCriterion("script_name not in", values, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameBetween(String value1, String value2) {
            addCriterion("script_name between", value1, value2, "scriptName");
            return (Criteria) this;
        }

        public Criteria andScriptNameNotBetween(String value1, String value2) {
            addCriterion("script_name not between", value1, value2, "scriptName");
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