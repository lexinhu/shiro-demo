package com.xn2001.service.impl;

import com.xn2001.service.SecurityService;
import com.xn2001.tools.DigestsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 乐心湖
 * @date 2021/2/7 21:54
 **/
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Map<String, String> findPasswordByLoginName(String loginName) {
        return DigestsUtil.encryptPassword("123456");
    }

    @Override
    public List<String> findRoleByLoginName(String loginName) {
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("dev");
        return list;
    }

    @Override
    public List<String> findPermissionByLoginName(String loginName) {
        List<String> list = new ArrayList<>();
        list.add("order:add");
        list.add("order:list");
        list.add("order:del");
        return list;
    }
}
