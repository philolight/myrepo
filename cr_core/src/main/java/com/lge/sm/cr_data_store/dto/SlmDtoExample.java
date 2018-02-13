package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class SlmDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SlmDtoExample() {
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

        public Criteria andSlmIdIsNull() {
            addCriterion("slm_id is null");
            return (Criteria) this;
        }

        public Criteria andSlmIdIsNotNull() {
            addCriterion("slm_id is not null");
            return (Criteria) this;
        }

        public Criteria andSlmIdEqualTo(String value) {
            addCriterion("slm_id =", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdNotEqualTo(String value) {
            addCriterion("slm_id <>", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdGreaterThan(String value) {
            addCriterion("slm_id >", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdGreaterThanOrEqualTo(String value) {
            addCriterion("slm_id >=", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdLessThan(String value) {
            addCriterion("slm_id <", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdLessThanOrEqualTo(String value) {
            addCriterion("slm_id <=", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdLike(String value) {
            addCriterion("slm_id like", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdNotLike(String value) {
            addCriterion("slm_id not like", value, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdIn(List<String> values) {
            addCriterion("slm_id in", values, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdNotIn(List<String> values) {
            addCriterion("slm_id not in", values, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdBetween(String value1, String value2) {
            addCriterion("slm_id between", value1, value2, "slmId");
            return (Criteria) this;
        }

        public Criteria andSlmIdNotBetween(String value1, String value2) {
            addCriterion("slm_id not between", value1, value2, "slmId");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNull() {
            addCriterion("protocol is null");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNotNull() {
            addCriterion("protocol is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolEqualTo(String value) {
            addCriterion("protocol =", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotEqualTo(String value) {
            addCriterion("protocol <>", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThan(String value) {
            addCriterion("protocol >", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThanOrEqualTo(String value) {
            addCriterion("protocol >=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThan(String value) {
            addCriterion("protocol <", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThanOrEqualTo(String value) {
            addCriterion("protocol <=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLike(String value) {
            addCriterion("protocol like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotLike(String value) {
            addCriterion("protocol not like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolIn(List<String> values) {
            addCriterion("protocol in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotIn(List<String> values) {
            addCriterion("protocol not in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolBetween(String value1, String value2) {
            addCriterion("protocol between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotBetween(String value1, String value2) {
            addCriterion("protocol not between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andUseAuthIsNull() {
            addCriterion("use_auth is null");
            return (Criteria) this;
        }

        public Criteria andUseAuthIsNotNull() {
            addCriterion("use_auth is not null");
            return (Criteria) this;
        }

        public Criteria andUseAuthEqualTo(Integer value) {
            addCriterion("use_auth =", value, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthNotEqualTo(Integer value) {
            addCriterion("use_auth <>", value, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthGreaterThan(Integer value) {
            addCriterion("use_auth >", value, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_auth >=", value, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthLessThan(Integer value) {
            addCriterion("use_auth <", value, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthLessThanOrEqualTo(Integer value) {
            addCriterion("use_auth <=", value, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthIn(List<Integer> values) {
            addCriterion("use_auth in", values, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthNotIn(List<Integer> values) {
            addCriterion("use_auth not in", values, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthBetween(Integer value1, Integer value2) {
            addCriterion("use_auth between", value1, value2, "useAuth");
            return (Criteria) this;
        }

        public Criteria andUseAuthNotBetween(Integer value1, Integer value2) {
            addCriterion("use_auth not between", value1, value2, "useAuth");
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

        public Criteria andUserPwIsNull() {
            addCriterion("user_pw is null");
            return (Criteria) this;
        }

        public Criteria andUserPwIsNotNull() {
            addCriterion("user_pw is not null");
            return (Criteria) this;
        }

        public Criteria andUserPwEqualTo(String value) {
            addCriterion("user_pw =", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwNotEqualTo(String value) {
            addCriterion("user_pw <>", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwGreaterThan(String value) {
            addCriterion("user_pw >", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwGreaterThanOrEqualTo(String value) {
            addCriterion("user_pw >=", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwLessThan(String value) {
            addCriterion("user_pw <", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwLessThanOrEqualTo(String value) {
            addCriterion("user_pw <=", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwLike(String value) {
            addCriterion("user_pw like", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwNotLike(String value) {
            addCriterion("user_pw not like", value, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwIn(List<String> values) {
            addCriterion("user_pw in", values, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwNotIn(List<String> values) {
            addCriterion("user_pw not in", values, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwBetween(String value1, String value2) {
            addCriterion("user_pw between", value1, value2, "userPw");
            return (Criteria) this;
        }

        public Criteria andUserPwNotBetween(String value1, String value2) {
            addCriterion("user_pw not between", value1, value2, "userPw");
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