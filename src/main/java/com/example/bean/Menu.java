package com.example.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 */
@Data
@TableName("sys_menu")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Menu {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String path;
    private String icon;
    private String description;

    private Integer pid;
    private String pagePath;
    private Integer sortNum;

    //忽略表格
    @TableField(exist = false)
    private List<Menu> children;
}
