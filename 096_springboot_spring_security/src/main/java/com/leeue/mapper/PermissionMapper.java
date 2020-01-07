package com.leeue.mapper;

import com.leeue.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限mapper
 *
 * @author liyue
 * @date 2019/12/18 16:57
 */

@Mapper
public interface PermissionMapper {

    /**
     * 查询所有所有权限
     *
     * @return
     */
    @Select(" select * from sys_permission ")
    List<Permission> findAllPermission();

}