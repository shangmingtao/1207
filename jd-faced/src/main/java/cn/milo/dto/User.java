package cn.milo.dto;

import java.io.Serializable;

/******************************************************
 ****** @ClassName   : User.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 07 14:21     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class User implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
