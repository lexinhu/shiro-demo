package com.xn2001.realm;

import com.xn2001.service.SecurityService;
import com.xn2001.service.impl.SecurityServiceImpl;
import com.xn2001.tools.DigestsUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.List;
import java.util.Map;

/**
 * @author 乐心湖
 * @date 2021/2/7 21:51
 **/
public class DefinitionRealm extends AuthorizingRealm {

    public DefinitionRealm() {
        //指定密码匹配方式为sha1
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(DigestsUtil.SHA1);
        //指定密码迭代次数
        matcher.setHashIterations(DigestsUtil.ITERATIONS);
        //使用父类方法使匹配方式生效
        setCredentialsMatcher(matcher);
    }

    /**
     * 鉴权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //拿到用户认证凭证信息
        String username = (String) principals.getPrimaryPrincipal();
        //从数据库中查询对应的角色和资源
        SecurityService securityService = new SecurityServiceImpl();
        List<String> roles = securityService.findRoleByLoginName(username);
        List<String> permissions = securityService.findPermissionByLoginName(username);
        //构建资源校验
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证方法
     *
     * @param token 传递登录 token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 实例化数据源访问接口
        SecurityService securityService = new SecurityServiceImpl();
        Map<String, String> map = securityService.findPasswordByLoginName(username);
        if (map.isEmpty()) {
            throw new UnknownAccountException("账户不存在");
        }
        String password = map.get("password");
        String salt = map.get("salt");

        // 参数1 缓存对象
        // 参数2 明文密码
        // 参数3 字节salt
        // 参数4 当前DefinitionRealm名称
        return new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(salt), getName());
    }
}
