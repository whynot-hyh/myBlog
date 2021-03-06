package com.MIBlog.dataobject;

import java.io.Serializable;
import java.util.Date;

public class Menus implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.id
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.parent_id
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.menu_name
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private String menuName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.url
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.icon
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private String icon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.admin
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private Integer admin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.gmt_create
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table menus
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.id
     *
     * @return the value of menus.id
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.id
     *
     * @param id the value for menus.id
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.parent_id
     *
     * @return the value of menus.parent_id
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.parent_id
     *
     * @param parentId the value for menus.parent_id
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.menu_name
     *
     * @return the value of menus.menu_name
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.menu_name
     *
     * @param menuName the value for menus.menu_name
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.url
     *
     * @return the value of menus.url
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.url
     *
     * @param url the value for menus.url
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.icon
     *
     * @return the value of menus.icon
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.icon
     *
     * @param icon the value for menus.icon
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.admin
     *
     * @return the value of menus.admin
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public Integer getAdmin() {
        return admin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.admin
     *
     * @param admin the value for menus.admin
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.gmt_create
     *
     * @return the value of menus.gmt_create
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.gmt_create
     *
     * @param gmtCreate the value for menus.gmt_create
     *
     * @mbg.generated Thu Apr 22 09:27:10 GMT+08:00 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}