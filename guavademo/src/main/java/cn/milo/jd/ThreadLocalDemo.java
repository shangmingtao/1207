package cn.milo.jd;

import java.util.HashMap;

/******************************************************
 ****** @ClassName   : ThreadLocalDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 15 15:20     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class ThreadLocalDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {
        threadLocal.get();

        HashMap map = null;
    }
}
