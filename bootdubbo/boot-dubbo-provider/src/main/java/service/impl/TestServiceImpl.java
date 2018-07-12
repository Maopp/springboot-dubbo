package service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import domain.User;
import service.TestService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(version = "mpp")
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date()) + str;
    }

    @Override
    public User findUser() {
        User user = new User();
        user.setId(1001);
        user.setAge(19);
        user.setGender(1);
        user.setUsername("zhangsan");
        user.setPassword("123qwe123");
        return user;
    }
}
