package com.jie.seriviceimpl;

import com.jie.bean.User;
import com.jie.service.UserListService;
import com.jie.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserListServiceimpl implements UserListService {
    @Autowired
    private UserService userService=null;
    Logger log=Logger.getLogger(UserListServiceimpl.class);
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED)
    public int insertUserList(List<User> users) {
        int count=0;
        for(User user:users){
            try {
                //count += userService.insertUser(user);
            }catch(Exception ex){
                log.info(ex);
            }
        }
        return count;
    }
}
