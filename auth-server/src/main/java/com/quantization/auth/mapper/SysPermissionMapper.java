package com.quantization.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quantization.auth.entities.SysPermission;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID获取拥有资源权限
     * @param userId 用户id
     * @return
     */
    List<SysPermission> findByUserId(@Param("userId") Long userId);
}
