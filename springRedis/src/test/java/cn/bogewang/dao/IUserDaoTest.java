package cn.bogewang.dao;

import cn.bogewang.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;

/**
 * Created by bogewang on 2017/3/22.
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class IUserDaoTest extends AbstractJUnit4SpringContextTests{
    @Autowired
    private IUserDao userDao;

    @Test
    public void add() throws Exception {

    }

    @Test
    public void add1() throws Exception {
        ArrayList<User> listUsers = new ArrayList<User>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++){
            User user = new User();
            user.setId("user"+i);
            user.setName("username"+i);
            listUsers.add(user);
        }
        long middle = System.currentTimeMillis();
        for (User user: listUsers) {
            userDao.add(user);
        }
        long end = System.currentTimeMillis() - middle;
        System.out.println("创建5000个user对象话费时间为:"+ (middle - start));
        System.out.println("将5000个对象放入redis时间为:"+end);
    }

    @Test
    public void delete() throws Exception {
        userDao.delete("user4999");
        System.out.println(userDao.get("user4999"));
    }

    @Test
    public void delete1() throws Exception {
        ArrayList<String> listKeys = new ArrayList<String>();
        for (int i=0;i<5000;i++){
            listKeys.add("user"+i);
        }
        userDao.delete(listKeys);
        for (int i=0;i<5000;i++){
            System.out.println(userDao.get("user" + i));
        }
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setName("bogewang");
        user.setId("user4999");
        boolean result = userDao.update(user);
        System.out.println(result);
        System.out.println(userDao.get("user4999"));
    }

    @Test
    public void get() throws Exception {
        for (int i=0; i<=5000;i++){
            User user = userDao.get("user" + i);
            System.out.println(user);
        }
    }
}