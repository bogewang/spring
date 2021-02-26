import cn.bogewang.dao.IUserDao;
import cn.bogewang.entity.User;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by bogewang on 2017/3/21.
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private IUserDao userDao;

    @Test
    public void testAdduser() throws Exception{
        User user = new User();
        user.setId("user1");
        user.setName("bogewang");
        boolean result = userDao.add(user);
        System.out.println(result);
        Assert.assertTrue(result);
    }

    @Test
    public void testGet() throws Exception{
        User user1 = userDao.get("user1");
        System.out.println(user1);
    }
}
