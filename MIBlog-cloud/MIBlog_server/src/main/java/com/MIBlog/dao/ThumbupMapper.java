package com.MIBlog.dao;

import com.MIBlog.dataobject.Thumbup;
import com.MIBlog.exmple.ThumbupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThumbupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    long countByExample(ThumbupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int deleteByExample(ThumbupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int insert(Thumbup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int insertSelective(Thumbup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    List<Thumbup> selectByExample(ThumbupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    Thumbup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int updateByExampleSelective(@Param("record") Thumbup record, @Param("example") ThumbupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int updateByExample(@Param("record") Thumbup record, @Param("example") ThumbupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int updateByPrimaryKeySelective(Thumbup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumbup
     *
     * @mbg.generated Mon Apr 26 16:05:06 GMT+08:00 2021
     */
    int updateByPrimaryKey(Thumbup record);
}