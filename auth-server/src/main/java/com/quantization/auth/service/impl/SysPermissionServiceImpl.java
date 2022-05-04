package com.quantization.auth.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quantization.auth.entities.SysPermission;
import com.quantization.auth.mapper.SysPermissionMapper;
import com.quantization.auth.service.SysPermissionService;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if(userId == null) {
            return null;
        }
        List<SysPermission> list = baseMapper.findByUserId(userId);
        //用户无任何权限时，list会有一个 `null` 空的SysPermission 对象，把那个null移除
        list.remove(null);
        return list;
    }
}
