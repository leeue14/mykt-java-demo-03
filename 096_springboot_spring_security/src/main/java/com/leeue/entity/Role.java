package com.leeue.entity;

import lombok.Data;

/**
 * 角色信息表
 *
 * @author liyue
 * @date 2019/12/18 16:57
 */
@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
}
