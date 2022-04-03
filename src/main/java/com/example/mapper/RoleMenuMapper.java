package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bean.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> selectByRoleId(@Param("roleId") Integer roleId);
}
