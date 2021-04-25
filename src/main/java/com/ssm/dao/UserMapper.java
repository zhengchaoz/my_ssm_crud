package com.ssm.dao;

import com.ssm.bean.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * @user 郑超
 * @date 2021/4/25
 */
public interface UserMapper {

    @Select("select * from user where mobile_no = #{mobileNo} and password = #{password};")
    @Results(id = "u", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "mobile_no", property = "mobileNo", javaType = long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "password", property = "password"),
            @Result(column = "login_token", property = "loginToken")
    })
    User getUser(@Param("mobileNo") long mobileNo, @Param("password") String password);

    @Select("select * from user where login_token = #{loginToken};")
    @ResultMap("u")
    User getUserByLoginToken(@Param("loginToken") String loginToken);

    @Update("update user set login_token = #{loginToken} where mobile_no = #{mobileNo};")
    int updateUserById(User user);

}
