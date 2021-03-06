package com.MIBlog.dataobject;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.pid
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private Integer pid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.article_id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private Integer articleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.user_id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.receiver
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private String receiver;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.description
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gmt_create
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table comment
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.id
     *
     * @return the value of comment.id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.id
     *
     * @param id the value for comment.id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.pid
     *
     * @return the value of comment.pid
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.pid
     *
     * @param pid the value for comment.pid
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.article_id
     *
     * @return the value of comment.article_id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.article_id
     *
     * @param articleId the value for comment.article_id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.user_id
     *
     * @return the value of comment.user_id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.user_id
     *
     * @param userId the value for comment.user_id
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.receiver
     *
     * @return the value of comment.receiver
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.receiver
     *
     * @param receiver the value for comment.receiver
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.description
     *
     * @return the value of comment.description
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.description
     *
     * @param description the value for comment.description
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.gmt_create
     *
     * @return the value of comment.gmt_create
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.gmt_create
     *
     * @param gmtCreate the value for comment.gmt_create
     *
     * @mbg.generated Sun Apr 25 17:26:45 GMT+08:00 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}