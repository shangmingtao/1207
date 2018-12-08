package cn.milo.controller;

import cn.milo.dto.User;
import cn.milo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

/******************************************************
 ****** @ClassName   : cn.milo.controller.UserController.java
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 07 22:23     
 ****** @version     : v1.0.x                      
 *******************************************************/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @RequestMapping("/findByName/{username}")
    @ResponseBody
    public String findUserByName(@PathVariable("username") String name){
        User user = userService.searchUser(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",user.getName());
        jsonObject.put("age",user.getAge());
        return jsonObject.toString();
    }

    @RequestMapping("/create/{username}/{age}")
    @ResponseBody
    public String findUserByName(@PathVariable("username") String name ,@PathVariable("age") Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        boolean flag = userService.createUser(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resulet",flag);
        return jsonObject.toString();
    }

}
