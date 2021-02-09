package com.xn2001;

import com.xn2001.realm.DefinitionRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author 乐心湖
 * @date 2021/2/7 17:58
 **/
public class HelloShiro {
    @Test
    public void shiroLogin() {

        //构建默认的安全示例并传入ini数据源(用户权限信息)
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置Realm
        securityManager.setRealm(new DefinitionRealm());

        //使用 SecurityUtils 工具生效安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //使用SecurityUtils工具获得主体
        Subject subject = SecurityUtils.getSubject();

        //构建用户，指定用户名密码，进行测试
        UsernamePasswordToken user = new UsernamePasswordToken("jay", "123456");

        //测试登录操作
        subject.login(user);

        System.out.println("是否登录成功：" + subject.isAuthenticated());
    }
}
