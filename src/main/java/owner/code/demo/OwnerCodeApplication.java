package owner.code.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OwnerCodeApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(OwnerCodeApplication.class, args);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}
