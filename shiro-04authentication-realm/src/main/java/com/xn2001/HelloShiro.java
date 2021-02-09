package com.xn2001;

import com.xn2001.realm.DefinitionRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author 乐心湖
 * @date 2021/2/7 17:58
 **/
public class HelloShiro {

    public Subject shiroLogin(String username, String password) {

        //构建默认的安全示例并传入ini数据源(用户权限信息)
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置Realm
        securityManager.setRealm(new DefinitionRealm());

        //使用 SecurityUtils 工具生效安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //使用SecurityUtils工具获得主体
        Subject subject = SecurityUtils.getSubject();

        //构建用户，指定用户名密码，进行测试
        UsernamePasswordToken user = new UsernamePasswordToken(username, password);

        //测试登录操作
        subject.login(user);

        // 返回门面
        return subject;
    }

    @Test
    public void testPermissionRealm() {
        Subject subject = shiroLogin("jay", "123456");

        //判断用户是否已经登录
        System.out.println("是否登录成功：" + subject.isAuthenticated());

        //---------检查当前用户的角色信息------------
        System.out.println("是否有管理员角色：" + subject.hasRole("admin"));

        //---------如果当前用户有此角色，无返回值。若没有此权限，则抛 UnauthorizedException------------
        try {
            subject.checkRole("coder");
            System.out.println("有coder角色");
        } catch (Exception e) {
            System.out.println("没有coder角色");
        }

        //---------检查当前用户的权限信息------------
        System.out.println("是否有查看订单列表资源：" + subject.isPermitted("order:list"));

        //---------如果当前用户有此权限，无返回值。若没有此权限，则抛 UnauthorizedException------------
        try {
            subject.checkPermissions("order:add", "order:del");
            System.out.println("有添加和删除订单资源");
        } catch (Exception e) {
            System.out.println("没有有添加和删除订单资源");
        }
    }
}
