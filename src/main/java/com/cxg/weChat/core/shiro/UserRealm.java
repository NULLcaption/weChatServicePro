package com.cxg.weChat.core.shiro;

import java.util.HashMap;
import java.util.Map;

import com.cxg.weChat.core.config.ApplicationContextRegister;
import com.cxg.weChat.core.utils.MD5Utils;
import com.cxg.weChat.crm.mapper.AllUserDao;
import com.cxg.weChat.crm.pojo.AllUsersDo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;


/**
* @Description:    shiro用户角色验证
* @Author:         Cheney Master
* @CreateDate:     2018/8/15 14:04
* @Version:        1.0
*/

public class UserRealm extends AuthorizingRealm {

	/**
	 * 授权，即权限验证，
	 * 验证某个已认证的用户是否拥有某个权限；
	 * 即判断用户是否能做事情，
	 * 常见的如：验证某个用户是否拥有某个角色。
	 * 或者细粒度的验证某个用户对某个资源是否具有某个权限。
	 * @param arg0
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//		Long userId = ShiroUtils.getUserId();
//		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
//		Set<String> perms = menuService.listPerms(userId);
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.setStringPermissions(perms);
		return null;
	}

	/**
	 * 身份认证/登录，验证用户是不是拥有相应的身份。
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());

        AllUserDao userMapper = ApplicationContextRegister.getBean(AllUserDao.class);
		// 查询用户信息
        AllUsersDo user = userMapper.list(map).get(0);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassWd())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getUserState() == "N") {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
