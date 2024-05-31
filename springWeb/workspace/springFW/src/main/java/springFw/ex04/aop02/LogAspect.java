package springFw.ex04.aop02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * packageName    : springFW.ex04.aop02
 * fileName       : LogAspect
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */
@Component
@Aspect
public class LogAspect {
    //핵심코드 (target) 메서드
    @Pointcut(value = "execution(* springFW.ex04..HelloService.sayHello(..))")
    private void helloPointcut(){}

    @Pointcut(value = "execution(* springFW.ex04..HelloService.sayGoodbye(..))")
    private void goodbyePointcut(){}

    //공통 코드(Aspect)
    @Before("helloPointcut()")
    public void beforeLog(JoinPoint joinPoint){
        System.out.println(">>> Log[before]: " + new Date());
    }

    @AfterReturning("goodbyePointcut()")
    public void afterLog(JoinPoint joinPoint){
        System.out.println(">>> Log[afterReturning]: " + new Date());
    }

    @Around(value = "helloPointcut() || goodbyePointcut()")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        System.out.println("[Log]Before : " + methodName  + " time check start");

        long startTime = System.nanoTime();


        try{
            result = joinPoint.proceed();
        }catch (Exception e){
            System.out.println("[Log]Exception : " + methodName);
        }finally {
            System.out.println("[Log]finally : " + methodName);
        }
        long endTime = System.nanoTime();
        System.out.println("[Log]After : " + methodName  + " time check end");
        System.out.println("[Log]" + methodName + " : " + (endTime - startTime) + "ns");
        return result;
    }


}
