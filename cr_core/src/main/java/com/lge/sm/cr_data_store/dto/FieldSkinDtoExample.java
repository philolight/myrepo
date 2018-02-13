package com.lge.sm.cr_data_store.dto;

import java.util.ArrayList;
import java.util.List;

public class FieldSkinDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FieldSkinDtoExample() {
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

        public Criteria andVisibleIsNull() {
            addCriterion("visible is null");
            return (Criteria) this;
        }

        public Criteria andVisibleIsNotNull() {
            addCriterion("visible is not null");
            return (Criteria) this;
        }

        public Criteria andVisibleEqualTo(Integer value) {
            addCriterion("visible =", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleNotEqualTo(Integer value) {
            addCriterion("visible <>", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleGreaterThan(Integer value) {
            addCriterion("visible >", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleGreaterThanOrEqualTo(Integer value) {
            addCriterion("visible >=", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleLessThan(Integer value) {
            addCriterion("visible <", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleLessThanOrEqualTo(Integer value) {
            addCriterion("visible <=", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleIn(List<Integer> values) {
            addCriterion("visible in", values, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleNotIn(List<Integer> values) {
            addCriterion("visible not in", values, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleBetween(Integer value1, Integer value2) {
            addCriterion("visible between", value1, value2, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleNotBetween(Integer value1, Integer value2) {
            addCriterion("visible not between", value1, value2, "visible");
            return (Criteria) this;
        }

        public Criteria andEditableIsNull() {
            addCriterion("editable is null");
            return (Criteria) this;
        }

        public Criteria andEditableIsNotNull() {
            addCriterion("editable is not null");
            return (Criteria) this;
        }

        public Criteria andEditableEqualTo(Integer value) {
            addCriterion("editable =", value, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableNotEqualTo(Integer value) {
            addCriterion("editable <>", value, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableGreaterThan(Integer value) {
            addCriterion("editable >", value, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableGreaterThanOrEqualTo(Integer value) {
            addCriterion("editable >=", value, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableLessThan(Integer value) {
            addCriterion("editable <", value, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableLessThanOrEqualTo(Integer value) {
            addCriterion("editable <=", value, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableIn(List<Integer> values) {
            addCriterion("editable in", values, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableNotIn(List<Integer> values) {
            addCriterion("editable not in", values, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableBetween(Integer value1, Integer value2) {
            addCriterion("editable between", value1, value2, "editable");
            return (Criteria) this;
        }

        public Criteria andEditableNotBetween(Integer value1, Integer value2) {
            addCriterion("editable not between", value1, value2, "editable");
            return (Criteria) this;
        }

        public Criteria andEncryptionIsNull() {
            addCriterion("encryption is null");
            return (Criteria) this;
        }

        public Criteria andEncryptionIsNotNull() {
            addCriterion("encryption is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptionEqualTo(Integer value) {
            addCriterion("encryption =", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotEqualTo(Integer value) {
            addCriterion("encryption <>", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionGreaterThan(Integer value) {
            addCriterion("encryption >", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("encryption >=", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionLessThan(Integer value) {
            addCriterion("encryption <", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionLessThanOrEqualTo(Integer value) {
            addCriterion("encryption <=", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionIn(List<Integer> values) {
            addCriterion("encryption in", values, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotIn(List<Integer> values) {
            addCriterion("encryption not in", values, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionBetween(Integer value1, Integer value2) {
            addCriterion("encryption between", value1, value2, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotBetween(Integer value1, Integer value2) {
            addCriterion("encryption not between", value1, value2, "encryption");
            return (Criteria) this;
        }

        public Criteria andHideTypingIsNull() {
            addCriterion("hide_typing is null");
            return (Criteria) this;
        }

        public Criteria andHideTypingIsNotNull() {
            addCriterion("hide_typing is not null");
            return (Criteria) this;
        }

        public Criteria andHideTypingEqualTo(Integer value) {
            addCriterion("hide_typing =", value, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingNotEqualTo(Integer value) {
            addCriterion("hide_typing <>", value, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingGreaterThan(Integer value) {
            addCriterion("hide_typing >", value, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingGreaterThanOrEqualTo(Integer value) {
            addCriterion("hide_typing >=", value, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingLessThan(Integer value) {
            addCriterion("hide_typing <", value, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingLessThanOrEqualTo(Integer value) {
            addCriterion("hide_typing <=", value, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingIn(List<Integer> values) {
            addCriterion("hide_typing in", values, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingNotIn(List<Integer> values) {
            addCriterion("hide_typing not in", values, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingBetween(Integer value1, Integer value2) {
            addCriterion("hide_typing between", value1, value2, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andHideTypingNotBetween(Integer value1, Integer value2) {
            addCriterion("hide_typing not between", value1, value2, "hideTyping");
            return (Criteria) this;
        }

        public Criteria andSkinIdIsNull() {
            addCriterion("skin_id is null");
            return (Criteria) this;
        }

        public Criteria andSkinIdIsNotNull() {
            addCriterion("skin_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkinIdEqualTo(String value) {
            addCriterion("skin_id =", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdNotEqualTo(String value) {
            addCriterion("skin_id <>", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdGreaterThan(String value) {
            addCriterion("skin_id >", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdGreaterThanOrEqualTo(String value) {
            addCriterion("skin_id >=", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdLessThan(String value) {
            addCriterion("skin_id <", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdLessThanOrEqualTo(String value) {
            addCriterion("skin_id <=", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdLike(String value) {
            addCriterion("skin_id like", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdNotLike(String value) {
            addCriterion("skin_id not like", value, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdIn(List<String> values) {
            addCriterion("skin_id in", values, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdNotIn(List<String> values) {
            addCriterion("skin_id not in", values, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdBetween(String value1, String value2) {
            addCriterion("skin_id between", value1, value2, "skinId");
            return (Criteria) this;
        }

        public Criteria andSkinIdNotBetween(String value1, String value2) {
            addCriterion("skin_id not between", value1, value2, "skinId");
            return (Criteria) this;
        }

        public Criteria andNillableIsNull() {
            addCriterion("nillable is null");
            return (Criteria) this;
        }

        public Criteria andNillableIsNotNull() {
            addCriterion("nillable is not null");
            return (Criteria) this;
        }

        public Criteria andNillableEqualTo(Integer value) {
            addCriterion("nillable =", value, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableNotEqualTo(Integer value) {
            addCriterion("nillable <>", value, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableGreaterThan(Integer value) {
            addCriterion("nillable >", value, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableGreaterThanOrEqualTo(Integer value) {
            addCriterion("nillable >=", value, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableLessThan(Integer value) {
            addCriterion("nillable <", value, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableLessThanOrEqualTo(Integer value) {
            addCriterion("nillable <=", value, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableIn(List<Integer> values) {
            addCriterion("nillable in", values, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableNotIn(List<Integer> values) {
            addCriterion("nillable not in", values, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableBetween(Integer value1, Integer value2) {
            addCriterion("nillable between", value1, value2, "nillable");
            return (Criteria) this;
        }

        public Criteria andNillableNotBetween(Integer value1, Integer value2) {
            addCriterion("nillable not between", value1, value2, "nillable");
            return (Criteria) this;
        }

        public Criteria andAutoFillIsNull() {
            addCriterion("auto_fill is null");
            return (Criteria) this;
        }

        public Criteria andAutoFillIsNotNull() {
            addCriterion("auto_fill is not null");
            return (Criteria) this;
        }

        public Criteria andAutoFillEqualTo(Integer value) {
            addCriterion("auto_fill =", value, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillNotEqualTo(Integer value) {
            addCriterion("auto_fill <>", value, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillGreaterThan(Integer value) {
            addCriterion("auto_fill >", value, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_fill >=", value, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillLessThan(Integer value) {
            addCriterion("auto_fill <", value, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillLessThanOrEqualTo(Integer value) {
            addCriterion("auto_fill <=", value, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillIn(List<Integer> values) {
            addCriterion("auto_fill in", values, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillNotIn(List<Integer> values) {
            addCriterion("auto_fill not in", values, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillBetween(Integer value1, Integer value2) {
            addCriterion("auto_fill between", value1, value2, "autoFill");
            return (Criteria) this;
        }

        public Criteria andAutoFillNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_fill not between", value1, value2, "autoFill");
            return (Criteria) this;
        }

        public Criteria andIsPkIsNull() {
            addCriterion("is_pk is null");
            return (Criteria) this;
        }

        public Criteria andIsPkIsNotNull() {
            addCriterion("is_pk is not null");
            return (Criteria) this;
        }

        public Criteria andIsPkEqualTo(Integer value) {
            addCriterion("is_pk =", value, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkNotEqualTo(Integer value) {
            addCriterion("is_pk <>", value, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkGreaterThan(Integer value) {
            addCriterion("is_pk >", value, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_pk >=", value, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkLessThan(Integer value) {
            addCriterion("is_pk <", value, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkLessThanOrEqualTo(Integer value) {
            addCriterion("is_pk <=", value, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkIn(List<Integer> values) {
            addCriterion("is_pk in", values, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkNotIn(List<Integer> values) {
            addCriterion("is_pk not in", values, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkBetween(Integer value1, Integer value2) {
            addCriterion("is_pk between", value1, value2, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsPkNotBetween(Integer value1, Integer value2) {
            addCriterion("is_pk not between", value1, value2, "isPk");
            return (Criteria) this;
        }

        public Criteria andIsFkIsNull() {
            addCriterion("is_fk is null");
            return (Criteria) this;
        }

        public Criteria andIsFkIsNotNull() {
            addCriterion("is_fk is not null");
            return (Criteria) this;
        }

        public Criteria andIsFkEqualTo(Integer value) {
            addCriterion("is_fk =", value, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkNotEqualTo(Integer value) {
            addCriterion("is_fk <>", value, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkGreaterThan(Integer value) {
            addCriterion("is_fk >", value, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_fk >=", value, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkLessThan(Integer value) {
            addCriterion("is_fk <", value, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkLessThanOrEqualTo(Integer value) {
            addCriterion("is_fk <=", value, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkIn(List<Integer> values) {
            addCriterion("is_fk in", values, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkNotIn(List<Integer> values) {
            addCriterion("is_fk not in", values, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkBetween(Integer value1, Integer value2) {
            addCriterion("is_fk between", value1, value2, "isFk");
            return (Criteria) this;
        }

        public Criteria andIsFkNotBetween(Integer value1, Integer value2) {
            addCriterion("is_fk not between", value1, value2, "isFk");
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