package cn.lxb.blog.dao;

import cn.lxb.blog.entity.Blogger;

/**
 * 博主Dao接口
 * Created by Andy on 2017/3/12.
 */
public interface BloggerDao {

    /**
     * 查询博主信息
     *
     * @return 找到的用户或者null
     */
    public Blogger find() throws Exception;

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 找到的用户或者null
     */
    public Blogger getByUserName(String userName) throws Exception;

    /**
     * 更新博主信息
     *
     * @param blogger
     * @return 影响的行数
     */
    public Integer update(Blogger blogger) throws Exception;
}
