package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class DriverDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverDtoExample() {
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

        public Criteria andDriverIdIsNull() {
            addCriterion("driver_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNotNull() {
            addCriterion("driver_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverIdEqualTo(Long value) {
            addCriterion("driver_id =", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotEqualTo(Long value) {
            addCriterion("driver_id <>", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThan(Long value) {
            addCriterion("driver_id >", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanOrEqualTo(Long value) {
            addCriterion("driver_id >=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThan(Long value) {
            addCriterion("driver_id <", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanOrEqualTo(Long value) {
            addCriterion("driver_id <=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdIn(List<Long> values) {
            addCriterion("driver_id in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotIn(List<Long> values) {
            addCriterion("driver_id not in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdBetween(Long value1, Long value2) {
            addCriterion("driver_id between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotBetween(Long value1, Long value2) {
            addCriterion("driver_id not between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Integer value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Integer value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Integer value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Integer value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Integer value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Integer> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Integer> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Integer value1, Integer value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andPingEnableIsNull() {
            addCriterion("ping_enable is null");
            return (Criteria) this;
        }

        public Criteria andPingEnableIsNotNull() {
            addCriterion("ping_enable is not null");
            return (Criteria) this;
        }

        public Criteria andPingEnableEqualTo(Integer value) {
            addCriterion("ping_enable =", value, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableNotEqualTo(Integer value) {
            addCriterion("ping_enable <>", value, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableGreaterThan(Integer value) {
            addCriterion("ping_enable >", value, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("ping_enable >=", value, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableLessThan(Integer value) {
            addCriterion("ping_enable <", value, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableLessThanOrEqualTo(Integer value) {
            addCriterion("ping_enable <=", value, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableIn(List<Integer> values) {
            addCriterion("ping_enable in", values, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableNotIn(List<Integer> values) {
            addCriterion("ping_enable not in", values, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableBetween(Integer value1, Integer value2) {
            addCriterion("ping_enable between", value1, value2, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andPingEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("ping_enable not between", value1, value2, "pingEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableIsNull() {
            addCriterion("alarm_enable is null");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableIsNotNull() {
            addCriterion("alarm_enable is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableEqualTo(Integer value) {
            addCriterion("alarm_enable =", value, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableNotEqualTo(Integer value) {
            addCriterion("alarm_enable <>", value, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableGreaterThan(Integer value) {
            addCriterion("alarm_enable >", value, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_enable >=", value, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableLessThan(Integer value) {
            addCriterion("alarm_enable <", value, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_enable <=", value, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableIn(List<Integer> values) {
            addCriterion("alarm_enable in", values, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableNotIn(List<Integer> values) {
            addCriterion("alarm_enable not in", values, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableBetween(Integer value1, Integer value2) {
            addCriterion("alarm_enable between", value1, value2, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andAlarmEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_enable not between", value1, value2, "alarmEnable");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyIsNull() {
            addCriterion("ping_frequency is null");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyIsNotNull() {
            addCriterion("ping_frequency is not null");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyEqualTo(Long value) {
            addCriterion("ping_frequency =", value, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyNotEqualTo(Long value) {
            addCriterion("ping_frequency <>", value, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyGreaterThan(Long value) {
            addCriterion("ping_frequency >", value, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyGreaterThanOrEqualTo(Long value) {
            addCriterion("ping_frequency >=", value, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyLessThan(Long value) {
            addCriterion("ping_frequency <", value, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyLessThanOrEqualTo(Long value) {
            addCriterion("ping_frequency <=", value, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyIn(List<Long> values) {
            addCriterion("ping_frequency in", values, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyNotIn(List<Long> values) {
            addCriterion("ping_frequency not in", values, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyBetween(Long value1, Long value2) {
            addCriterion("ping_frequency between", value1, value2, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andPingFrequencyNotBetween(Long value1, Long value2) {
            addCriterion("ping_frequency not between", value1, value2, "pingFrequency");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdIsNull() {
            addCriterion("driver_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdIsNotNull() {
            addCriterion("driver_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdEqualTo(String value) {
            addCriterion("driver_type_id =", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdNotEqualTo(String value) {
            addCriterion("driver_type_id <>", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdGreaterThan(String value) {
            addCriterion("driver_type_id >", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("driver_type_id >=", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdLessThan(String value) {
            addCriterion("driver_type_id <", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdLessThanOrEqualTo(String value) {
            addCriterion("driver_type_id <=", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdLike(String value) {
            addCriterion("driver_type_id like", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdNotLike(String value) {
            addCriterion("driver_type_id not like", value, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdIn(List<String> values) {
            addCriterion("driver_type_id in", values, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdNotIn(List<String> values) {
            addCriterion("driver_type_id not in", values, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdBetween(String value1, String value2) {
            addCriterion("driver_type_id between", value1, value2, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andDriverTypeIdNotBetween(String value1, String value2) {
            addCriterion("driver_type_id not between", value1, value2, "driverTypeId");
            return (Criteria) this;
        }

        public Criteria andPollEnableIsNull() {
            addCriterion("poll_enable is null");
            return (Criteria) this;
        }

        public Criteria andPollEnableIsNotNull() {
            addCriterion("poll_enable is not null");
            return (Criteria) this;
        }

        public Criteria andPollEnableEqualTo(Integer value) {
            addCriterion("poll_enable =", value, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableNotEqualTo(Integer value) {
            addCriterion("poll_enable <>", value, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableGreaterThan(Integer value) {
            addCriterion("poll_enable >", value, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("poll_enable >=", value, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableLessThan(Integer value) {
            addCriterion("poll_enable <", value, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableLessThanOrEqualTo(Integer value) {
            addCriterion("poll_enable <=", value, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableIn(List<Integer> values) {
            addCriterion("poll_enable in", values, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableNotIn(List<Integer> values) {
            addCriterion("poll_enable not in", values, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableBetween(Integer value1, Integer value2) {
            addCriterion("poll_enable between", value1, value2, "pollEnable");
            return (Criteria) this;
        }

        public Criteria andPollEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("poll_enable not between", value1, value2, "pollEnable");
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