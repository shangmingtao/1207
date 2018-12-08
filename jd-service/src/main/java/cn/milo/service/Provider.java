package cn.milo.service;

import cn.milo.util.RedisTemplateUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}