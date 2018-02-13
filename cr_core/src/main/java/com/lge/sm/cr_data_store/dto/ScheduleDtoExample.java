package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleDtoExample() {
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

        public Criteria andScheduleIdIsNull() {
            addCriterion("schedule_id is null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIsNotNull() {
            addCriterion("schedule_id is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdEqualTo(String value) {
            addCriterion("schedule_id =", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotEqualTo(String value) {
            addCriterion("schedule_id <>", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThan(String value) {
            addCriterion("schedule_id >", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThanOrEqualTo(String value) {
            addCriterion("schedule_id >=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThan(String value) {
            addCriterion("schedule_id <", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThanOrEqualTo(String value) {
            addCriterion("schedule_id <=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLike(String value) {
            addCriterion("schedule_id like", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotLike(String value) {
            addCriterion("schedule_id not like", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIn(List<String> values) {
            addCriterion("schedule_id in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotIn(List<String> values) {
            addCriterion("schedule_id not in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdBetween(String value1, String value2) {
            addCriterion("schedule_id between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotBetween(String value1, String value2) {
            addCriterion("schedule_id not between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(String value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(String value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(String value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(String value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(String value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(String value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLike(String value) {
            addCriterion("room_id like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotLike(String value) {
            addCriterion("room_id not like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<String> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<String> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(String value1, String value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(String value1, String value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
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

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andSdateIsNull() {
            addCriterion("sdate is null");
            return (Criteria) this;
        }

        public Criteria andSdateIsNotNull() {
            addCriterion("sdate is not null");
            return (Criteria) this;
        }

        public Criteria andSdateEqualTo(String value) {
            addCriterion("sdate =", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotEqualTo(String value) {
            addCriterion("sdate <>", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateGreaterThan(String value) {
            addCriterion("sdate >", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateGreaterThanOrEqualTo(String value) {
            addCriterion("sdate >=", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateLessThan(String value) {
            addCriterion("sdate <", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateLessThanOrEqualTo(String value) {
            addCriterion("sdate <=", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateLike(String value) {
            addCriterion("sdate like", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotLike(String value) {
            addCriterion("sdate not like", value, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateIn(List<String> values) {
            addCriterion("sdate in", values, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotIn(List<String> values) {
            addCriterion("sdate not in", values, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateBetween(String value1, String value2) {
            addCriterion("sdate between", value1, value2, "sdate");
            return (Criteria) this;
        }

        public Criteria andSdateNotBetween(String value1, String value2) {
            addCriterion("sdate not between", value1, value2, "sdate");
            return (Criteria) this;
        }

        public Criteria andEdateIsNull() {
            addCriterion("edate is null");
            return (Criteria) this;
        }

        public Criteria andEdateIsNotNull() {
            addCriterion("edate is not null");
            return (Criteria) this;
        }

        public Criteria andEdateEqualTo(String value) {
            addCriterion("edate =", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotEqualTo(String value) {
            addCriterion("edate <>", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateGreaterThan(String value) {
            addCriterion("edate >", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateGreaterThanOrEqualTo(String value) {
            addCriterion("edate >=", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateLessThan(String value) {
            addCriterion("edate <", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateLessThanOrEqualTo(String value) {
            addCriterion("edate <=", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateLike(String value) {
            addCriterion("edate like", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotLike(String value) {
            addCriterion("edate not like", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateIn(List<String> values) {
            addCriterion("edate in", values, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotIn(List<String> values) {
            addCriterion("edate not in", values, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateBetween(String value1, String value2) {
            addCriterion("edate between", value1, value2, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotBetween(String value1, String value2) {
            addCriterion("edate not between", value1, value2, "edate");
            return (Criteria) this;
        }

        public Criteria andLocalYearIsNull() {
            addCriterion("local_year is null");
            return (Criteria) this;
        }

        public Criteria andLocalYearIsNotNull() {
            addCriterion("local_year is not null");
            return (Criteria) this;
        }

        public Criteria andLocalYearEqualTo(Integer value) {
            addCriterion("local_year =", value, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearNotEqualTo(Integer value) {
            addCriterion("local_year <>", value, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearGreaterThan(Integer value) {
            addCriterion("local_year >", value, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("local_year >=", value, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearLessThan(Integer value) {
            addCriterion("local_year <", value, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearLessThanOrEqualTo(Integer value) {
            addCriterion("local_year <=", value, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearIn(List<Integer> values) {
            addCriterion("local_year in", values, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearNotIn(List<Integer> values) {
            addCriterion("local_year not in", values, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearBetween(Integer value1, Integer value2) {
            addCriterion("local_year between", value1, value2, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalYearNotBetween(Integer value1, Integer value2) {
            addCriterion("local_year not between", value1, value2, "localYear");
            return (Criteria) this;
        }

        public Criteria andLocalMonthIsNull() {
            addCriterion("local_month is null");
            return (Criteria) this;
        }

        public Criteria andLocalMonthIsNotNull() {
            addCriterion("local_month is not null");
            return (Criteria) this;
        }

        public Criteria andLocalMonthEqualTo(Integer value) {
            addCriterion("local_month =", value, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthNotEqualTo(Integer value) {
            addCriterion("local_month <>", value, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthGreaterThan(Integer value) {
            addCriterion("local_month >", value, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("local_month >=", value, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthLessThan(Integer value) {
            addCriterion("local_month <", value, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthLessThanOrEqualTo(Integer value) {
            addCriterion("local_month <=", value, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthIn(List<Integer> values) {
            addCriterion("local_month in", values, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthNotIn(List<Integer> values) {
            addCriterion("local_month not in", values, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthBetween(Integer value1, Integer value2) {
            addCriterion("local_month between", value1, value2, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("local_month not between", value1, value2, "localMonth");
            return (Criteria) this;
        }

        public Criteria andLocalDayIsNull() {
            addCriterion("local_day is null");
            return (Criteria) this;
        }

        public Criteria andLocalDayIsNotNull() {
            addCriterion("local_day is not null");
            return (Criteria) this;
        }

        public Criteria andLocalDayEqualTo(Integer value) {
            addCriterion("local_day =", value, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayNotEqualTo(Integer value) {
            addCriterion("local_day <>", value, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayGreaterThan(Integer value) {
            addCriterion("local_day >", value, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("local_day >=", value, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayLessThan(Integer value) {
            addCriterion("local_day <", value, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayLessThanOrEqualTo(Integer value) {
            addCriterion("local_day <=", value, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayIn(List<Integer> values) {
            addCriterion("local_day in", values, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayNotIn(List<Integer> values) {
            addCriterion("local_day not in", values, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayBetween(Integer value1, Integer value2) {
            addCriterion("local_day between", value1, value2, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayNotBetween(Integer value1, Integer value2) {
            addCriterion("local_day not between", value1, value2, "localDay");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekIsNull() {
            addCriterion("local_day_of_week is null");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekIsNotNull() {
            addCriterion("local_day_of_week is not null");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekEqualTo(Integer value) {
            addCriterion("local_day_of_week =", value, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekNotEqualTo(Integer value) {
            addCriterion("local_day_of_week <>", value, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekGreaterThan(Integer value) {
            addCriterion("local_day_of_week >", value, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("local_day_of_week >=", value, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekLessThan(Integer value) {
            addCriterion("local_day_of_week <", value, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekLessThanOrEqualTo(Integer value) {
            addCriterion("local_day_of_week <=", value, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekIn(List<Integer> values) {
            addCriterion("local_day_of_week in", values, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekNotIn(List<Integer> values) {
            addCriterion("local_day_of_week not in", values, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekBetween(Integer value1, Integer value2) {
            addCriterion("local_day_of_week between", value1, value2, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalDayOfWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("local_day_of_week not between", value1, value2, "localDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmIsNull() {
            addCriterion("local_shhmm is null");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmIsNotNull() {
            addCriterion("local_shhmm is not null");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmEqualTo(String value) {
            addCriterion("local_shhmm =", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmNotEqualTo(String value) {
            addCriterion("local_shhmm <>", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmGreaterThan(String value) {
            addCriterion("local_shhmm >", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmGreaterThanOrEqualTo(String value) {
            addCriterion("local_shhmm >=", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmLessThan(String value) {
            addCriterion("local_shhmm <", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmLessThanOrEqualTo(String value) {
            addCriterion("local_shhmm <=", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmLike(String value) {
            addCriterion("local_shhmm like", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmNotLike(String value) {
            addCriterion("local_shhmm not like", value, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmIn(List<String> values) {
            addCriterion("local_shhmm in", values, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmNotIn(List<String> values) {
            addCriterion("local_shhmm not in", values, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmBetween(String value1, String value2) {
            addCriterion("local_shhmm between", value1, value2, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalShhmmNotBetween(String value1, String value2) {
            addCriterion("local_shhmm not between", value1, value2, "localShhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmIsNull() {
            addCriterion("local_ehhmm is null");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmIsNotNull() {
            addCriterion("local_ehhmm is not null");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmEqualTo(String value) {
            addCriterion("local_ehhmm =", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmNotEqualTo(String value) {
            addCriterion("local_ehhmm <>", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmGreaterThan(String value) {
            addCriterion("local_ehhmm >", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmGreaterThanOrEqualTo(String value) {
            addCriterion("local_ehhmm >=", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmLessThan(String value) {
            addCriterion("local_ehhmm <", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmLessThanOrEqualTo(String value) {
            addCriterion("local_ehhmm <=", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmLike(String value) {
            addCriterion("local_ehhmm like", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmNotLike(String value) {
            addCriterion("local_ehhmm not like", value, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmIn(List<String> values) {
            addCriterion("local_ehhmm in", values, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmNotIn(List<String> values) {
            addCriterion("local_ehhmm not in", values, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmBetween(String value1, String value2) {
            addCriterion("local_ehhmm between", value1, value2, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalEhhmmNotBetween(String value1, String value2) {
            addCriterion("local_ehhmm not between", value1, value2, "localEhhmm");
            return (Criteria) this;
        }

        public Criteria andLocalDurationIsNull() {
            addCriterion("local_duration is null");
            return (Criteria) this;
        }

        public Criteria andLocalDurationIsNotNull() {
            addCriterion("local_duration is not null");
            return (Criteria) this;
        }

        public Criteria andLocalDurationEqualTo(Integer value) {
            addCriterion("local_duration =", value, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationNotEqualTo(Integer value) {
            addCriterion("local_duration <>", value, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationGreaterThan(Integer value) {
            addCriterion("local_duration >", value, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("local_duration >=", value, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationLessThan(Integer value) {
            addCriterion("local_duration <", value, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationLessThanOrEqualTo(Integer value) {
            addCriterion("local_duration <=", value, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationIn(List<Integer> values) {
            addCriterion("local_duration in", values, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationNotIn(List<Integer> values) {
            addCriterion("local_duration not in", values, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationBetween(Integer value1, Integer value2) {
            addCriterion("local_duration between", value1, value2, "localDuration");
            return (Criteria) this;
        }

        public Criteria andLocalDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("local_duration not between", value1, value2, "localDuration");
            return (Criteria) this;
        }

        public Criteria andSensorCntIsNull() {
            addCriterion("sensor_cnt is null");
            return (Criteria) this;
        }

        public Criteria andSensorCntIsNotNull() {
            addCriterion("sensor_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andSensorCntEqualTo(Integer value) {
            addCriterion("sensor_cnt =", value, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntNotEqualTo(Integer value) {
            addCriterion("sensor_cnt <>", value, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntGreaterThan(Integer value) {
            addCriterion("sensor_cnt >", value, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("sensor_cnt >=", value, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntLessThan(Integer value) {
            addCriterion("sensor_cnt <", value, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntLessThanOrEqualTo(Integer value) {
            addCriterion("sensor_cnt <=", value, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntIn(List<Integer> values) {
            addCriterion("sensor_cnt in", values, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntNotIn(List<Integer> values) {
            addCriterion("sensor_cnt not in", values, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntBetween(Integer value1, Integer value2) {
            addCriterion("sensor_cnt between", value1, value2, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andSensorCntNotBetween(Integer value1, Integer value2) {
            addCriterion("sensor_cnt not between", value1, value2, "sensorCnt");
            return (Criteria) this;
        }

        public Criteria andTotalSensorIsNull() {
            addCriterion("total_sensor is null");
            return (Criteria) this;
        }

        public Criteria andTotalSensorIsNotNull() {
            addCriterion("total_sensor is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSensorEqualTo(Integer value) {
            addCriterion("total_sensor =", value, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorNotEqualTo(Integer value) {
            addCriterion("total_sensor <>", value, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorGreaterThan(Integer value) {
            addCriterion("total_sensor >", value, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_sensor >=", value, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorLessThan(Integer value) {
            addCriterion("total_sensor <", value, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorLessThanOrEqualTo(Integer value) {
            addCriterion("total_sensor <=", value, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorIn(List<Integer> values) {
            addCriterion("total_sensor in", values, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorNotIn(List<Integer> values) {
            addCriterion("total_sensor not in", values, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorBetween(Integer value1, Integer value2) {
            addCriterion("total_sensor between", value1, value2, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalSensorNotBetween(Integer value1, Integer value2) {
            addCriterion("total_sensor not between", value1, value2, "totalSensor");
            return (Criteria) this;
        }

        public Criteria andTotalDetectIsNull() {
            addCriterion("total_detect is null");
            return (Criteria) this;
        }

        public Criteria andTotalDetectIsNotNull() {
            addCriterion("total_detect is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDetectEqualTo(Integer value) {
            addCriterion("total_detect =", value, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectNotEqualTo(Integer value) {
            addCriterion("total_detect <>", value, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectGreaterThan(Integer value) {
            addCriterion("total_detect >", value, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_detect >=", value, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectLessThan(Integer value) {
            addCriterion("total_detect <", value, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectLessThanOrEqualTo(Integer value) {
            addCriterion("total_detect <=", value, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectIn(List<Integer> values) {
            addCriterion("total_detect in", values, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectNotIn(List<Integer> values) {
            addCriterion("total_detect not in", values, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectBetween(Integer value1, Integer value2) {
            addCriterion("total_detect between", value1, value2, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andTotalDetectNotBetween(Integer value1, Integer value2) {
            addCriterion("total_detect not between", value1, value2, "totalDetect");
            return (Criteria) this;
        }

        public Criteria andChkDurationIsNull() {
            addCriterion("chk_duration is null");
            return (Criteria) this;
        }

        public Criteria andChkDurationIsNotNull() {
            addCriterion("chk_duration is not null");
            return (Criteria) this;
        }

        public Criteria andChkDurationEqualTo(Integer value) {
            addCriterion("chk_duration =", value, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationNotEqualTo(Integer value) {
            addCriterion("chk_duration <>", value, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationGreaterThan(Integer value) {
            addCriterion("chk_duration >", value, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("chk_duration >=", value, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationLessThan(Integer value) {
            addCriterion("chk_duration <", value, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationLessThanOrEqualTo(Integer value) {
            addCriterion("chk_duration <=", value, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationIn(List<Integer> values) {
            addCriterion("chk_duration in", values, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationNotIn(List<Integer> values) {
            addCriterion("chk_duration not in", values, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationBetween(Integer value1, Integer value2) {
            addCriterion("chk_duration between", value1, value2, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andChkDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("chk_duration not between", value1, value2, "chkDuration");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Integer value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Integer value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Integer value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Integer value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Integer value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Integer> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Integer> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Integer value1, Integer value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Integer value1, Integer value2) {
            addCriterion("result not between", value1, value2, "result");
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

        public Criteria andErrorCntIsNull() {
            addCriterion("error_cnt is null");
            return (Criteria) this;
        }

        public Criteria andErrorCntIsNotNull() {
            addCriterion("error_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCntEqualTo(Integer value) {
            addCriterion("error_cnt =", value, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntNotEqualTo(Integer value) {
            addCriterion("error_cnt <>", value, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntGreaterThan(Integer value) {
            addCriterion("error_cnt >", value, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("error_cnt >=", value, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntLessThan(Integer value) {
            addCriterion("error_cnt <", value, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntLessThanOrEqualTo(Integer value) {
            addCriterion("error_cnt <=", value, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntIn(List<Integer> values) {
            addCriterion("error_cnt in", values, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntNotIn(List<Integer> values) {
            addCriterion("error_cnt not in", values, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntBetween(Integer value1, Integer value2) {
            addCriterion("error_cnt between", value1, value2, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andErrorCntNotBetween(Integer value1, Integer value2) {
            addCriterion("error_cnt not between", value1, value2, "errorCnt");
            return (Criteria) this;
        }

        public Criteria andResultDateIsNull() {
            addCriterion("result_date is null");
            return (Criteria) this;
        }

        public Criteria andResultDateIsNotNull() {
            addCriterion("result_date is not null");
            return (Criteria) this;
        }

        public Criteria andResultDateEqualTo(String value) {
            addCriterion("result_date =", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateNotEqualTo(String value) {
            addCriterion("result_date <>", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateGreaterThan(String value) {
            addCriterion("result_date >", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateGreaterThanOrEqualTo(String value) {
            addCriterion("result_date >=", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateLessThan(String value) {
            addCriterion("result_date <", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateLessThanOrEqualTo(String value) {
            addCriterion("result_date <=", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateLike(String value) {
            addCriterion("result_date like", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateNotLike(String value) {
            addCriterion("result_date not like", value, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateIn(List<String> values) {
            addCriterion("result_date in", values, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateNotIn(List<String> values) {
            addCriterion("result_date not in", values, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateBetween(String value1, String value2) {
            addCriterion("result_date between", value1, value2, "resultDate");
            return (Criteria) this;
        }

        public Criteria andResultDateNotBetween(String value1, String value2) {
            addCriterion("result_date not between", value1, value2, "resultDate");
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