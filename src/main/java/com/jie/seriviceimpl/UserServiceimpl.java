package com.jie.seriviceimpl;

import com.jie.bean.User;
import com.jie.dao.UserMapper;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper mapper=null;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
    public int insertUser(User user) {
        return mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
    public User getUser(String id) {
        return  mapper.getUser(id);
    }

    @Override
    public User login(User user) {
         User  u=getUserByPhone(user.getUserPhone());
         //比对传入的用户手机号和密码是否和数据的匹配
        if(u==null)
            return null;
         if(u.getUserPhone().equals(user.getUserPhone())&&u.getUserPassword().equals(user.getUserPassword())){
             return u;
         }
        return null;
    }

    @Override
    public User getUserByPhone(String userPhone) {
        return  mapper.getUserByPhone(userPhone);
    }

    @Override
    public int register(User user) {
        //插入之前先进行查询，如果已存在则无法插入，并返回msg
        if(mapper.getUserByPhone(user.getUserPhone())==null){
           if(mapper.insert(user)==1)
           return  getUserId(user.getUserPhone());
           else return 101;//插入失败
        }
        return 100;//100代表已注册
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
    public int getUserId(String userPhone) {
        return mapper.getUserId(userPhone);
    }

}
