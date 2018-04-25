package cn.lxb.blog.other.shiro.realm;

import cn.lxb.blog.entity.Blogger;
import cn.lxb.blog.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * <P>
 *  Description：博主登录认证的自定义realm
 * </P>
 * @author Andy
 * @since 2017-09-13 09:00.
 * @apiNote 知识改变命运，技术改变世界！
 */
public class BloggerAuthorizingRealm extends AuthorizingRealm {

    @Resource
    private BloggerService bloggerService;

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 为当前登录的用户授予角色和权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 验证当前登录的用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            String userName = token.getPrincipal().toString();
            Blogger blogger = bloggerService.getByUserName(userName);
            if (blogger != null) {
                SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // 当前用户信息存到session中
                return new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "54lxb");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
