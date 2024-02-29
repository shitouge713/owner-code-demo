package owner.code.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan(basePackages = "owner.code.demo.dao")
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


