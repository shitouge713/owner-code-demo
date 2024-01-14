package owner.code.demo.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class ServiceTest {
    public static void main(String[] args) {
        TeacherSingService service  = new TeacherSingService();
        Enhancer en = new Enhancer();
        en.setSuperclass(SingService.class);
        en.setCallback(new TeacherSingService());
        SingService instance = (SingService)en.create();
        instance.sing();
    }
}
