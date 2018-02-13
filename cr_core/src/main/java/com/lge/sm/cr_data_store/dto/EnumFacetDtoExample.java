package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class EnumFacetDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnumFacetDtoExample() {
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

        public Criteria andEnumFacetIdIsNull() {
            addCriterion("enum_facet_id is null");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdIsNotNull() {
            addCriterion("enum_facet_id is not null");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdEqualTo(Long value) {
            addCriterion("enum_facet_id =", value, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdNotEqualTo(Long value) {
            addCriterion("enum_facet_id <>", value, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdGreaterThan(Long value) {
            addCriterion("enum_facet_id >", value, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("enum_facet_id >=", value, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdLessThan(Long value) {
            addCriterion("enum_facet_id <", value, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdLessThanOrEqualTo(Long value) {
            addCriterion("enum_facet_id <=", value, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdIn(List<Long> values) {
            addCriterion("enum_facet_id in", values, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdNotIn(List<Long> values) {
            addCriterion("enum_facet_id not in", values, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdBetween(Long value1, Long value2) {
            addCriterion("enum_facet_id between", value1, value2, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andEnumFacetIdNotBetween(Long value1, Long value2) {
            addCriterion("enum_facet_id not between", value1, value2, "enumFacetId");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("value like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("value not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("value not between", value1, value2, "value");
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