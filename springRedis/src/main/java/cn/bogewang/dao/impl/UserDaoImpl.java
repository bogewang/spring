package cn.bogewang.dao.impl;

import cn.bogewang.dao.AbstractBaseRedisDao;
import cn.bogewang.dao.IUserDao;
import cn.bogewang.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogewang on 2017/3/21.
 */
public class UserDaoImpl extends AbstractBaseRedisDao<String,User> implements IUserDao {
    @Override
    public boolean add(final User user) throws Exception{
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = getRedisSerializer();
                byte[] key = redisSerializer.serialize(user.getId());
                byte[] name = redisSerializer.serialize(user.getName());
                return redisConnection.setNX(key,name);
            }
        });
        return result;
    }

    @Override
    public boolean add(final List<User> list) {
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = getRedisSerializer();
                for( User user : list ){
                    byte[] key = redisSerializer.serialize(user.getId());
                    byte[] name = redisSerializer.serialize(user.getName());
                    redisConnection.setNX(key, name);
                }
                return true;
            }
        },false,true);
        return result;
    }

    public void delete(String key) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(key);
        delete(list);
    }

    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public boolean update(final User user) {
        String key = user.getId();
        if( get(key) == null ){
            throw new NullPointerException("数据行不存在,key=" + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = getRedisSerializer();
                byte[] key = redisSerializer.serialize(user.getId());
                byte[] name = redisSerializer.serialize(user.getName());
                redisConnection.set(key, name); //该处和新增有区别, set setNX
                return true;
            }
        });
        return result;
    }

    public User get(final String keyId) {
        User result = redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = getRedisSerializer();
                byte[] key = redisSerializer.serialize(keyId);
                byte[] value = redisConnection.get(key);
                if( value == null ){
                    return null;
                }
                String name = redisSerializer.deserialize(value);
                return new User(keyId,name,null);
            }
        });
        return result;
    }
}
