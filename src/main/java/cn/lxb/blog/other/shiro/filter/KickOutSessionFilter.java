package cn.lxb.blog.other.shiro.filter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <P>
 *  Description：同一用户后登陆踢出前面的用户
 * </P>
 * @author Andy
 * @since 2017-09-13 09:00.
 * @apiNote 知识改变命运，技术改变世界！
 */
public class KickOutSessionFilter extends AccessControlFilter {

    //踢出后到的地址
    private String kickOutUrl;
    //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private boolean kickOutAfter = false;
    //同一个帐号最大会话数 默认1
    private int maxSession = 1;
    // session管理器
    private SessionManager sessionManager;
    // 缓存管理
    private Cache<String, Deque<Serializable>> cache;

    public String getKickOutUrl() {
        return kickOutUrl;
    }

    public void setKickOutUrl(String kickOutUrl) {
        this.kickOutUrl = kickOutUrl;
    }

    public boolean isKickOutAfter() {
        return kickOutAfter;
    }

    public void setKickOutAfter(boolean kickOutAfter) {
        this.kickOutAfter = kickOutAfter;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Cache<String, Deque<Serializable>> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Deque<Serializable>> cache) {
        this.cache = cache;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("bloggerSession");
    }

    /**
     * 是否允许访问，返回true表示允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return true;
    }

    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        // 同步控制,将用户的队列放到缓存里
        Deque<Serializable> deque = cache.get(username);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put(username, deque);
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickOut") == null) {
            deque.push(sessionId);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickOutSessionId;
            if (kickOutAfter) {
                //如果踢出后者
                kickOutSessionId = deque.removeFirst();
            } else {
                //否则踢出前者
                kickOutSessionId = deque.removeLast();
            }

            Session kickOutSession = sessionManager.getSession(new DefaultSessionKey(kickOutSessionId));
            if (kickOutSession != null) {
                //设置会话的kickOut属性表示踢出了
                kickOutSession.setAttribute("kickOut", true);
            }
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickOut") != null) {
            //会话被踢出了
            subject.logout();
            WebUtils.issueRedirect(servletRequest, servletResponse, kickOutUrl);
            return false;
        }
        return  true;
    }
}
