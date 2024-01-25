package owner.code.demo;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import owner.code.demo.cglib.MyFactoryBean;
import owner.code.demo.cglib.SingService;


@SpringBootApplication
public class OwnerCodeApplication {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context = SpringApplication.run(OwnerCodeApplication.class, args);
            System.out.println(context.getBean("myFactoryBean"));
            System.out.println(context.getBean("&myFactoryBean"));
            System.out.println("启动成功");
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}


