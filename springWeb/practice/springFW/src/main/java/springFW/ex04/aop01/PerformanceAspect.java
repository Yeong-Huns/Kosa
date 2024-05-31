package springFW.ex04.aop01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * packageName    : springFW.ex04.aop01
 * fileName       : PerfomanceAspect
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */
public class PerformanceAspect {
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
