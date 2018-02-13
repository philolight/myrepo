package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class StartableHistoryDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StartableHistoryDtoExample() {
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

        public Criteria andStartableIdIsNull() {
            addCriterion("startable_id is null");
            return (Criteria) this;
        }

        public Criteria andStartableIdIsNotNull() {
            addCriterion("startable_id is not null");
            return (Criteria) this;
        }

        public Criteria andStartableIdEqualTo(String value) {
            addCriterion("startable_id =", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdNotEqualTo(String value) {
            addCriterion("startable_id <>", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdGreaterThan(String value) {
            addCriterion("startable_id >", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdGreaterThanOrEqualTo(String value) {
            addCriterion("startable_id >=", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdLessThan(String value) {
            addCriterion("startable_id <", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdLessThanOrEqualTo(String value) {
            addCriterion("startable_id <=", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdLike(String value) {
            addCriterion("startable_id like", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdNotLike(String value) {
            addCriterion("startable_id not like", value, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdIn(List<String> values) {
            addCriterion("startable_id in", values, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdNotIn(List<String> values) {
            addCriterion("startable_id not in", values, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdBetween(String value1, String value2) {
            addCriterion("startable_id between", value1, value2, "startableId");
            return (Criteria) this;
        }

        public Criteria andStartableIdNotBetween(String value1, String value2) {
            addCriterion("startable_id not between", value1, value2, "startableId");
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

        public Criteria andStartSucceedIsNull() {
            addCriterion("start_succeed is null");
            return (Criteria) this;
        }

        public Criteria andStartSucceedIsNotNull() {
            addCriterion("start_succeed is not null");
            return (Criteria) this;
        }

        public Criteria andStartSucceedEqualTo(Integer value) {
            addCriterion("start_succeed =", value, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedNotEqualTo(Integer value) {
            addCriterion("start_succeed <>", value, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedGreaterThan(Integer value) {
            addCriterion("start_succeed >", value, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_succeed >=", value, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedLessThan(Integer value) {
            addCriterion("start_succeed <", value, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedLessThanOrEqualTo(Integer value) {
            addCriterion("start_succeed <=", value, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedIn(List<Integer> values) {
            addCriterion("start_succeed in", values, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedNotIn(List<Integer> values) {
            addCriterion("start_succeed not in", values, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedBetween(Integer value1, Integer value2) {
            addCriterion("start_succeed between", value1, value2, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartSucceedNotBetween(Integer value1, Integer value2) {
            addCriterion("start_succeed not between", value1, value2, "startSucceed");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andLatencyIsNull() {
            addCriterion("latency is null");
            return (Criteria) this;
        }

        public Criteria andLatencyIsNotNull() {
            addCriterion("latency is not null");
            return (Criteria) this;
        }

        public Criteria andLatencyEqualTo(Long value) {
            addCriterion("latency =", value, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyNotEqualTo(Long value) {
            addCriterion("latency <>", value, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyGreaterThan(Long value) {
            addCriterion("latency >", value, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyGreaterThanOrEqualTo(Long value) {
            addCriterion("latency >=", value, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyLessThan(Long value) {
            addCriterion("latency <", value, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyLessThanOrEqualTo(Long value) {
            addCriterion("latency <=", value, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyIn(List<Long> values) {
            addCriterion("latency in", values, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyNotIn(List<Long> values) {
            addCriterion("latency not in", values, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyBetween(Long value1, Long value2) {
            addCriterion("latency between", value1, value2, "latency");
            return (Criteria) this;
        }

        public Criteria andLatencyNotBetween(Long value1, Long value2) {
            addCriterion("latency not between", value1, value2, "latency");
            return (Criteria) this;
        }

        public Criteria andReportIsNull() {
            addCriterion("report is null");
            return (Criteria) this;
        }

        public Criteria andReportIsNotNull() {
            addCriterion("report is not null");
            return (Criteria) this;
        }

        public Criteria andReportEqualTo(String value) {
            addCriterion("report =", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportNotEqualTo(String value) {
            addCriterion("report <>", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportGreaterThan(String value) {
            addCriterion("report >", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportGreaterThanOrEqualTo(String value) {
            addCriterion("report >=", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportLessThan(String value) {
            addCriterion("report <", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportLessThanOrEqualTo(String value) {
            addCriterion("report <=", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportLike(String value) {
            addCriterion("report like", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportNotLike(String value) {
            addCriterion("report not like", value, "report");
            return (Criteria) this;
        }

        public Criteria andReportIn(List<String> values) {
            addCriterion("report in", values, "report");
            return (Criteria) this;
        }

        public Criteria andReportNotIn(List<String> values) {
            addCriterion("report not in", values, "report");
            return (Criteria) this;
        }

        public Criteria andReportBetween(String value1, String value2) {
            addCriterion("report between", value1, value2, "report");
            return (Criteria) this;
        }

        public Criteria andReportNotBetween(String value1, String value2) {
            addCriterion("report not between", value1, value2, "report");
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