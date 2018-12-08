package cn.milo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/******************************************************
 ****** @ClassName   : TimeHandler2.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 08 18:43     
 ****** @version     : v1.0.x                      
 *******************************************************/

@Aspect
@Component
public class TimeHandler2 {

    @Pointcut(value = "execution(* cn.milo.service.*.*(..))")
    public void point(){}


    @Before(value="point()")
    public void before(){
        System.out.println("transaction begin");
    }

    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("transaction commit");
    }

//    @Around("point()")
//    public boolean around(ProceedingJoinPoint joinPoint) throws Throwable{
//        System.out.println("transaction begin");
//        Boolean flag = (Boolean) joinPoint.proceed();
//        System.out.println("transaction commit");
//        return flag;
//
//    }
}
