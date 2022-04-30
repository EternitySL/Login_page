package com.shuangleng.mapper;

import com.shuangleng.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectAll(@Param("username")String userword,@Param("password")String password);


}
