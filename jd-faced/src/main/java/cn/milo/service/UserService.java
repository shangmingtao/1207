package cn.milo.service;

import cn.milo.dto.User;

/******************************************************
 ****** @ClassName   : UserService.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 07 14:20     
 ****** @version     : v1.0.x                      
 *******************************************************/
public interface UserService {
    public User searchUser(String name);

    public boolean createUser(User user);
}
