package owner.code.demo.beanlifecycle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description  代理增强类
 * @Author
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019-08-25
 */
@Component
@Aspect  //标识为一个切面供容器读取
public class HelloServiceHelper {
    @Before("execution(* owner.code.demo.beanlifecycle.HelloService.s*(..))")
    public void before(){
        System.out.println ("前置通知。。。");
    }

    @AfterReturning(value="execution(* owner.code.demo.beanlifecycle.HelloService.s*(..))")
    public void afterReturning(){
        System.out.println ("后置通知。。。");
    }

    @Around("execution(* owner.code.demo.beanlifecycle.HelloService.s*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println ("环绕前。。。");
        Object value = pjp.proceed();
        System.out.println (value);
        System.out.println ("环绕后。。。");
    }

    @After("execution(* owner.code.demo.beanlifecycle.HelloService.s*(..))")
    public void after(){
        System.out.println ("最终通知。。。");
    }

    @AfterThrowing(value="execution(* *.s*(..))",throwing="ex")
    public void afterThrowing(JoinPoint jp, Throwable ex){
        System.out.println ("异常抛出通知" + ex);
    }

    @Pointcut("execution(* owner.code.demo.beanlifecycle.HelloService.search(..))")
    public void mypointcut(){}

    @Pointcut("execution(* owner.code.demo.beanlifecycle.HelloService.update(..))")
    public void mypointcut1(){}

    //使用@Pointcut来声明切点，避免在每个通知中定义切点
    @Before("mypointcut()||mypointcut1()")
    public void before1(){
        System.out.println ("前置通知。。。。");
    }
}
