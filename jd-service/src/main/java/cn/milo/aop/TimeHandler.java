package cn.milo.aop;

import org.springframework.stereotype.Component;


public class TimeHandler {
    public void printTime() {
        System.out.println("CurrentTime = " + System.currentTimeMillis());
    }
}



