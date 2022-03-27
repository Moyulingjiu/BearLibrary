package com.bear.service.model.po;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvitationCodePoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public InvitationCodePoExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdIsNull() {
            addCriterion("`administrator_id` is null");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdIsNotNull() {
            addCriterion("`administrator_id` is not null");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdEqualTo(Long value) {
            addCriterion("`administrator_id` =", value, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdNotEqualTo(Long value) {
            addCriterion("`administrator_id` <>", value, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdGreaterThan(Long value) {
            addCriterion("`administrator_id` >", value, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`administrator_id` >=", value, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdLessThan(Long value) {
            addCriterion("`administrator_id` <", value, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdLessThanOrEqualTo(Long value) {
            addCriterion("`administrator_id` <=", value, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdIn(List<Long> values) {
            addCriterion("`administrator_id` in", values, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdNotIn(List<Long> values) {
            addCriterion("`administrator_id` not in", values, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdBetween(Long value1, Long value2) {
            addCriterion("`administrator_id` between", value1, value2, "administratorId");
            return (Criteria) this;
        }

        public Criteria andAdministratorIdNotBetween(Long value1, Long value2) {
            addCriterion("`administrator_id` not between", value1, value2, "administratorId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("`user_id` is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("`user_id` is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("`user_id` =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("`user_id` <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("`user_id` >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`user_id` >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("`user_id` <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("`user_id` <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("`user_id` in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("`user_id` not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("`user_id` between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("`user_id` not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("`code` is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("`code` is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("`code` =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("`code` <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("`code` >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("`code` >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("`code` <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("`code` <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("`code` like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("`code` not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("`code` in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("`code` not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("`code` between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("`code` not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andValidTimeIsNull() {
            addCriterion("`valid_time` is null");
            return (Criteria) this;
        }

        public Criteria andValidTimeIsNotNull() {
            addCriterion("`valid_time` is not null");
            return (Criteria) this;
        }

        public Criteria andValidTimeEqualTo(LocalDateTime value) {
            addCriterion("`valid_time` =", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotEqualTo(LocalDateTime value) {
            addCriterion("`valid_time` <>", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeGreaterThan(LocalDateTime value) {
            addCriterion("`valid_time` >", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`valid_time` >=", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLessThan(LocalDateTime value) {
            addCriterion("`valid_time` <", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`valid_time` <=", value, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeIn(List<LocalDateTime> values) {
            addCriterion("`valid_time` in", values, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotIn(List<LocalDateTime> values) {
            addCriterion("`valid_time` not in", values, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`valid_time` between", value1, value2, "validTime");
            return (Criteria) this;
        }

        public Criteria andValidTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`valid_time` not between", value1, value2, "validTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("`gmt_create` is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("`gmt_create` is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(LocalDateTime value) {
            addCriterion("`gmt_create` =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(LocalDateTime value) {
            addCriterion("`gmt_create` <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(LocalDateTime value) {
            addCriterion("`gmt_create` >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`gmt_create` >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(LocalDateTime value) {
            addCriterion("`gmt_create` <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`gmt_create` <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<LocalDateTime> values) {
            addCriterion("`gmt_create` in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<LocalDateTime> values) {
            addCriterion("`gmt_create` not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`gmt_create` between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`gmt_create` not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("`gmt_modified` is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("`gmt_modified` is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(LocalDateTime value) {
            addCriterion("`gmt_modified` =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(LocalDateTime value) {
            addCriterion("`gmt_modified` <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(LocalDateTime value) {
            addCriterion("`gmt_modified` >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`gmt_modified` >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(LocalDateTime value) {
            addCriterion("`gmt_modified` <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`gmt_modified` <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<LocalDateTime> values) {
            addCriterion("`gmt_modified` in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<LocalDateTime> values) {
            addCriterion("`gmt_modified` not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`gmt_modified` between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`gmt_modified` not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNull() {
            addCriterion("`create_id` is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("`create_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(Long value) {
            addCriterion("`create_id` =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(Long value) {
            addCriterion("`create_id` <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(Long value) {
            addCriterion("`create_id` >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`create_id` >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(Long value) {
            addCriterion("`create_id` <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(Long value) {
            addCriterion("`create_id` <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<Long> values) {
            addCriterion("`create_id` in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<Long> values) {
            addCriterion("`create_id` not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(Long value1, Long value2) {
            addCriterion("`create_id` between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(Long value1, Long value2) {
            addCriterion("`create_id` not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNull() {
            addCriterion("`create_name` is null");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNotNull() {
            addCriterion("`create_name` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateNameEqualTo(String value) {
            addCriterion("`create_name` =", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotEqualTo(String value) {
            addCriterion("`create_name` <>", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThan(String value) {
            addCriterion("`create_name` >", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
            addCriterion("`create_name` >=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThan(String value) {
            addCriterion("`create_name` <", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThanOrEqualTo(String value) {
            addCriterion("`create_name` <=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLike(String value) {
            addCriterion("`create_name` like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotLike(String value) {
            addCriterion("`create_name` not like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameIn(List<String> values) {
            addCriterion("`create_name` in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotIn(List<String> values) {
            addCriterion("`create_name` not in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameBetween(String value1, String value2) {
            addCriterion("`create_name` between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotBetween(String value1, String value2) {
            addCriterion("`create_name` not between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andModifiedIdIsNull() {
            addCriterion("`modified_id` is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIdIsNotNull() {
            addCriterion("`modified_id` is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedIdEqualTo(Long value) {
            addCriterion("`modified_id` =", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotEqualTo(Long value) {
            addCriterion("`modified_id` <>", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdGreaterThan(Long value) {
            addCriterion("`modified_id` >", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`modified_id` >=", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdLessThan(Long value) {
            addCriterion("`modified_id` <", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdLessThanOrEqualTo(Long value) {
            addCriterion("`modified_id` <=", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdIn(List<Long> values) {
            addCriterion("`modified_id` in", values, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotIn(List<Long> values) {
            addCriterion("`modified_id` not in", values, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdBetween(Long value1, Long value2) {
            addCriterion("`modified_id` between", value1, value2, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotBetween(Long value1, Long value2) {
            addCriterion("`modified_id` not between", value1, value2, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedNameIsNull() {
            addCriterion("`modified_name` is null");
            return (Criteria) this;
        }

        public Criteria andModifiedNameIsNotNull() {
            addCriterion("`modified_name` is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedNameEqualTo(String value) {
            addCriterion("`modified_name` =", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotEqualTo(String value) {
            addCriterion("`modified_name` <>", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameGreaterThan(String value) {
            addCriterion("`modified_name` >", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameGreaterThanOrEqualTo(String value) {
            addCriterion("`modified_name` >=", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameLessThan(String value) {
            addCriterion("`modified_name` <", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameLessThanOrEqualTo(String value) {
            addCriterion("`modified_name` <=", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameLike(String value) {
            addCriterion("`modified_name` like", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotLike(String value) {
            addCriterion("`modified_name` not like", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameIn(List<String> values) {
            addCriterion("`modified_name` in", values, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotIn(List<String> values) {
            addCriterion("`modified_name` not in", values, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameBetween(String value1, String value2) {
            addCriterion("`modified_name` between", value1, value2, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotBetween(String value1, String value2) {
            addCriterion("`modified_name` not between", value1, value2, "modifiedName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table invitation_code
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table invitation_code
     *
     * @mbg.generated
     */
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