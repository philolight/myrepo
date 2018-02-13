package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class CancelHistoryDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CancelHistoryDtoExample() {
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

        public Criteria andDateOfIsNull() {
            addCriterion("date_of is null");
            return (Criteria) this;
        }

        public Criteria andDateOfIsNotNull() {
            addCriterion("date_of is not null");
            return (Criteria) this;
        }

        public Criteria andDateOfEqualTo(String value) {
            addCriterion("date_of =", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfNotEqualTo(String value) {
            addCriterion("date_of <>", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfGreaterThan(String value) {
            addCriterion("date_of >", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfGreaterThanOrEqualTo(String value) {
            addCriterion("date_of >=", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfLessThan(String value) {
            addCriterion("date_of <", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfLessThanOrEqualTo(String value) {
            addCriterion("date_of <=", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfLike(String value) {
            addCriterion("date_of like", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfNotLike(String value) {
            addCriterion("date_of not like", value, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfIn(List<String> values) {
            addCriterion("date_of in", values, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfNotIn(List<String> values) {
            addCriterion("date_of not in", values, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfBetween(String value1, String value2) {
            addCriterion("date_of between", value1, value2, "dateOf");
            return (Criteria) this;
        }

        public Criteria andDateOfNotBetween(String value1, String value2) {
            addCriterion("date_of not between", value1, value2, "dateOf");
            return (Criteria) this;
        }

        public Criteria andLocationIdIsNull() {
            addCriterion("location_id is null");
            return (Criteria) this;
        }

        public Criteria andLocationIdIsNotNull() {
            addCriterion("location_id is not null");
            return (Criteria) this;
        }

        public Criteria andLocationIdEqualTo(String value) {
            addCriterion("location_id =", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotEqualTo(String value) {
            addCriterion("location_id <>", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdGreaterThan(String value) {
            addCriterion("location_id >", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdGreaterThanOrEqualTo(String value) {
            addCriterion("location_id >=", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLessThan(String value) {
            addCriterion("location_id <", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLessThanOrEqualTo(String value) {
            addCriterion("location_id <=", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLike(String value) {
            addCriterion("location_id like", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotLike(String value) {
            addCriterion("location_id not like", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdIn(List<String> values) {
            addCriterion("location_id in", values, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotIn(List<String> values) {
            addCriterion("location_id not in", values, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdBetween(String value1, String value2) {
            addCriterion("location_id between", value1, value2, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotBetween(String value1, String value2) {
            addCriterion("location_id not between", value1, value2, "locationId");
            return (Criteria) this;
        }

        public Criteria andReservationsIsNull() {
            addCriterion("reservations is null");
            return (Criteria) this;
        }

        public Criteria andReservationsIsNotNull() {
            addCriterion("reservations is not null");
            return (Criteria) this;
        }

        public Criteria andReservationsEqualTo(Integer value) {
            addCriterion("reservations =", value, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsNotEqualTo(Integer value) {
            addCriterion("reservations <>", value, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsGreaterThan(Integer value) {
            addCriterion("reservations >", value, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsGreaterThanOrEqualTo(Integer value) {
            addCriterion("reservations >=", value, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsLessThan(Integer value) {
            addCriterion("reservations <", value, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsLessThanOrEqualTo(Integer value) {
            addCriterion("reservations <=", value, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsIn(List<Integer> values) {
            addCriterion("reservations in", values, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsNotIn(List<Integer> values) {
            addCriterion("reservations not in", values, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsBetween(Integer value1, Integer value2) {
            addCriterion("reservations between", value1, value2, "reservations");
            return (Criteria) this;
        }

        public Criteria andReservationsNotBetween(Integer value1, Integer value2) {
            addCriterion("reservations not between", value1, value2, "reservations");
            return (Criteria) this;
        }

        public Criteria andCancelsIsNull() {
            addCriterion("cancels is null");
            return (Criteria) this;
        }

        public Criteria andCancelsIsNotNull() {
            addCriterion("cancels is not null");
            return (Criteria) this;
        }

        public Criteria andCancelsEqualTo(Integer value) {
            addCriterion("cancels =", value, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsNotEqualTo(Integer value) {
            addCriterion("cancels <>", value, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsGreaterThan(Integer value) {
            addCriterion("cancels >", value, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsGreaterThanOrEqualTo(Integer value) {
            addCriterion("cancels >=", value, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsLessThan(Integer value) {
            addCriterion("cancels <", value, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsLessThanOrEqualTo(Integer value) {
            addCriterion("cancels <=", value, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsIn(List<Integer> values) {
            addCriterion("cancels in", values, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsNotIn(List<Integer> values) {
            addCriterion("cancels not in", values, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsBetween(Integer value1, Integer value2) {
            addCriterion("cancels between", value1, value2, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelsNotBetween(Integer value1, Integer value2) {
            addCriterion("cancels not between", value1, value2, "cancels");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesIsNull() {
            addCriterion("cancel_minutes is null");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesIsNotNull() {
            addCriterion("cancel_minutes is not null");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesEqualTo(Integer value) {
            addCriterion("cancel_minutes =", value, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesNotEqualTo(Integer value) {
            addCriterion("cancel_minutes <>", value, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesGreaterThan(Integer value) {
            addCriterion("cancel_minutes >", value, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesGreaterThanOrEqualTo(Integer value) {
            addCriterion("cancel_minutes >=", value, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesLessThan(Integer value) {
            addCriterion("cancel_minutes <", value, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesLessThanOrEqualTo(Integer value) {
            addCriterion("cancel_minutes <=", value, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesIn(List<Integer> values) {
            addCriterion("cancel_minutes in", values, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesNotIn(List<Integer> values) {
            addCriterion("cancel_minutes not in", values, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesBetween(Integer value1, Integer value2) {
            addCriterion("cancel_minutes between", value1, value2, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelMinutesNotBetween(Integer value1, Integer value2) {
            addCriterion("cancel_minutes not between", value1, value2, "cancelMinutes");
            return (Criteria) this;
        }

        public Criteria andCancelRateIsNull() {
            addCriterion("cancel_rate is null");
            return (Criteria) this;
        }

        public Criteria andCancelRateIsNotNull() {
            addCriterion("cancel_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCancelRateEqualTo(Float value) {
            addCriterion("cancel_rate =", value, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateNotEqualTo(Float value) {
            addCriterion("cancel_rate <>", value, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateGreaterThan(Float value) {
            addCriterion("cancel_rate >", value, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateGreaterThanOrEqualTo(Float value) {
            addCriterion("cancel_rate >=", value, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateLessThan(Float value) {
            addCriterion("cancel_rate <", value, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateLessThanOrEqualTo(Float value) {
            addCriterion("cancel_rate <=", value, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateIn(List<Float> values) {
            addCriterion("cancel_rate in", values, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateNotIn(List<Float> values) {
            addCriterion("cancel_rate not in", values, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateBetween(Float value1, Float value2) {
            addCriterion("cancel_rate between", value1, value2, "cancelRate");
            return (Criteria) this;
        }

        public Criteria andCancelRateNotBetween(Float value1, Float value2) {
            addCriterion("cancel_rate not between", value1, value2, "cancelRate");
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

        public Criteria andReusesIsNull() {
            addCriterion("reuses is null");
            return (Criteria) this;
        }

        public Criteria andReusesIsNotNull() {
            addCriterion("reuses is not null");
            return (Criteria) this;
        }

        public Criteria andReusesEqualTo(Integer value) {
            addCriterion("reuses =", value, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesNotEqualTo(Integer value) {
            addCriterion("reuses <>", value, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesGreaterThan(Integer value) {
            addCriterion("reuses >", value, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesGreaterThanOrEqualTo(Integer value) {
            addCriterion("reuses >=", value, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesLessThan(Integer value) {
            addCriterion("reuses <", value, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesLessThanOrEqualTo(Integer value) {
            addCriterion("reuses <=", value, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesIn(List<Integer> values) {
            addCriterion("reuses in", values, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesNotIn(List<Integer> values) {
            addCriterion("reuses not in", values, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesBetween(Integer value1, Integer value2) {
            addCriterion("reuses between", value1, value2, "reuses");
            return (Criteria) this;
        }

        public Criteria andReusesNotBetween(Integer value1, Integer value2) {
            addCriterion("reuses not between", value1, value2, "reuses");
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