package com.yourtion.micro.user.mapper;

import com.yourtion.micro.user.thrift.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select id, username, password, real_name as realName," +
            "mobile, email from pe_user where id=#{id}")
    UserInfo getUserById(@Param("id") int id);

    @Select("Select id, username, password, real_name as realName," +
            "mobile, email from pe_user where username=#{username}")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("Insert into pe_user (username, password, real_name, mobile, email)" +
            "values (#{u.username}, #{u.password}, #{u.realName}, #{u.mobile}, #{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);

    @Select("Select u.id, u.username, u.password, u.real_name as realName, u.mobile, u.email, " +
            "t.intro, t.stars " +
            "from pe_user u left join pe_teacher t on u.id=t.user_id" +
            "where u.id=#{id}")
    UserInfo getTeacherById(@Param("id") int id);
}
