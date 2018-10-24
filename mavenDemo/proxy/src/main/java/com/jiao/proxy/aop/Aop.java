package com.jiao.proxy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * Created by jiao on 2018/10/19.
 */
@Component
@Aspect  // 指定当前类为切面类
public class Aop {

    Logger logger = LoggerFactory.getLogger(getClass());


    //    private String exec;
//
//    public Aop(String exec) {
//        this.exec = exec;
//    }
//    String name =  "execution(* com.jiao.byagent.service..*.*(..))";
//    指定切入点表达式： 拦截哪些方法； 即为哪些类生成代理对象
//    execution(* com.jiao.byagent.service..*.*(..))
//    * com.jiao.byagent.service..*.*(..)
//
    @Pointcut("execution(* com.jiao.byagent.service..*.*(..))")
    public void pointCut_() {
    }

//    // 前置通知 : 在执行目标方法之前执行
//    @Before("pointCut_()")
//    public void begin() {
//        System.out.println("开始事务/异常");
//    }
//
//    // 后置/最终通知：在执行目标方法之后执行  【无论是否出现异常最终都会执行】
//    @After("pointCut_()")
//    public void after() {
//        System.out.println("提交事务/关闭");
//    }
//
//    // 返回后通知： 在调用目标方法结束后执行 【出现异常不执行】
//    @AfterReturning("pointCut_()")
//    public void afterReturning() {
//        System.out.println("afterReturning()");
//    }
//
    // 异常通知： 当目标方法执行异常时候执行此关注点代码
//    @AfterThrowing("pointCut_()")
//    public void afterThrowing() {
//        logger.error("出现错误 : occurs expection : " );
//    }

    // 环绕通知：环绕目标方式执行
    @Around("pointCut_()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        Object[] objs = pjp.getArgs();
//        Person p = (Person) objs[0];
//        System.out.println(p.getName());
//        System.out.println("环绕前....");
//        pjp.proceed();  // 执行目标方法
//        System.out.println("环绕后....");
        Object result = null;
        String methodName = "class:" + pjp.getTarget().getClass().getName() + " method:" + pjp.getSignature().getName(); //获取方法名称
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); //获取请求的URL//执行目标方法
        try {
            logger.info("开始执行方法：" + methodName);
            //前置通知
            result = pjp.proceed();//返回通知
            logger.info("方法执行成功：" + methodName);
        } catch (Throwable e) {
            //异常通知
            logger.error("出现错误：The method " + methodName + " occurs expection : " + e);
            throw e;  //让spring处理异常
        }finally {
            //记录日志
        }
        return result;  //必须返回结果，否则controller无法获取service返回的结果（正常情况下）

    }
}