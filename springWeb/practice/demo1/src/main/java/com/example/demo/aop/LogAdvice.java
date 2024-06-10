package com.example.demo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.demo.aop
 * fileName       : LogAdvice
 * author         : Yeong-Huns
 * date           : 2024-06-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-10        Yeong-Huns       최초 생성
 */
@Aspect
@Component
@Log4j2
public class LogAdvice {
    @Before("execution(* com.example.demo..HelloController.temp(..))")
    public void beforeLog(JoinPoint joinPoint){
        log.info("😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆");
        log.info("before Advice 수행중");
        log.info("😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆😆");
        Signature signature = joinPoint.getSignature();
    }
}
