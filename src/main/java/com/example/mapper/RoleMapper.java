package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bean.Role;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
public interface RoleMapper extends BaseMapper<Role> {

    Integer selectByFlag(@Param("flag") String flag);
}
