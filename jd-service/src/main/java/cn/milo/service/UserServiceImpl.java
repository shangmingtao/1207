package cn.milo.service;


import cn.milo.domain.User;
import cn.milo.mapper.UserMapper;
import cn.milo.util.RedisTemplateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


/******************************************************
 ****** @ClassName   : UserServiceImpl.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 07 14:24     
 ****** @version     : v1.0.x                      
 *******************************************************/
@Service("userFacede")
public class UserServiceImpl implements UserService {

    Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Override
    public cn.milo.dto.User searchUser(String id) {
        log.info("service 被访问了。。。传入参数3：   " + id);
        cn.milo.dto.User user1 = new cn.milo.dto.User();
        if (redisTemplateUtil.get(id) != null){
            log.info("redis mingzhong le 3....");
            user1.setName(redisTemplateUtil.get(id)+"");
            return user1;
        }else {
            log.info("search in db1");
            User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
            user1.setName(user.getName());
            user1.setAge(user.getAge());
        }
        return user1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
    public boolean createUser(cn.milo.dto.User user) {
        User user1 = new User();
        user1.setId(user.getAge());
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        user1.setPassword(user.getName());
        int i = userMapper.insert(user1);
        log.info("插入了 。。。 "  +i+ " 条");
        log.info("set redis ....");
        HashMap<String,String> map = new HashMap<String,String>();
        redisTemplateUtil.set(user.getAge()+"",user.getName());
//        int k = 1/0;
        return  i==1?Boolean.valueOf(true):Boolean.valueOf(false);
    }
}
