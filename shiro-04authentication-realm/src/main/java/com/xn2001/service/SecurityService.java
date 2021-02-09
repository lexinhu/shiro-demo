package com.xn2001.service;

import java.util.List;
import java.util.Map;

/**
 * @author 乐心湖
 * @date 2021/2/7 21:52
 * 模拟数据库操作接口
 **/
public interface SecurityService {

    /**
     * 查询用户密码
     * @param loginName 用户名
     * @return 密码
     */
    Map<String, String> findPasswordByLoginName(String loginName);

    /**
     * @param loginName 登录名称
     * @return
     * @Description 查找角色按用户登录名
     */
    List<String> findRoleByLoginName(String loginName);

    /**
     * @param loginName 登录名称
     * @return
     * @Description 查找资源按用户登录名
     */
    List<String> findPermissionByLoginName(String loginName);
}
