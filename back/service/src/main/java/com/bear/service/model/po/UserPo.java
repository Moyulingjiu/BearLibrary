package com.bear.service.model.po;

import java.time.LocalDateTime;

public class UserPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.open_id
     *
     * @mbg.generated
     */
    private String openId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.invitation_code_id
     *
     * @mbg.generated
     */
    private Long invitationCodeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nickname
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.avatar
     *
     * @mbg.generated
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.birthday
     *
     * @mbg.generated
     */
    private String birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gender
     *
     * @mbg.generated
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.honor_point
     *
     * @mbg.generated
     */
    private Long honorPoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.self_control_point
     *
     * @mbg.generated
     */
    private Long selfControlPoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.contribution_point
     *
     * @mbg.generated
     */
    private Long contributionPoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.walk
     *
     * @mbg.generated
     */
    private Long walk;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.read
     *
     * @mbg.generated
     */
    private Long read;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.sport
     *
     * @mbg.generated
     */
    private Long sport;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.art
     *
     * @mbg.generated
     */
    private Long art;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.practice
     *
     * @mbg.generated
     */
    private Long practice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.valid
     *
     * @mbg.generated
     */
    private Integer valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gmt_create
     *
     * @mbg.generated
     */
    private LocalDateTime gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gmt_modified
     *
     * @mbg.generated
     */
    private LocalDateTime gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_id
     *
     * @mbg.generated
     */
    private Long createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_name
     *
     * @mbg.generated
     */
    private String createName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.modified_id
     *
     * @mbg.generated
     */
    private Long modifiedId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.modified_name
     *
     * @mbg.generated
     */
    private String modifiedName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.open_id
     *
     * @return the value of user.open_id
     *
     * @mbg.generated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.open_id
     *
     * @param openId the value for user.open_id
     *
     * @mbg.generated
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.invitation_code_id
     *
     * @return the value of user.invitation_code_id
     *
     * @mbg.generated
     */
    public Long getInvitationCodeId() {
        return invitationCodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.invitation_code_id
     *
     * @param invitationCodeId the value for user.invitation_code_id
     *
     * @mbg.generated
     */
    public void setInvitationCodeId(Long invitationCodeId) {
        this.invitationCodeId = invitationCodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nickname
     *
     * @return the value of user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nickname
     *
     * @param nickname the value for user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.avatar
     *
     * @return the value of user.avatar
     *
     * @mbg.generated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.avatar
     *
     * @param avatar the value for user.avatar
     *
     * @mbg.generated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.birthday
     *
     * @return the value of user.birthday
     *
     * @mbg.generated
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.birthday
     *
     * @param birthday the value for user.birthday
     *
     * @mbg.generated
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gender
     *
     * @return the value of user.gender
     *
     * @mbg.generated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gender
     *
     * @param gender the value for user.gender
     *
     * @mbg.generated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.phone
     *
     * @return the value of user.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.phone
     *
     * @param phone the value for user.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.honor_point
     *
     * @return the value of user.honor_point
     *
     * @mbg.generated
     */
    public Long getHonorPoint() {
        return honorPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.honor_point
     *
     * @param honorPoint the value for user.honor_point
     *
     * @mbg.generated
     */
    public void setHonorPoint(Long honorPoint) {
        this.honorPoint = honorPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.self_control_point
     *
     * @return the value of user.self_control_point
     *
     * @mbg.generated
     */
    public Long getSelfControlPoint() {
        return selfControlPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.self_control_point
     *
     * @param selfControlPoint the value for user.self_control_point
     *
     * @mbg.generated
     */
    public void setSelfControlPoint(Long selfControlPoint) {
        this.selfControlPoint = selfControlPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.contribution_point
     *
     * @return the value of user.contribution_point
     *
     * @mbg.generated
     */
    public Long getContributionPoint() {
        return contributionPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.contribution_point
     *
     * @param contributionPoint the value for user.contribution_point
     *
     * @mbg.generated
     */
    public void setContributionPoint(Long contributionPoint) {
        this.contributionPoint = contributionPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.walk
     *
     * @return the value of user.walk
     *
     * @mbg.generated
     */
    public Long getWalk() {
        return walk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.walk
     *
     * @param walk the value for user.walk
     *
     * @mbg.generated
     */
    public void setWalk(Long walk) {
        this.walk = walk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.read
     *
     * @return the value of user.read
     *
     * @mbg.generated
     */
    public Long getRead() {
        return read;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.read
     *
     * @param read the value for user.read
     *
     * @mbg.generated
     */
    public void setRead(Long read) {
        this.read = read;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.sport
     *
     * @return the value of user.sport
     *
     * @mbg.generated
     */
    public Long getSport() {
        return sport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.sport
     *
     * @param sport the value for user.sport
     *
     * @mbg.generated
     */
    public void setSport(Long sport) {
        this.sport = sport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.art
     *
     * @return the value of user.art
     *
     * @mbg.generated
     */
    public Long getArt() {
        return art;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.art
     *
     * @param art the value for user.art
     *
     * @mbg.generated
     */
    public void setArt(Long art) {
        this.art = art;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.practice
     *
     * @return the value of user.practice
     *
     * @mbg.generated
     */
    public Long getPractice() {
        return practice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.practice
     *
     * @param practice the value for user.practice
     *
     * @mbg.generated
     */
    public void setPractice(Long practice) {
        this.practice = practice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.valid
     *
     * @return the value of user.valid
     *
     * @mbg.generated
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.valid
     *
     * @param valid the value for user.valid
     *
     * @mbg.generated
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gmt_create
     *
     * @return the value of user.gmt_create
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gmt_create
     *
     * @param gmtCreate the value for user.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gmt_modified
     *
     * @return the value of user.gmt_modified
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gmt_modified
     *
     * @param gmtModified the value for user.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_id
     *
     * @return the value of user.create_id
     *
     * @mbg.generated
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_id
     *
     * @param createId the value for user.create_id
     *
     * @mbg.generated
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_name
     *
     * @return the value of user.create_name
     *
     * @mbg.generated
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_name
     *
     * @param createName the value for user.create_name
     *
     * @mbg.generated
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.modified_id
     *
     * @return the value of user.modified_id
     *
     * @mbg.generated
     */
    public Long getModifiedId() {
        return modifiedId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.modified_id
     *
     * @param modifiedId the value for user.modified_id
     *
     * @mbg.generated
     */
    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.modified_name
     *
     * @return the value of user.modified_name
     *
     * @mbg.generated
     */
    public String getModifiedName() {
        return modifiedName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.modified_name
     *
     * @param modifiedName the value for user.modified_name
     *
     * @mbg.generated
     */
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName == null ? null : modifiedName.trim();
    }
}