package cn.bogewang.dao;

import cn.bogewang.entity.User;

import java.util.List;

/**
 * Created by bogewang on 2017/3/21.
 */
public interface IUserDao {
    /**
     * 新增
     * @param user
     * @return
     */
    boolean add(User user) throws Exception;

    /**
     * 实现批量新增
     * @param list
     * @return
     */
    boolean add(List<User> list);

    /**
     * 删除
     * @param key
     */
    void delete(String key);

    /**
     * 批量删除
     * @param keys
     */
    void  delete(List<String> keys);

    /**
     * 修改
     * @return
     */
    boolean update(User user);

    /**
     * 通过key获取
     * @param keyId
     * @return
     */
    User get(String keyId);
}
