package com.quantization.auth.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quantization.auth.entities.SysPermission;

public interface SysPermissionService extends IService<SysPermission> {

    /**
     * @param userId 用户id
     * @return 用户所拥有的权限
     */
    List<SysPermission> findByUserId(Long userId);

}
