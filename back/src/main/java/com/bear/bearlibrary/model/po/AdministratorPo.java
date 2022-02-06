package com.bear.bearlibrary.model.po;

import java.time.LocalDateTime;

public class AdministratorPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.valid
     *
     * @mbg.generated
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.gmt_create
     *
     * @mbg.generated
     */
    private LocalDateTime gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.gmt_modified
     *
     * @mbg.generated
     */
    private LocalDateTime gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.create_id
     *
     * @mbg.generated
     */
    private Long createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column administrator.modified_id
     *
     * @mbg.generated
     */
    private Long modifiedId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.id
     *
     * @return the value of administrator.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.id
     *
     * @param id the value for administrator.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.name
     *
     * @return the value of administrator.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.name
     *
     * @param name the value for administrator.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.password
     *
     * @return the value of administrator.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.password
     *
     * @param password the value for administrator.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.valid
     *
     * @return the value of administrator.valid
     *
     * @mbg.generated
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.valid
     *
     * @param valid the value for administrator.valid
     *
     * @mbg.generated
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.gmt_create
     *
     * @return the value of administrator.gmt_create
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.gmt_create
     *
     * @param gmtCreate the value for administrator.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.gmt_modified
     *
     * @return the value of administrator.gmt_modified
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.gmt_modified
     *
     * @param gmtModified the value for administrator.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.create_id
     *
     * @return the value of administrator.create_id
     *
     * @mbg.generated
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.create_id
     *
     * @param createId the value for administrator.create_id
     *
     * @mbg.generated
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column administrator.modified_id
     *
     * @return the value of administrator.modified_id
     *
     * @mbg.generated
     */
    public Long getModifiedId() {
        return modifiedId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column administrator.modified_id
     *
     * @param modifiedId the value for administrator.modified_id
     *
     * @mbg.generated
     */
    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }
}