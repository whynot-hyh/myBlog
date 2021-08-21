package com.MIBlog.dao;

import com.MIBlog.dataobject.Photo;
import com.MIBlog.exmple.PhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhotoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    long countByExample(PhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    int deleteByExample(PhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    int insert(Photo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    int insertSelective(Photo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    List<Photo> selectByExample(PhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    int updateByExampleSelective(@Param("record") Photo record, @Param("example") PhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table photo
     *
     * @mbg.generated Thu Apr 22 17:33:11 GMT+08:00 2021
     */
    int updateByExample(@Param("record") Photo record, @Param("example") PhotoExample example);
}
