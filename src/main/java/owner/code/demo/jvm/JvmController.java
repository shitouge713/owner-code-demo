package owner.code.demo.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class JvmController {

    private static Logger logger = LoggerFactory.getLogger(JvmController.class);

    @GetMapping("/test")
    public void test(){
        logger.info("test success");
    }

    @GetMapping("/jvm")
    public void jvm(){
        jvmTest();
    }

    @GetMapping("/oomTest")
    public void oomTest(){
        List<Teacher> teacher = new ArrayList<Teacher>();
        while(true){
            Teacher a = new Teacher("abdasdsadfadfafadsfjadsfaabdasdsadfadfafadsfjadsfa",
                    "abdasdsadfadfafadsfjadsfaabdasdsadfadfafadsfjadsfaadsfa",
                    "adfasdfaabdasdsadfadfafadsfjadsfaabdasdsadfadfafadsfjadsfa",
                    "werabdasdsadfadfafadsfjadsfaabdasdsadfadfafadsfjadsfawe");
            teacher.add(a);
        }
    }

    public void jvmTest() {
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.run();
        }
    }

    private void run(){
        boolean flag =true;
        int i =0;
        while(flag){
            i++;
            System.out.println("基数:"+i);
        }
    }

}
