package owner.code.demo.beanlifecycle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "aop", description = "面向切面编程")
@RestController
@RequestMapping("/kpt/aop")
@Slf4j
public class AopBackGroundController {

    @Autowired
    private HelloService helloService;

    @ApiOperation(value = "JDK动态代理测试", httpMethod = "GET")
    @RequestMapping(value = "/jdkproxy", method = RequestMethod.GET)
    public void testJDKProxy() {
        //JDK动态代理
        helloService.login("zz", "123456");
        System.out.println("--------------JDK动态代理------------");
        JDKProxyFactory jdkProxyFactory = new JDKProxyFactory(helloService);
        HelloService helloServiceProxy = (HelloService) jdkProxyFactory.createProxy();
        helloServiceProxy.login("zz", "123456");
    }

    @ApiOperation(value = "CGLIB动态代理测试", httpMethod = "GET")
    @RequestMapping(value = "/cglibproxy", method = RequestMethod.GET)
    public void testCGLIBProxy() {
        //CGLIB动态代理
        helloService.regist();
        System.out.println("--------------CGLIB动态代理------------");
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(helloService);
        HelloService helloServiceProxy = (HelloService) cglibProxyFactory.createProxy();
        helloServiceProxy.regist();
    }

    @ApiOperation(value = "AspectJ代理测试", httpMethod = "GET")
    @RequestMapping(value = "/aspectJproxy", method = RequestMethod.GET)
    public void testAspectJ() {
        //com.mmz.tkp.controller.aoptest.helloServiceHelper
        helloService.search();
    }

    @ApiOperation(value = "切点通知测试", httpMethod = "GET")
    @RequestMapping(value = "/aspectJproxy", method = RequestMethod.GET)
    public void testPointcutAdvice() {
        //com.mmz.tkp.controller.aoptest.helloServiceHelper
        helloService.search();
    }
}
