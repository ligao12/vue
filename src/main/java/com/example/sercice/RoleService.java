package com.example.sercice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bean.Role;

import java.util.List;

/**
 *
 */
public interface RoleService extends IService<Role> {

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
