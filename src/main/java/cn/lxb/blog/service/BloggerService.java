package cn.lxb.blog.service;

import cn.lxb.blog.entity.Blogger;

/**
 * <P>
 *  Description：博主Service接口
 * </P>
 * @author Andy
 * @since 2017-09-13 09:00.
 * @apiNote 知识改变命运，技术改变世界！
 */
public interface BloggerService {

    /**
     * 查询博主信息
     *
     * @return 找到的博主信息
     */
    public Blogger find() throws Exception;

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 找到用户信息bean
     */
    public Blogger getByUserName(String userName) throws Exception;

    /**
     * 更新博主信息
     *
     * @param id 博主id
     * @param blogger 博主信息bean
     * @return 影响的行数
     */
    public Integer updateById(Integer id, Blogger blogger) throws Exception;

}
